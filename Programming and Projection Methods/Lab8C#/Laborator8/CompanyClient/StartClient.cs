using log4net.Config;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;
using CompanyServices;
using CompanyNetworking;

namespace CompanyClient
{
    static class StartClient
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            //Application.SetCompatibleTextRenderingDefault(false);

            IServer server1 = new ServerProxy("127.0.0.1", 55555);
            Controller controller1 = new Controller(server1);
            LoginView loginView1 = new LoginView();
            loginView1.setAttributes(controller1);
            Application.Run(loginView1);

            IServer server2 = new ServerProxy("127.0.0.1", 55555);
            Controller controller2 = new Controller(server2);
            LoginView loginView2 = new LoginView();
            loginView2.setAttributes(controller2);
            Application.Run(loginView2);
        }


    }
}
