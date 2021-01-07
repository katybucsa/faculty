using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace RESTClient
{
    class MainClass
    {
        static HttpClient client = new HttpClient();

        public static void Main(string[] args)
        {
            RunAsync().Wait();
        }


        static async Task RunAsync()
        {
            client.BaseAddress = new Uri("http://localhost:8080/company/rides");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            Ride ride = new Ride(4, "Suceava", "2019-28-05", "12:48");

            //create
            //Ride addedRide = await CreateRide("http://localhost:8080/company/rides", ride);
            //Console.WriteLine("Added ride: " + ride);

            //update
            //ride.Destination = "Paris";
            //Ride updatedRide = await UpdateRide("http://localhost:8080/company/rides/4", ride);

            //delete
            //Ride deletedRide = await DeleteRide("http://localhost:8080/company/rides/4");
            //Console.WriteLine("Deleted ride: " + deletedRide);

            //getById
            Console.WriteLine("Get ride with id 2");
            Ride result = await GetRideAsync("http://localhost:8080/company/rides/2");
            Console.WriteLine("Ride: " + result);
            Console.ReadLine();


            //getAll
            //IList<Ride> allRides = await GetAllRidesAsync("http://localhost:8080/company/rides");
            //Console.WriteLine("AllRides request...");
            //foreach (var ride in allRides)
            //    Console.WriteLine(ride);

        }

        static async Task<Ride> CreateRide(string path, Ride ride)
        {
            string r = null;
            Ride result = null;
            String serializedRide = Newtonsoft.Json.JsonConvert.SerializeObject(ride);
            var content = new StringContent(serializedRide, Encoding.UTF8, "application/json");
            var response = await client.PostAsync(path, content);
            if (response.IsSuccessStatusCode)
                r = await response.Content.ReadAsStringAsync();
            result = Newtonsoft.Json.JsonConvert.DeserializeObject<Ride>(r);
            return result;
        }

        static async Task<Ride> UpdateRide(string path, Ride ride)
        {
            string r = null;
            Ride result = null;
            String serializedRide = Newtonsoft.Json.JsonConvert.SerializeObject(ride);
            var content = new StringContent(serializedRide, Encoding.UTF8, "application/json");
            var response = await client.PutAsync(path, content);
            if (response.IsSuccessStatusCode)
                r = await response.Content.ReadAsStringAsync();
            result = Newtonsoft.Json.JsonConvert.DeserializeObject<Ride>(r);
            return result;
        }

        static async Task<Ride> DeleteRide(string path)
        {
            string r = null;
            Ride result = null;
            var response = await client.DeleteAsync(path);
            if (response.IsSuccessStatusCode)
                r = await response.Content.ReadAsStringAsync();
            result = Newtonsoft.Json.JsonConvert.DeserializeObject<Ride>(r);
            return result;
        }

        static async Task<Ride> GetRideAsync(string path)
        {
            String r = null;
            Ride result = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
                r = await response.Content.ReadAsStringAsync();
            result = Newtonsoft.Json.JsonConvert.DeserializeObject<Ride>(r);
            return result;
        }

        static async Task<IList<Ride>> GetAllRidesAsync(string path)
        {
            string r = null;
            IList<Ride> rides = new List<Ride>();
            var response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                r = await response.Content.ReadAsStringAsync();
                rides = Newtonsoft.Json.JsonConvert.DeserializeObject<IList<Ride>>(r);
            }
            return rides;
        }

    }

}
