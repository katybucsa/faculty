using System;
using System.Collections.Generic;
using System.Text;

namespace Pontaje.repository
{
    class RepoException:Exception
    { 
        public RepoException(string msg) : base(msg) { }
    }
}
