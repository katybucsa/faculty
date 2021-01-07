using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using CompanyServices;
using CompanyModel;

namespace CompanyClient
{
    public class Controller : IObserver
    {
        public event EventHandler<UserEventArgs> UpdateEvent;
        private IServer server;
        private User user;
        private IList<Object> rModel;
        private IList<KeyValuePair<IList<RBooking>, IList<String>>> rbModelList;

        public Controller(IServer server)
        {
            this.server = server;
            rModel = new List<Object>();
            rbModelList = new List<KeyValuePair<IList<RBooking>, IList<String>>>();
        }

        private void PopulateRideList()
        {
            rModel.Clear();
            Ride[] rides = server.FindAllRides();
            foreach (var r in rides)
                rModel.Add(new { Destinatia = r.Destination, Data = r.Date, Ora = r.Hour, NrLocuriDisponibile = r.NrPlacesAvailable });
        }

        public IList<Object> GetRModel()
        {
            PopulateRideList();
            return rModel;
        }

        public IList<RBooking> GetRBModel(string destination, string date, string hour)
        {
            IList<RBooking> rbModel = new ObservableCollection<RBooking>();
            server.FindAllRBookings(destination, date, hour).ToList().ForEach(x => rbModel.Add(x));
            rbModelList.Add(new KeyValuePair<IList<RBooking>, IList<string>>(rbModel, new List<string> { destination, date, hour }));
            return rbModel;
        }

        public User FindUser(String username, String password)
        {
            User user = new User(username, password);
            server.Login(user, this);
            this.user = user;
            return user;
        }

        public void Logout(User user)
        {
            server.Logout(user, this);
        }

        public String BookPlaces(Ride r, String cname, int nrplaces)
        {
            String places = server.BookPlaces(r, new Client(0, cname), nrplaces);
            return places;
        }

        private void Upd(KeyValuePair<IList<RBooking>, IList<string>> pair)
        {
            pair.Key.Clear();
            server.FindAllRBookings(pair.Value.ElementAt(0), pair.Value.ElementAt(1), pair.Value.ElementAt(2))
                .ToList().ForEach(x => pair.Key.Add(x));
        }

        protected virtual void OnUserEvent(UserEventArgs e)
        {
            if (UpdateEvent == null)
                return;
            UpdateEvent(this, e);
            Console.WriteLine("Update event called");
        }

        public void Update()
        {
            Console.WriteLine("Controller update");
            UserEventArgs userArgs = new UserEventArgs();
            OnUserEvent(userArgs);
        }
    }
}
