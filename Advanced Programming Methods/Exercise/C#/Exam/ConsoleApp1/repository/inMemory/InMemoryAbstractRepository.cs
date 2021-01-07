using ConsoleApp1.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.repository.inMemory
{
    public abstract class InMemoryAbstractRepository<ID,E>: IRepository<ID, E> where E:IHasID<ID>    {

        private IDictionary<ID, E> map;

        protected InMemoryAbstractRepository()
        {
            map = new Dictionary<ID, E>();
        }

        public IEnumerable<E> FindAll()
        {
            return map.Values;
        }

        protected E Save(E entity)
        {
            if (map.ContainsKey(entity.Id))
                return default(E);
            map.Add(entity.Id, entity);
            return entity;
        }

        public E FindOne(ID id)
        {
            if (map.ContainsKey(id))
                return map[id];
            return default(E);
        }
    }
}
