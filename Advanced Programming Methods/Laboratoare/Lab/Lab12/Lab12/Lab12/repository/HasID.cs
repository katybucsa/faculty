using System;
using System.Collections.Generic;
using System.Text;

namespace Lab12.repository
{
    public interface HasID<ID>
    {
        ID Id { get; set; }
    }
}
