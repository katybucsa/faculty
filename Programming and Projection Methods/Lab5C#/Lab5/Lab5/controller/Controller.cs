using Lab5.domain;
using Lab5.service;
using Lab5.utils;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab5.controller
{
    public class Controller:Observer
    {
        private Service service;
        private ObservableCollection<Object> rModel;
        private IList<KeyValuePair<ObservableCollection<RBooking>, List<String>>> rbModelList;

        public Controller(Service service)
        {
            this.service = service;
            service.AddObserver(this);
            rModel = new ObservableCollection<Object>();
            rbModelList = new List<KeyValuePair<ObservableCollection<RBooking>, List<String>>>();
            PopulateRideList();
        }

        private void PopulateRideList()
        {
            rModel.Clear();
            service.findAllRides().ToList().ForEach(x => rModel.Add(new { Destinatia=x.Destination, Data=x.Date, Ora=x.Hour, NrLocuriDisponibile=x.NrPlacesAvailable }));
            rModel.CollectionChanged += RModel_CollectionChanged;
        }

        private void RModel_CollectionChanged(object sender, System.Collections.Specialized.NotifyCollectionChangedEventArgs e)
        {
            //NotifyCollectionChangedEventArgs args = new NotifyCollectionChangedEventArgs(NotifyCollectionChangedAction.Reset);
            //OnCollectionChanged(args);
        }

        public ObservableCollection<Object> getRModel()
        {
            return rModel;
        }

        public ObservableCollection<RBooking> getRBModel(string destination,string date,string hour)
        {
            ObservableCollection<RBooking> rbModel = new ObservableCollection<RBooking>();
            service.findAllRBookings(destination, date, hour).ToList().ForEach(x => rbModel.Add(x));
            rbModelList.Add(new KeyValuePair<ObservableCollection<RBooking>,List<string>>( rbModel, new List<string> { destination, date, hour }));
            return rbModel;
        }

        public User FindUser(String username,String password)
        {
            return service.findUser(username, password);
        }

        public String bookPlaces(Ride r,String cname,int nrplaces)
        {
            return service.bookPlaces(r, new Client(0, cname), nrplaces);
        }

        public IEnumerable<Ride> GetAllRides()
        {
            return service.findAllRides();
        }
        public IEnumerable<RBooking> FindAllRideBookings(string dest,string date,string hour)
        {
            return service.findAllRBookings(dest, date, hour);
        }

        private void Upd(KeyValuePair<ObservableCollection<RBooking>, List<string>> pair)
        {
            pair.Key.Clear();
            service.findAllRBookings(pair.Value.ElementAt(0), pair.Value.ElementAt(1), pair.Value.ElementAt(2))
                .ToList().ForEach(x => pair.Key.Add(x));
        }

        public override void Update()
        {
            this.PopulateRideList();
            this.rbModelList.ToList().ForEach(x => this.Upd(x));
        }
    }
}
