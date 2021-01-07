using Lab12.repository;
using Lab12.validator;
using System;
using System.Collections.Generic;
using System.Text;

namespace Lab12.repository
{
    public class Repository<ID,E>:IRepository<ID,E> where E :class, HasID<ID>
    {
        private IDictionary<ID, E> map;
        private IValidator<E> validator;
        public Repository(IValidator<E> val)
        {
            this.validator = val;
            map = new Dictionary<ID,E>();
        }

        public virtual E Save(E e)
        {
            validator.Validate(e);
            if (map.ContainsKey(e.Id))
                return (E)null;
            map.Add(e.Id, e);
            return e;
        }

        public E FindOne(ID id)
        {
            if (map.ContainsKey(id))
                return map[id];
            return null;
        }

        public virtual E Delete(ID id)
        {
            E found = FindOne(id);
            if (found == null)
                return null;
            map.Remove(id);
            return found;
        }

        public IEnumerable<E> FindAll()
        {
            return map.Values;
        }

        public virtual E Update(E e)
        {
            validator.Validate(e);
            if (map.ContainsKey(e.Id))
            {
                map[e.Id] = e;
                return null;
            }
            return e;
        }
    }
}
