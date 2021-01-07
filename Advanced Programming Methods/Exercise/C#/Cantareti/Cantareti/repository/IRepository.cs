using System;
using System.Collections.Generic;
using System.Text;

namespace Cantareti.repository
{
    public interface IRepository<ID,E>
    {
        IEnumerable<E> FindAll();
    }
}
