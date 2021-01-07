using System;
using System.Collections.Generic;
using System.Text;

namespace Books.repository
{
    public interface IHasID<ID>
    {
        ID Id { get; set; }
    }
}
