using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyModel
{
    public interface IHasID<ID>
    {
        ID Id { get; set; }
    }
}
