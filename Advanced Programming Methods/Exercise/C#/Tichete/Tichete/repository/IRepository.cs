﻿using System;
using System.Collections.Generic;
using System.Text;

namespace Books.repository
{
    public interface IRepository<ID,E>
    {
        ICollection<E> FindAll();
    }
}
