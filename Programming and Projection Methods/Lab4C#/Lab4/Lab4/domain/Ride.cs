using Lab4.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4.domain
{
    class Ride:IHasID<int>
    {
        public Ride(int id, string destination, string date, string hour, string places)
        {
            Id = id;
            Destination = destination;
            Date = date;
            Hour = hour;
            Places = places;
        }

        public int Id { get; set; }
        public string Destination { get; set; }
        public string Date { get; set; }
        public string Hour { get; set; }
        public string Places { get; set; }

        
    }
}
