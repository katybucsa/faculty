using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;
using Thrift.Protocol;
using Thrift.Transport;
using Thrift.Server;

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
            Application.SetCompatibleTextRenderingDefault(false);

            //being client
            TTransport transport = new TSocket("localhost", 9090);
            transport.Open();

            TProtocol protocol = new TBinaryProtocol(transport);

            IServer.Client client = new IServer.Client(protocol);
            Controller controller = new Controller(client);
            LoginView loginView = new LoginView();
            loginView.setAttributes(controller);
            Application.Run(loginView);
        }
    }
}
