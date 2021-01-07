using System;
using System.Collections.Generic;
using System.Text;

namespace Lab12.repository
{
    public interface IRepository<ID,E>
    {
        E Save(E e);
        E Delete(ID id);
        E Update(E e);
        E FindOne(ID id);
        IEnumerable<E> FindAll();
    }
}
