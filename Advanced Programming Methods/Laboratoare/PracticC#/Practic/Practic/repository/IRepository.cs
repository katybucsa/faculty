using System;
using System.Collections.Generic;
using System.Text;

namespace Practic.repository
{
    public interface IRepository<ID,E>
    {
        IEnumerable<E> FindAll();
    }
}
