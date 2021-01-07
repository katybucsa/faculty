using ConsoleApp1.domain;
using ConsoleApp1.repository;
using ConsoleApp1.repository.inMemory;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.utils
{
    public abstract class TemplateConvertor<Entitate1,ID1,Entitate2,ID2> where Entitate1:IHasID<ID1> where Entitate2:IHasID<ID2>
    {
        private static InMemoryAbstractRepository<ID1, Entitate1> repository1;
        private static InMemoryAbstractRepository<ID2, Entitate2> repository2;

        public static InMemoryAbstractRepository<ID1,Entitate1> Repository1
        {
            set
            {
                repository1 = value;
            }
        }

        public static Entitate1 GetFullEntitate1(ID1 id)
        {
            return repository1.FindOne(id);
        }

        public static InMemoryAbstractRepository<ID2, Entitate2> Repository2
        {
            set
            {
                repository2 = value;
            }
        }

        public static Entitate2 GetFullEntitate2(ID2 id)
        {
            return repository2.FindOne(id);
        }
    }
}
