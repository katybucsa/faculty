using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CompanyModel;

namespace CompanyServices
{
    public interface IServer
    {
        void Login(User u, IObserver o);
        void Logout(User u, IObserver o);
        RBooking[] FindAllRBookings(String dest, String date, String hour);
        String BookPlaces(Ride ride, Client client, int nrplaces);
        Ride[] FindAllRides();
        void NotifyObservers();
    }
}
