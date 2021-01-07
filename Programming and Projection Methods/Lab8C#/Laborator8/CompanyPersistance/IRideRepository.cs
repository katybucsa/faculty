using CompanyModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyPersistance
{
    public interface IRideRepository:IRepository<int,Ride>
    {
        Ride FindOneby_Destination_Date_Hour(String destination, String date, String hour);
    }
}
