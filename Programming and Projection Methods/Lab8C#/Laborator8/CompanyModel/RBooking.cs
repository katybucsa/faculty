using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyModel
{
    [Serializable]
    public class RBooking
    {
        
        public int Loc { get; set; }
        public String Client { get; set; }

        public RBooking(int place, String name)
        {
            this.Client = name;
            this.Loc = place;
        }
    }
}
