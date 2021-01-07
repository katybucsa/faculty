using CompanyPersistance;
using System;
using System.Collections.Generic;
using System.Configuration;
using log4net.Config;
using CompanyNetworking;
using CompanyServices;

namespace CompanyServer
{
    class StartServer
    {
        static void Main(string[] args)
        {
            XmlConfigurator.Configure(new System.IO.FileInfo("log4j.xml"));

            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("Firma_de_transport"));

            IUserRepository user_repo = new UserRepository(props);
            IRideRepository ride_repo = new RideRepository(props);
            IClientRepository client_repo = new ClientRepository(props);
            IBookingRepository booking_repo = new BookingRepository(props, ride_repo, client_repo);
            IServer serverImpl = new Server(user_repo, ride_repo, client_repo, booking_repo);
            SerialServer server = new SerialServer("127.0.0.1", 55555, serverImpl);
            server.Start();
            Console.WriteLine("Server starter ...");
            Console.ReadLine();
        }
        static string GetConnectionStringByName(string name)
        {
            // Assume failure.
            string returnValue = null;

            // Look for the name in the connectionStrings section.
            ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[name];

            // If found, return the connection string.
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }
    }
}
