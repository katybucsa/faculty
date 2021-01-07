using CompanyModel;
using System;
namespace CompanyNetworking
{
    public interface Request
    {
    }

    [Serializable]
    public class LoginRequest : Request
    {
        private User user;

        public LoginRequest(User user)
        {
            this.user = user;
        }

        public virtual User User
        {
            get
            {
                return user;
            }
        }
    }

    [Serializable]
    public class LogoutRequest : Request
    {
        private User user;

        public LogoutRequest(User user)
        {
            this.user = user;
        }

        public virtual User User
        {
            get
            {
                return user;
            }
        }
    }

    [Serializable]
    public class GetAllRidesRequest : Request
    {

    }

    [Serializable]
    public class BookPlacesRequest : Request
    {
        private Ride ride;
        private Client client;
        private int nrplaces;

        public BookPlacesRequest(Ride ride, Client client, int nrplaces)
        {
            this.ride = ride;
            this.client = client;
            this.nrplaces = nrplaces;
        }

        public virtual Ride Ride
        {
            get { return ride; }
        }

        public virtual Client Client
        {
            get { return client; }
        }

        public virtual int NrPlaces
        {
            get { return nrplaces; }
        }
    }

    [Serializable]
    public class GetAllRBookingsRequest : Request
    {
        private String destination;
        private String date;
        private String hour;

        public GetAllRBookingsRequest(string destination, string date, string hour)
        {
            this.destination = destination;
            this.date = date;
            this.hour = hour;
        }

        public virtual String Destination
        {
            get { return destination; }
        }

        public virtual String Date
        {
            get { return date; }
        }

        public virtual String Hour
        {
            get { return hour; }
        }
    }

}