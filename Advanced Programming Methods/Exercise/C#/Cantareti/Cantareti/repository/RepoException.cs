using System;
using System.Collections.Generic;
using System.Text;

namespace Cantareti.repository
{
    class RepoException:Exception
    { 
        public RepoException(string msg) : base(msg) { }
    }
}
