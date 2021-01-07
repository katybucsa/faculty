using System;
using System.Collections.Generic;
using System.Threading;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using CompanyServices;
using CompanyModel;

namespace CompanyNetworking
{

    public class ServerProxy : IServer
    {
        private string host;
        private int port;

        private IObserver client;

        private NetworkStream stream;

        private IFormatter formatter;
        private TcpClient connection;

        private Queue<Response> responses;
        private volatile bool finished;
        private EventWaitHandle _waitHandle;
        public ServerProxy(string host, int port)
        {
            this.host = host;
            this.port = port;
            responses = new Queue<Response>();
        }

        public virtual void Login(User user, IObserver client)
        {
            InitializeConnection();
            SendRequest(new LoginRequest(user));
            Response response = ReadResponse();
            if (response is OkResponse)
            {
                this.client = client;
                return;
            }
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                CloseConnection();
                throw new AppException(err.Message);
            }
        }

        public virtual void Logout(User user, IObserver client)
        {
            SendRequest(new LogoutRequest(user));
            Response response = ReadResponse();
            CloseConnection();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                throw new AppException(err.Message);
            }
        }
        public Ride[] FindAllRides()
        {
            SendRequest(new GetAllRidesRequest());
            Response response = ReadResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                throw new AppException(err.Message);
            }
            GetAllRidesResponse ridesResponse = (GetAllRidesResponse)response;
            return ridesResponse.Rides;
        }

        public string BookPlaces(Ride ride, Client client, int nrplaces)
        {
            SendRequest(new BookPlacesRequest(ride, client, nrplaces));
            Response response = ReadResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                throw new AppException(err.Message);
            }
            BookPlacesResponse placesResponse = (BookPlacesResponse)response;
            return placesResponse.Places;
        }

        public RBooking[] FindAllRBookings(string dest, string date, string hour)
        {
            SendRequest(new GetAllRBookingsRequest(dest, date, hour));
            Response response = ReadResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                throw new AppException(err.Message);
            }
            GetAllRbookingsResponse rbookingsResponse = (GetAllRbookingsResponse)response;
            return rbookingsResponse.RBookings;
        }

        private void CloseConnection()
        {
            finished = true;
            try
            {
                stream.Close();
                //output.close();
                connection.Close();
                _waitHandle.Close();
                client = null;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

        }

        private void SendRequest(Request request)
        {
            try
            {
                lock (stream)
                {
                    formatter.Serialize(stream, request);
                    stream.Flush();
                }
            }
            catch (Exception e)
            {
                throw new AppException("Error sending object " + e);
            }

        }

        private Response ReadResponse()
        {
            Response response = null;
            try
            {
                _waitHandle.WaitOne();
                lock (responses)
                {
                    //Monitor.Wait(responses); 
                    response = responses.Dequeue();

                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return response;
        }

        private void InitializeConnection()
        {
            try
            {
                connection = new TcpClient(host, port);
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                finished = false;
                _waitHandle = new AutoResetEvent(false);
                startReader();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        private void startReader()
        {
            Thread tw = new Thread(run);
            tw.Start();
        }


        private void handleUpdate()
        {
            client.Update();
        }

        public virtual void run()
        {
            while (!finished)
            {
                try
                {
                    object response = formatter.Deserialize(stream);
                    Console.WriteLine("response received " + response);
                    if (response is UpdateResponse)
                    {
                        handleUpdate();
                    }
                    else
                    {
                        lock (responses)
                        {
                            responses.Enqueue((Response)response);
                        }
                        _waitHandle.Set();
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Reading error " + e);
                }

            }
        }

        public void NotifyObservers()
        {
            throw new NotImplementedException();
        }
    }

}