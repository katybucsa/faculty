using Lab5.domain;
using Lab5.utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab5.repository
{
    public interface IBookingRepository:IRepository<KeyValuePair<Ride, Client>,Booking>
    {
        IEnumerable<RBooking> findBookingsByDestDateHour(String destination, String date, String hour);
    }
}
