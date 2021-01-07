using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab5.domain;
using log4net;

namespace Lab5.repository
{
    public class UserRepository:IUserRepository
    {
        private static readonly ILog log = LogManager.GetLogger("UserRepository");
        IDictionary<String, String> props;
        public UserRepository(IDictionary<String, String> props)
        {
            log.Info("Creating RideRepository");
            this.props = props;
        }

        public void Delete(string id)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<User> FindAll()
        {
            throw new NotImplementedException();
        }

        public User FindOne(string id)
        {
            log.InfoFormat("Finding User with id {0}", id);
            IDbConnection con = DBUtils.getConnection(props);

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from User where username=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        String username = dataR.GetString(0);
                        String password = dataR.GetString(1);
                        String name = dataR.GetString(2);
                        User user = new User(username,password,name);
                        log.InfoFormat("Exiting findOne with value {0}", user);
                        return user;
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            throw new RepositoryException("User inexietent!");
        }

        public User FindOne(string username, string password)
        {
            User u = FindOne(username);
            if (password.Equals(u.Password))
                return u;
            throw new RepositoryException("Parola introdusă este incorectă!");
        }

        public void Save(User e)
        {
            throw new NotImplementedException();
        }

        public void Update(User e)
        {
            throw new NotImplementedException();
        }
    }
}
