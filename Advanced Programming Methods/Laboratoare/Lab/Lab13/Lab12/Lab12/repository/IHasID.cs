using System;
using System.Collections.Generic;
using System.Text;

namespace Lab12.repository
{
    public interface IHasID<ID>
    {
        ID Id { get; set; }
    }
}
