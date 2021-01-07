

using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using CompanyServices;

namespace CompanyNetworking
{


    public abstract class AbstractServer
    {
        private TcpListener server;
        private String host;
        private int port;
        public AbstractServer(String host, int port)
        {
            this.host = host;
            this.port = port;
        }
        public void Start()
        {
            IPAddress adr = IPAddress.Parse(host);
            IPEndPoint ep = new IPEndPoint(adr, port);
            server = new TcpListener(ep);
            server.Start();
            while (true)
            {
                Console.WriteLine("Waiting for clients ...");
                TcpClient client = server.AcceptTcpClient();
                Console.WriteLine("Client connected ...");
                processRequest(client);
            }
        }

        public abstract void processRequest(TcpClient client);

    }


    public abstract class ConcurrentServer : AbstractServer
    {

        public ConcurrentServer(string host, int port) : base(host, port)
        { }

        public override void processRequest(TcpClient client)
        {

            Thread t = createWorker(client);
            t.Start();

        }

        protected abstract Thread createWorker(TcpClient client);

    }

    public class SerialServer : ConcurrentServer
    {
        private IServer server;
        private ClientWorker worker;
        public SerialServer(string host, int port, IServer server) : base(host, port)
        {
            this.server = server;
            Console.WriteLine("SerialChatServer...");
        }
        protected override Thread createWorker(TcpClient client)
        {
            worker = new ClientWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }

}
