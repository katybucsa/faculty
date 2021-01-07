using Lab5.domain;
using Lab5.utils;
using log4net;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab5.repository
{
    public class BookingRepository : IBookingRepository
    {
        private static readonly ILog log = LogManager.GetLogger("BookingRepository");
        private IDictionary<String, String> props;
        private IRideRepository ride_repo;
        private IClientRepository client_repo;

        public BookingRepository(IDictionary<string, string> props, IRideRepository ride_repo, IClientRepository client_repo)
        {
            log.Info("Creating BookingRepository");
            this.props = props;
            this.ride_repo = ride_repo;
            this.client_repo = client_repo;
        }


        public void Delete(KeyValuePair<Ride, Client> id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Booking> FindAll()
        {
            throw new NotImplementedException();
        }

        public IEnumerable<RBooking> findBookingsByDestDateHour(string destination, string date, string hour)
        {
            log.InfoFormat("Finding bookings for destination {0}, date {1} and hour {2}", destination, date, hour);
            IDbConnection con = DBUtils.getConnection(props);
            List<RBooking> rbookings = new List<RBooking>();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select c.name , b.places " +
                    "from Ride as [r] inner join Booking as [b] on r.ride_id=b.ride_id  " +
                    "inner join Client as [c] on b.client_id=c.client_id " +
                "where r.destination=@destination and r.date=@date and r.hour=@hour";
                IDbDataParameter paramDest = comm.CreateParameter();
                paramDest.ParameterName = "@destination";
                paramDest.Value = destination;
                comm.Parameters.Add(paramDest);
                IDbDataParameter paramDate = comm.CreateParameter();
                paramDate.ParameterName = "@date";
                paramDate.Value = date;
                comm.Parameters.Add(paramDate);
                IDbDataParameter paramHour = comm.CreateParameter();
                paramHour.ParameterName = "@hour";
                paramHour.Value = hour;
                comm.Parameters.Add(paramHour);
                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        String cname = dataR.GetString(0);
                        String cplaces = dataR.GetValue(1).ToString();
                        String[] cp = cplaces.Split(',');
                        for (int i = 0; i < cp.Length; ++i)
                        {
                            RBooking rb = new RBooking(int.Parse(cp[i]), cname);
                            rbookings.Add(rb);
                        }
                    }
                }
                Ride r = ride_repo.findOneby_Destination_Date_Hour(destination, date, hour);
                for (int i = 1; i < 19; i++)
                {
                    if (r.Places[i].Equals('0'))
                        rbookings.Add(new RBooking(i, "-"));
                }
                rbookings.Sort((x, y) => x.Loc - y.Loc);
                return rbookings;
            }
            throw new RepositoryException("Cursa nu a fost gasita!");
            
        }

        public Booking FindOne(KeyValuePair<Ride, Client> id)
        {
            throw new NotImplementedException();
        }

        public void Save(Booking e)
        {
            log.InfoFormat("Saving booking {0}", e);
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into Booking values (@rid,@cid,@placesW,@places)";
                IDbDataParameter prid = comm.CreateParameter();
                prid.ParameterName = "@rid";
                prid.Value = e.Id.Key.Id;
                comm.Parameters.Add(prid);
                IDbDataParameter pcid = comm.CreateParameter();
                pcid.ParameterName = "@cid";
                pcid.Value = e.Id.Value.Id;
                comm.Parameters.Add(pcid);
                IDbDataParameter pplacesW = comm.CreateParameter();
                pplacesW.ParameterName = "@placesW";
                pplacesW.Value = e.NrPlacesWanted;
                comm.Parameters.Add(pplacesW);
                IDbDataParameter pplaces = comm.CreateParameter();
                pplaces.ParameterName = "@places";
                pplaces.Value = e.Places;
                comm.Parameters.Add(pplaces);
                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("Rezervarea nu a fost adaugat!");
            }
        }

        public void Update(Booking e)
        {
            throw new NotImplementedException();
        }
    }
}

