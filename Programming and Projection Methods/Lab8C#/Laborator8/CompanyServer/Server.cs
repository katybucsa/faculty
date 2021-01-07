
using CompanyModel;
using CompanyPersistance;
using CompanyServices;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyServer
{
    public class Server : IServer
    {
        private IUserRepository user_repo;
        private IRideRepository ride_repo;
        private IClientRepository client_repo;
        private IBookingRepository booking_repo;
        private readonly IDictionary<String, IObserver> loggedClients;

        public Server(IUserRepository user_repo, IRideRepository ride_repo, IClientRepository client_repo, IBookingRepository booking_repo)
        {
            this.user_repo = user_repo;
            this.ride_repo = ride_repo;
            this.client_repo = client_repo;
            this.booking_repo = booking_repo;
            loggedClients = new Dictionary<String, IObserver>();
        }


        public void Login(User u, IObserver o)
        {
            User user = user_repo.FindOne(u.Id, u.Password);
            loggedClients.Add(user.Id, o);
        }

        public void Logout(User u, IObserver o)
        {
            loggedClients.Remove(u.Id);
        }

        public RBooking[] FindAllRBookings(String dest, String date, String hour)
        {
            return booking_repo.FindBookingsByDestDateHour(dest, date, hour).ToArray();
        }

        public String BookPlaces(Ride ride, Client c, int nrplaces)
        {
            ride = ride_repo.FindOneby_Destination_Date_Hour(ride.Destination, ride.Date, ride.Hour);
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
            Booking booking = new Booking(new KeyValuePair<Ride, Client>(ride, cl), nrplaces, places);
            booking_repo.Save(booking);
            NotifyObservers();
            return places;
        }

        public Ride[] FindAllRides()
        {
            return ride_repo.FindAll().ToArray();
        }


        public void NotifyObservers()
        {
            Task.Run(() => loggedClients.Values.ToList().ForEach(x => x.Update()));
        }
    }
}
