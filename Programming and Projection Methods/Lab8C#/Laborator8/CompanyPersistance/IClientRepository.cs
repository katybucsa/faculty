using CompanyModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyPersistance
{
    public interface IClientRepository:IRepository<int,Client>
    {
        Client FindLastAdded();
    }
}
