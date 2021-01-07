using System;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Laborator3
{
    class Program
    {
        static void Main(string[] args)
        {
            Service s = new Service();
            Thread t1 = new Thread(s.UpdateTables1);
            Thread t2=new Thread(s.UpdateTables2);
            t1.Start();
            t2.Start();
            t1.Join();
            t2.Join();
        }
    }
}
