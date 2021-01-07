using System;
using System.Threading;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using CompanyServices;
using CompanyModel;
using CompanyPersistance;

namespace CompanyNetworking
{
    public class ClientWorker : IObserver //, Runnable
    {
        private IServer server;
        private TcpClient connection;

        private NetworkStream stream;
        private IFormatter formatter;
        private volatile bool connected;
        public ClientWorker(IServer server, TcpClient connection)
        {
            this.server = server;
            this.connection = connection;
            try
            {

                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                connected = true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        public virtual void run()
        {
            while (connected)
            {
                try
                {
                    object request = formatter.Deserialize(stream);
                    object response = HandleRequest((Request)request);
                    if (response != null)
                    {
                        SendResponse((Response)response);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }

                try
                {
                    Thread.Sleep(1000);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }
            try
            {
                stream.Close();
                connection.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Error " + e);
            }
        }


        private Response HandleRequest(Request request)
        {
            Response response = null;
            if (request is LoginRequest)
            {
                Console.WriteLine("Login request ...");
                LoginRequest logReq = (LoginRequest)request;
                User user = logReq.User;
                try
                {
                    lock (server)
                    {
                        server.Login(user, this);
                    }
                    return new OkResponse();
                }
                catch (RepositoryException e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }
            if (request is LogoutRequest)
            {
                Console.WriteLine("Logout request");
                LogoutRequest logReq = (LogoutRequest)request;
                User user = logReq.User;
                try
                {
                    lock (server)
                    {

                        server.Logout(user, this);
                    }
                    connected = false;
                    return new OkResponse();

                }
                catch (RepositoryException e)
                {
                    return new ErrorResponse(e.Message);
                }
            }
            if (request is GetAllRidesRequest)
            {
                Console.WriteLine("GetAllRides request");
                GetAllRidesRequest ridesReq = (GetAllRidesRequest)request;
                try
                {
                    Ride[] rides;
                    lock (server)
                    {
                        rides = server.FindAllRides();
                    }
                    return new GetAllRidesResponse(rides);
                }
                catch (RepositoryException err)
                {
                    return new ErrorResponse(err.Message);
                }
            }
            if (request is BookPlacesRequest)
            {
                Console.WriteLine("BookPlaces request");
                BookPlacesRequest bookReq = (BookPlacesRequest)request;
                try
                {
                    String places;
                    lock (server)
                    {
                        places = server.BookPlaces(bookReq.Ride, bookReq.Client, bookReq.NrPlaces);
                    }
                    return new BookPlacesResponse(places);
                }
                catch (RepositoryException ex)
                {
                    return new ErrorResponse(ex.Message);
                }
            }

            if (request is GetAllRBookingsRequest)
            {
                Console.WriteLine("GetAllRBookings request");
                GetAllRBookingsRequest rbookingsReq = (GetAllRBookingsRequest)request;
                try
                {
                    RBooking[] rBookings;
                    lock (server)
                    {
                        rBookings = server.FindAllRBookings(rbookingsReq.Destination, rbookingsReq.Date, rbookingsReq.Hour);
                    }
                    return new GetAllRbookingsResponse(rBookings);
                }
                catch (RepositoryException ex)
                {
                    return new ErrorResponse(ex.Message);
                }
            }

            return response;
        }

        private void SendResponse(Response response)
        {
            Console.WriteLine("sending response " + response);
            lock (stream)
            {
                formatter.Serialize(stream, response);
                stream.Flush();
            }
        }

        public void Update()
        {
            SendResponse(new UpdateResponse());
        }
    }

}