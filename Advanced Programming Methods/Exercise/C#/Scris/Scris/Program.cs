using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

delegate string CustomDel(string s);
delegate bool Del(int x);
namespace Scris
{
    class Program
    {

        static void Main(string[] args)
        {
            /*int[] num = { 10, 15, 20, 25, 30, 35 };
            var res=from n in num 
                    group n by (n%10==0) into gr
                    select gr;
            foreach(IGrouping<bool,int> gr in res)
            {
                if (gr.Key == true)
                    Console.WriteLine("Div");
                else
                    Console.WriteLine("Not div");
                foreach (int nr in gr)
                    Console.WriteLine(nr);
            }
            /*string[] fr = { "Grape", "Orange", "Apple" };
            int[] am = { 1, 2, 3 };
            var rez = fr.SelectMany(f => am, (f, a) => new
            {
                Fruuit = f,
                Amount = a
            });
            foreach (var o in rez)
                Console.WriteLine(o.Fruuit + " " + o.Amount);
            /*var numbers = new int[] { 1, 2, 3, 4, 5 };
            var res = numbers.Aggregate((a, b)=>a * b);
            Console.WriteLine(res);
            /*List<String> vegetables = new List<string> { "Carrot", "Selleri" };
            var res = from v in vegetables select v;
            Console.WriteLine("Elements in vegetables array before add: " + res.Count());
            vegetables.Add("Broccoli");
            Console.WriteLine("Elements in vegetables array after add: " + res.Count());
            /*int[] numbers = { 1, 2, 3, 5, 2, 1, 2, 3, 6, 2, 2, 4, 1, 2, 1, 4, 6, 2, 4, 1, 2, 5, 7 };
            var result = from n in numbers
                         group n by n into g
                         select new
                         {
                             val = g.Key,
                             frecv = g.Count()
                         };
            result.ToList().ForEach(x => Console.WriteLine($"Valoare {x.val} :Frecventa {x.frecv}"));
            numbers.ToList()
                    .GroupBy(x => { Console.WriteLine(x); return x; })
                    .ToList()
                    .Select(gr =>
                    {
                        return new
                        {
                            Valoare = gr.Key,
                            Frecventa = gr.Count()
                        };
                    });*/
            /*string[] digits = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
          
            digits.ToList().Where((d, i) => digits.Length > i)
                 .ToList().ForEach(x => Console.WriteLine($"d {x}"+x));
                 */
           /* List<Felina> l = new List<Felina>();
            Pisica p = new Pisica();
            p.showIdentity();
            Felina f = new Felina();
            f.showIdentity();
            l.Add(p);
            l.Add(f);
            var res = l.Where(x => x.showIdentity().Equals("Pisica"));
            l.Add(new Pisica());
            res.ToList().ForEach(Console.WriteLine);
            //Pantera pan = new Pantera();
            //pan.showIdentity();
            
           // l.Add(pan);
            l.ForEach(x =>x.showIdentity());

           /* int i = int.MaxValue;
            Console.WriteLine(i);
            string s = i.ToString();
            string t = 10.ToString();
            int x = 10.CompareTo(9);
            Console.WriteLine(x);
            System.Int32 a = 8;
            object o = a;//boxing
            int ua = (int)o;*/

            //Console.WriteLine((int)Day.Mon);
           /* List<Day> l = new List<Day> { Day.Sun, Day.Mon, Day.Tue, Day.Wed, Day.Thu, Day.Fri, Day.Sat };
            var v = from x in l
                    orderby x ascending
                    select x;
            v.ToList().ForEach(y=>Console.WriteLine(y));
            /*int[] sir = new int[10];

            int[][] scores2 = new int[2][] {new int[] {92,93,94},
                                          new int[] {85,86,87,88}};
            Console.WriteLine(scores2.Rank);
            int[,,] t1 = new int[3, 4,5];
            Console.WriteLine(t1.Rank);


            NumberManipulator nr = new NumberManipulator();
            int a = 100, b = 200;
            nr.Swap(ref a, ref b);
            String str = "Unchanged";
            nr.changeStr(str);
            Console.WriteLine(str);
           // Console.WriteLine(a + "\n" + b);




            static string Hello(string s)
            {
                Console.WriteLine("Hello");
                return "Hello " + s;
            }
            static string Goodbye(string s)
            {
                Console.WriteLine("GoodBy");
                return "GoodBy " + s;
            }

            static bool Mare(int x)
            {
            return x > 100;
            }
            static bool Mic(int x)
            {
            return x < 1000;
            }
            static void Main(string[] args)
            {

            Del del = Mare;
            del += Mic;
            Console.WriteLine(del.Invoke(50));
                CustomDel del = Hello;
                //Console.WriteLine(del.Invoke("John"));
                del += (x) => "How are your? " + x;
                //del += Goodbye;
                Console.WriteLine(del.Invoke("John"));
            //}
            /*Imobil[] v = new Imobil[3];
            v[0] = new Casa("cj", 100, 500, "str");
            v[1] = new Imobil("sv", 10000);
            Console.WriteLine(v[0].Pret());
            Console.WriteLine(v[1].Pret());
            List<Imobil> list = new List<Imobil>();
            list.Add(new Casa("cj", 100, 500,"str"));
            list.Add(new Imobil("sv", 10000));
            list.ForEach(x => Console.WriteLine(x));
            //A a = new C(10);
            string[] strs = { "luni", "marti", "miercuri" };
            var chrs = from str in strs
                       let chrArray = str.ToCharArray()
                       from ch in chrArray
                       orderby ch
                       select ch;
            foreach (char c in chrs)
                Console.WriteLine(c + " ");
            int[] numbers = { 1, -2, -3, 5 };
            var x = from n in numbers
                    orderby n descending
                    select n * 4 / 2;

            foreach (int i in x)
                Console.Write(i + " ");
            int[] sir = new int[9];

            sir[0] = 2;
            sir[1] = 4;
            sir[2] = sir[0] * sir[1];

            foreach (var el in sir)
            {
                Console.Write(el +" ");
            }

            // AA a = new AA();


            ArrayList studentList = new ArrayList();
            studentList.Add(new Pers("B", "B"));
            studentList.Add("A");
            studentList.Add(new Pers("C", "C"));
            studentList.Sort();
            foreach (Object st in studentList)
            {
                Console.Write(st);
            }
            List<String> names = new List<String> { "AQ", "ZW" };
            var result = from v in names select v;
            names.Add("CK");
            result.ToList().ForEach(Console.Write);
            
            List<Circle> c = (new Circle[1] { new Circle() }).ToList();
            DrawShapes(c);
            Rectangle[] r = new Rectangle[1] { new Rectangle() };
            DrawShapes(r.ToList());*/

            Console.WriteLine(MyClass<Object>.Execute());

        }

       /* public enum Numb
        {
            1 10 6 4 8
        }*/

        static void DrawShapes<T>(List<T> shapes) where T: Shape
        {
            shapes.ForEach(x => x.Draw());
        }
    }

    public class MyException<T> : Exception
    {
        public MyException() { }
    }

    public class MyObj
    {
        public MyObj() { }
    }


    public class MyClass<T>
    {
        public static int Execute()
        {
            var x = 0;
            try
            {
                throw new MyException<Object>();
            }
            /*catch (MyException<T>)
            {
                x = 1;
            }*/
            catch (MyException<Object>)
            {
                x = 2;
            }
            catch (MyException<MyObj>)
            {
                x = 3;
            }
            return x;
        }
    }


    abstract class Shape
    {
        public abstract void Draw();
    }

    class Circle : Shape
    {
        public override void Draw()
        {
            Console.WriteLine("Circle");
        }
    }

    class Rectangle : Shape
    {
        public override void Draw()
        {
            Console.WriteLine("Rectangle");
        }
    }


    class Pers : IComparable
    {

        public string name;
        public string surname;

        public Pers(string name, string surname)
        {
            this.name = name;
            this.surname = surname;
        }
        public int CompareTo(object other) { return other.ToString().CompareTo(this.name); }

        public override string ToString() { return name + surname; }
    }



    class AA
    {
        private int V = 10;

        public AA()
        {
            //new BB();
            //new BB();
        }

        public int getV()
        {
            return V;
        }

        public class BB: AA
        {
        public BB()
        {
            V += 5;
        }
    }
}


class A
    {
        private int a;
        public A(int a)
        {
            this.a = Function1(a) - Function2(a);
            Console.WriteLine(this.a);
        }
        protected virtual int Function2(int a)
        {
            return a * 10;
        }
        protected virtual int Function1(int a)
        {
            return a * 10 + 1;
        }
    }
    class B : A
    {
        public B(int a) : base(a) { }
        protected override int Function1(int a)
        {
            return a * 10 + 2;
        }
        protected override int Function2(int a)
        {
            return a * 10 - 1;
        }
    }
    class C : B
    {
        public C(int a) : base(a) { }
        protected new int Function1(int a)
        {
            return a * 10 + 3;
        }
        protected new int Function2(int a)
        {
            return a * 10 - 2;
        }
    }


    public enum Day : byte
    {
        Sun=5,Mon,Tue,Wed,Thu=1,Fri, Sat
    }

    class NumberManipulator
    {
        public void Swap(ref int x,ref int y)
        {
            int temp = x;x = y;y = temp;
        }

        public void changeStr(String str)
        {
            str = "c";
        }
    }

    class Imobil
    {
        public double Suprafata { get; set; }
        public string Address { get; set; }
        public Imobil(string address,double sup)
        {
            this.Address = address;
            this.Suprafata = sup;
        }

        public virtual double Pret()
        {
            return 100 * Suprafata;
        }
        public override string ToString()
        {
            return Address + " " + Suprafata;
        }
    }

    class Casa : Imobil
    {
        public double SupTeren { get; set; }
        public string str { get; set; }
        public Casa(string adresa,double sup, double supteren,string s) : base(adresa, sup)
        {
            this.SupTeren = supteren;
            this.str = s;
        }

        //public override double Pret()
        public new double Pret()
        {
            return base.Pret() + SupTeren;
        }

        public override string ToString()
        {
            return base.ToString() + " " + SupTeren;
        }
    }
}
