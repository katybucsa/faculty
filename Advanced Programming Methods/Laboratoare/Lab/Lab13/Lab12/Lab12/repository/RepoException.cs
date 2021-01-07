using System;
using System.Collections.Generic;
using System.Text;

namespace Lab12.repository
{
    class RepoException:Exception
    { 
        public RepoException(string msg) : base(msg) { }
    }
}
