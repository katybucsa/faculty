using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using log4net;
using CompanyModel;

namespace CompanyPersistance
{
    public class RideRepository : IRideRepository
    {
        private static readonly ILog log = LogManager.GetLogger("RideRepository");
        IDictionary<String, String> props;
        public RideRepository(IDictionary<String, String> props)
        {
            log.Info("Creating RideRepository");
            this.props = props;
        }

        public void Delete(int id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Ride> FindAll()
        {

            log.InfoFormat("Finding all rides");
            IDbConnection con = DBUtils.getConnection(props);
            IList<Ride> rides = new List<Ride>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Ride";

                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        String destination = dataR.GetString(1);
                        String date = dataR.GetString(2);
                        String hour = dataR.GetString(3);
                        String places = dataR.GetString(4);
                        Ride ride = new Ride(idV, destination, date, hour);
                        ride.Places = places;
                        rides.Add(ride);
                    }
                    log.InfoFormat("Exiting findAll with value {0}", rides);
                    return rides;
                }
            }
            log.InfoFormat("Exiting findAll with value {0}", null);
        }

        public Ride FindOne(int id)
        {

            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Ride where ride_id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        String destination = dataR.GetString(1);
                        String date = dataR.GetString(2);
                        String hour = dataR.GetString(3);
                        String places = dataR.GetString(4);
                        Ride ride = new Ride(idV, destination, date, hour);
                        ride.Places = places;
                        log.InfoFormat("Exiting findOne with value {0}", ride);
                        return ride;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            throw new RepositoryException("Cursa inexistenta!");
        }

        public Ride FindOneby_Destination_Date_Hour(string destination, string date, string hour)
        {
            log.InfoFormat("Finding ride with destination {0}, date {1} and hour {2}", destination, date, hour);
            var con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Ride where destination=@dest and date=@date and hour=@hour";

                var paramDest = comm.CreateParameter();
                paramDest.ParameterName = "@dest";
                paramDest.Value = destination;
                comm.Parameters.Add(paramDest);

                var datee = comm.CreateParameter();
                datee.ParameterName = "@date";
                datee.Value = date;
                comm.Parameters.Add(datee);

                var hourr = comm.CreateParameter();
                hourr.ParameterName = "@hour";
                hourr.Value = hour;
                comm.Parameters.Add(hourr);
                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        String places = dataR.GetString(4);
                        Ride ride = new Ride(idV, destination, date, hour);
                        ride.Places = places;
                        log.InfoFormat("Exiting findOne with value {0}", ride);
                        return ride;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            throw new RepositoryException("Cursa nu a putut fi localizata!");
        }



        public void Save(Ride e)
        {
            var con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into Ride  values (@id, @dest, @date, @hour, @places)";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = e.Id;
                comm.Parameters.Add(paramId);

                var paramDest = comm.CreateParameter();
                paramDest.ParameterName = "@dest";
                paramDest.Value = e.Destination;
                comm.Parameters.Add(paramDest);

                var date = comm.CreateParameter();
                date.ParameterName = "@date";
                date.Value = e.Date;
                comm.Parameters.Add(date);

                var hour = comm.CreateParameter();
                hour.ParameterName = "@hour";
                hour.Value = e.Hour;
                comm.Parameters.Add(hour);

                var places = comm.CreateParameter();
                places.ParameterName = "@places";
                places.Value = e.Places;
                comm.Parameters.Add(places);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No ride added !");
            }
        }


        public void Update(Ride e)
        {
            var con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "UPDATE Ride SET places=@places WHERE ride_id=@id";

                var paramPlaces = comm.CreateParameter();
                paramPlaces.ParameterName = "@places";
                paramPlaces.Value = e.Places;
                comm.Parameters.Add(paramPlaces);

                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = e.Id;
                comm.Parameters.Add(paramId);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("Ride wasn't  updated!");
            }
        }
    }
}



