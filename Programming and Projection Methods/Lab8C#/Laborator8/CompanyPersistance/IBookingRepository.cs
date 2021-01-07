using CompanyModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyPersistance
{
    public interface IBookingRepository:IRepository<KeyValuePair<Ride, Client>,Booking>
    {
        IEnumerable<RBooking> FindBookingsByDestDateHour(String destination, String date, String hour);
    }
}
