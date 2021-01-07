using System;
using System.Collections.Generic;
using System.Text;

namespace Lab12.validator
{
    public class ValidationException:Exception
    {
        public  ValidationException(string msg) : base(msg) { }
    }
}
