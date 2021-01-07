using ConsoleApp1.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.repository
{
    public interface IRepository<ID,E> where E:IHasID<ID>
    {
        IEnumerable<E> FindAll();
    }
}
