using System;
using System.Collections.Generic;
using System.Text;

namespace Pontaje.repository
{
    public interface IRepository<ID,E>
    {
        ICollection<E> FindAll();
    }
}
