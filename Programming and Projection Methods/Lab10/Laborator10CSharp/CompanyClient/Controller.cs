using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Threading;
using CompanyMode;
using CompanyModel;
using Thrift.Server;
using Thrift.Transport;

namespace CompanyClient
{
    public class Controller : IObserver.Iface
    {
        public event EventHandler<UserEventArgs> UpdateEvent;
        private IServer.Iface server;
        private User user;
        private int port;
        private IList<Object> rModel;
        private IList<KeyValuePair<IList<RBooking>, IList<String>>> rbModelList;

        public Controller(IServer.Iface server)
        {
            this.server = server;
            rModel = new List<Object>();
            rbModelList = new List<KeyValuePair<IList<RBooking>, IList<String>>>();
        }

        public User FindUser(String username, String password)
        {
            User user = new User(username, password);
            int p = server.login(user);
            this.user = user;
            this.port = p;
            new Thread(ClientServ).Start();
            return user;
        }

        public void ClientServ()
        {
            IObserver.Processor processor = new IObserver.Processor(this);
            TServerTransport serverTransport = new TServerSocket(port);
            TServer serverc = new TThreadPoolServer(processor, serverTransport);
            serverc.Serve();
        }


        private void PopulateRideList()
        {
            rModel.Clear();
            IList<Ride> rides = server.findAllRides();
            foreach (var r in rides)
                rModel.Add(new
                {
                    Destinatia = r.Destination,
                    Data = r.Date,
                    Ora = r.Hour,
                    NrLocuriDisponibile = r.NrPlacesAvailable
                });
        }

        public IList<Object> GetRModel()
        {
            PopulateRideList();
            return rModel;
        }

        public IList<RBooking> GetRBModel(string destination, string date, string hour)
        {
            IList<RBooking> rbModel = new ObservableCollection<RBooking>();
            server.findAllRBookings(destination, date, hour).ToList().ForEach(x => rbModel.Add(x));
            rbModelList.Add(new KeyValuePair<IList<RBooking>, IList<string>>(rbModel, new List<string> { destination, date, hour }));
            return rbModel;
        }


        public void Logout(User user)
        {
            server.logout(user);
        }

        public String BookPlaces(Ride r, String cname, int nrplaces)
        {
            String places = server.bookPlaces(r, new Clientj(0, cname), nrplaces);
            return places;
        }

        private void Upd(KeyValuePair<IList<RBooking>, IList<string>> pair)
        {
            pair.Key.Clear();
            server.findAllRBookings(pair.Value.ElementAt(0), pair.Value.ElementAt(1), pair.Value.ElementAt(2))
                .ToList().ForEach(x => pair.Key.Add(x));
        }

        protected virtual void OnUserEvent(UserEventArgs e)
        {
            if (UpdateEvent == null)
                return;
            UpdateEvent(this, e);
            Console.WriteLine("Update event called");
        }

        public void update()
        {
            Console.WriteLine("Controller update");
            UserEventArgs userArgs = new UserEventArgs();
            OnUserEvent(userArgs);
        }
    }
}