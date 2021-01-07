using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RESTClient
{
    [Serializable]
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

        [JsonProperty(PropertyName = "id")]
        public int Id { get; set; }

        [JsonProperty(PropertyName = "destination")]
        public string Destination { get; set; }

        [JsonProperty(PropertyName = "date")]
        public string Date { get; set; }

        [JsonProperty(PropertyName = "hour")]
        public string Hour { get; set; }

        [JsonProperty(PropertyName = "places")]
        public string Places { get; set; }
       

        public override string ToString()
        {
            return Id + " " + Destination + " " + Date + " " + Hour + " " + Places;
        }

    }


}
