using Lab5.controller;
using Lab5.repository;
using Lab5.service;
using log4net.Config;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;

using Lab5.view;

namespace Lab5
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            //configurare jurnalizare folosind log4net
            XmlConfigurator.Configure(new System.IO.FileInfo("log4j.xml"));

            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("Firma_de_transport"));

            IUserRepository user_repo = new UserRepository(props);
            IRideRepository ride_repo = new RideRepository(props);
            IClientRepository client_repo = new ClientRepository(props);
            IBookingRepository booking_repo = new BookingRepository(props,ride_repo,client_repo);
            Service service = new Service(user_repo, ride_repo, client_repo, booking_repo);
            Controller controller = new Controller(service);
            LoginView loginView = new LoginView();
            loginView.setAttributes(controller);
            Application.EnableVisualStyles();
            //Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(loginView);
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
