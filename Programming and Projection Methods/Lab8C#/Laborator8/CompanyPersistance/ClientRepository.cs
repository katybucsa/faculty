using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CompanyModel;
using log4net;

namespace CompanyPersistance
{
    public class ClientRepository : IClientRepository
    {
        private static readonly ILog log = LogManager.GetLogger("RideRepository");
        IDictionary<String, String> props;
        public ClientRepository(IDictionary<String, String> props)
        {
            log.Info("Creating ClientRepository");
            this.props = props;
        }

        public Client FindOne(String name)
        {
            log.InfoFormat("Finding client with name {0}", name);
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from Client where name=@name";
                IDbDataParameter paramName = comm.CreateParameter();
                paramName.ParameterName = "@name";
                paramName.Value = name;
                comm.Parameters.Add(paramName);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        Client c = new Client(id, name);
                        return c;
                    }
                }
            }
            throw new RepositoryException("Client nu a fost gasit!");
        }


        public void Delete(int id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Client> FindAll()
        {
            throw new NotImplementedException();
        }

        public Client FindOne(int id)
        {
            throw new NotImplementedException();
        }

        public void Save(Client e)
        {
            log.InfoFormat("Saving client {0}", e);
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "insert into Client (name) values (@name)";
                IDbDataParameter paramName = comm.CreateParameter();
                paramName.ParameterName = "@name";
                paramName.Value = e.Name;
                comm.Parameters.Add(paramName);
                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No client added !");

                log.InfoFormat("Client inserted successfully");
            }
        }

        public Client FindLastAdded()
        {
            log.InfoFormat("Finding last client added");
            IDbConnection con = DBUtils.getConnection(props);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select max(client_id),name from Client";
                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        String name = dataR.GetString(1);
                        Client c = new Client(id, name);
                        return c;
                    }
                    log.ErrorFormat("Client couldn't be found in the database!");
                    throw new RepositoryException("Client cound't be set!");
                }
            }
        }

        public void Update(Client e)
        {
            throw new NotImplementedException();
        }
    }
}
