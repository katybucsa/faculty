using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Scris
{
    public class Felina
    {
        public  virtual String  showIdentity() {
            return "Felina";
            //Console.WriteLine("Felina");
        }
        public override string ToString()
        {
            return "Artist";
        }
    }
    public class Pisica : Felina
    {
        public override String showIdentity()
        {
            return "Pisica";
            //Console.WriteLine("Pisica");
        }
        public override string ToString()
        {
            return "Pisica";
        }
    }

    /*public class Pantera : Pisica
    {
        public override void showIdentity()
        {
            Console.WriteLine("Pantera");
        }
    }*/
}
