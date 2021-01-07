using Lab5.domain;
using Lab5.repository;
using Lab5.utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab5.service
{
    public class Service:Observable
    {
        private IUserRepository user_repo;
        private IRideRepository ride_repo;
        private IClientRepository client_repo;
        private IBookingRepository booking_repo;
        private IList<Observer> observers = new  List<Observer>();

        public Service(IUserRepository user_repo, IRideRepository ride_repo, IClientRepository client_repo, IBookingRepository booking_repo)
        {
            this.user_repo = user_repo;
            this.ride_repo = ride_repo;
            this.client_repo = client_repo;
            this.booking_repo = booking_repo;
        }

        public User findUser(String username,String password)
        {
            return user_repo.FindOne(username, password);
        }

        public IEnumerable<RBooking> findAllRBookings(String dest,String date,String hour)
        {
            return booking_repo.findBookingsByDestDateHour(dest, date, hour);
        }

        public String bookPlaces(Ride ride,Client c,int nrplaces)
        {
            ride = ride_repo.findOneby_Destination_Date_Hour(ride.Destination, ride.Date, ride.Hour);
            client_repo.Save(c);
            String places = "";
            int x = nrplaces;
            String p = ride.Places;
            for (int i = 1; i <= 18; i++)
            {
                if (ride.Places[i].Equals('0'))
                {
                    if (x == 1)
                        places += i;
                    else
                        places += i + ",";
                    p = p.Substring(0, i) + '1' + p.Substring(i + 1);
                    x--;
                    if (x == 0)
                        break;
                }
            }
            ride.Places = p;
            ride_repo.Update(ride);
            Client cl = client_repo.FindLastAdded();
            Booking booking = new Booking(new KeyValuePair<Ride,Client>(ride,cl), nrplaces, places);
            booking_repo.Save(booking);
            NotifyObservers();
            return places;
        }

        public IEnumerable<Ride> findAllRides()
        {
            return ride_repo.FindAll();
        }

        public override void AddObserver(Observer o)
        {
            observers.Add(o);
        }

        public override void NotifyObservers()
        {
            observers.ToList().ForEach(x => x.Update());
        }
    }
}
