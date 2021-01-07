using System;
using System.Collections.Generic;
using System.Text;

namespace Cantareti.repository
{
    public interface IHasID<ID>
    {
        ID Id { get; set; }
    }
}
