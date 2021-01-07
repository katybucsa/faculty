using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyModel
{
    [Serializable]
    public class Client:IHasID<int>
    {
        public Client(int id,string name)
        {
            Id = id;
            Name = name;
        }

        public int Id { get;set; }
        public string Name { get; set; }

    }
}
