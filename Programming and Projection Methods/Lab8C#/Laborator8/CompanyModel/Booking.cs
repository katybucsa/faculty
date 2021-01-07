
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyModel
{
    [Serializable]
    public class Booking:IHasID<KeyValuePair<Ride, Client>>
    {
        public Booking(KeyValuePair<Ride, Client> id, int nrPlacesWanted, string places)
        {
            Id = id;
            NrPlacesWanted = nrPlacesWanted;
            Places = places;
        }

        public KeyValuePair<Ride,Client> Id { get; set; }
        public int NrPlacesWanted { get; set; }
        public string Places { get; set; }

    }
}
