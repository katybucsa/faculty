using Lab5.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab5.repository
{
    public interface IClientRepository:IRepository<int,Client>
    {
        Client FindLastAdded();
    }
}
