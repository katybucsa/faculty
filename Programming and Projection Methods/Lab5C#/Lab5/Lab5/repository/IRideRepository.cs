using Lab5.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab5.repository
{
    public interface IRideRepository:IRepository<int,Ride>
    {
        Ride findOneby_Destination_Date_Hour(String destination, String date, String hour);
    }
}
