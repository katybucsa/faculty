using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompanyServices
{
    public class AppException:Exception
    {
        public AppException(String msg) : base(msg) { }
    }
}
