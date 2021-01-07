using CompanyModel;
using System;
namespace CompanyNetworking
{

    public interface Response
    {
    }

    [Serializable]
    public class OkResponse : Response
    {

    }

    [Serializable]
    public class GetAllRidesResponse : Response
    {
        private Ride[] rides;
        public GetAllRidesResponse(Ride[] rides)
        {
            this.rides = rides;
        }
        public virtual Ride[] Rides
        {
            get { return rides; }
        }
    }

    [Serializable]
    public class BookPlacesResponse : Response
    {
        private String places;

        public BookPlacesResponse(string places)
        {
            this.places = places;
        }
        public virtual String Places
        {
            get { return places; }
        }
    }

    [Serializable]
    public class GetAllRbookingsResponse : Response
    {
        private RBooking[] rbookings;

        public GetAllRbookingsResponse(RBooking[] rbookings)
        {
            this.rbookings = rbookings;
        }
        public virtual RBooking[] RBookings
        {
            get { return rbookings; }
        }
    }

    [Serializable]
    public class ErrorResponse : Response
    {
        private string message;

        public ErrorResponse(string message)
        {
            this.message = message;
        }

        public virtual string Message
        {
            get
            {
                return message;
            }
        }
    }

    [Serializable]
    public class UpdateResponse : Response
    {
    }
}