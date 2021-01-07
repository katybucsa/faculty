using System;
using System.Collections.Generic;
using System.Text;

namespace Books.repository
{
    class RepoException:Exception
    { 
        public RepoException(string msg) : base(msg) { }
    }
}
