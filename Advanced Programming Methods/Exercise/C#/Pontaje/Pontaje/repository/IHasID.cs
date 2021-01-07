using System;
using System.Collections.Generic;
using System.Text;

namespace Pontaje.repository
{
    public interface IHasID<ID>
    {
        ID Id { get; set; }
    }
}
