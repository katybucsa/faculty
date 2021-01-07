using Lab4.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab4.domain
{
    class Client:IHasID<int>
    {
        Client(int id,string name)
        {
            Id = id;
            Name = name;
        }

        public int Id { get;set; }
        public string Name { get; set; }

    }
}
