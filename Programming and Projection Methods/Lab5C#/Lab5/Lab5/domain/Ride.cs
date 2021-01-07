using Lab5.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab5.domain
{
    public class Ride:IHasID<int>
    {
        public Ride(int id, string destination, string date, string hour)
        {
            Id = id;
            Destination = destination;
            Date = date;
            Hour = hour;
            Places = "0000000000000000000";
        }

        public int Id { get; set; }
        public string Destination { get; set; }
        public string Date { get; set; }
        public string Hour { get; set; }
        public string Places { get; set; }
        public int NrPlacesAvailable { get { return Places.Count(x => x.Equals('0')) - 1; } }
       

        public override string ToString()
        {
            return Id + " " + Destination + " " + Date + " " + Hour + " " + Places;
        }

    }


}
