using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyPersistance
{
    public interface IRepository<ID, E>
    {
        void Save(E e);
        void Delete(ID id);
        void Update(E e);
        E FindOne(ID id);
        IEnumerable<E> FindAll();
    }
}
