using System;
using System.Collections.Generic;
using System.Text;

namespace Cantareti.repository
{
    public class Repository<ID,E>:IRepository<ID,E> where E :class, IHasID<ID>
    {
        protected IDictionary<ID, E> map;
        public Repository()
        {
            map = new Dictionary<ID,E>();
        }

        public virtual E Save(E e)
        {
            if (map.ContainsKey(e.Id))
                return (E)null;
            map.Add(e.Id, e);
            return e;
        }

        public IEnumerable<E> FindAll()
        {
            return map.Values;
        }
    }
}
