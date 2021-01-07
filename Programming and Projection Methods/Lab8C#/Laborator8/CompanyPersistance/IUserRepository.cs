using CompanyModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyPersistance
{
    public interface IUserRepository:IRepository<string,User>
    {
       User FindOne(string username, string password);
    }
}
