using Books.domain;
using Books.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Books.service
{
    public class Service
    {
        private IRepository<String,Book> brepo;
        private IRepository<String,Client> crepo;
        private IRepository<KeyValuePair<Book,Client>, Rent> rrepo;

        public Service(IRepository<String, Book> arepo, IRepository<String, Client> srepo, IRepository<KeyValuePair<Book, Client>, Rent> prepo)
        {
            this.brepo = arepo;
            this.crepo = srepo;
            this.rrepo = prepo;
        }

        //Sa se afișeze lista de carti, grupate dupa tip,sortata descrescator dupa numele autorului si tip
       public void GroupByType()
        {
            List<Book> l = brepo.FindAll().ToList();
            l.Sort((x, y) => {
                if (x.Type.ToString().Equals(y.Type.ToString()))
                    return -x.Author.CompareTo(y.Author);
                else
                    return y.Type.ToString().CompareTo(x.Type.ToString());
            });
            foreach (var x in l)
                Console.WriteLine(x);
            /*var map = from b in brepo.FindAll()
                      group b by b.Type into gr orderby gr.Key descending select gr;
            foreach (var gr in map)
                foreach (var a in gr)
                    Console.WriteLine(a);*/
        }

        //sa se afiseze nr de carti care au fost inchiriate unui client al carui nume se da ca parametru in linia de comanda
        public void NrCarti(string nume)
        {
            var map = rrepo.FindAll().ToList();
            Console.WriteLine(map.Where(x => x.Client.Name.Equals(nume)).Count());
        }

        public void Mp3I(int year)
        {
            var map = from r in rrepo.FindAll()
                      where r.Data.Year == year
                      group r by r.Book into gr select new
                      {
                          Bk = gr.Key,
                          NRents = gr.Count()
                      };

            var map2 = rrepo.FindAll().ToList()
                        .Where(x => x.Data.Year != year)
                        .GroupBy(x => x.Book);
            foreach (var x in map)
                if (x.NRents <= 3)
                    Console.WriteLine(x.Bk);
            foreach (var x in map2)
                Console.WriteLine(x.FirstOrDefault().Book);
            foreach (var x in brepo.FindAll())
            {
                bool ok = true;
                foreach (var y in rrepo.FindAll())
                    if (x == y.Book)
                        ok = false;
                if (ok)
                    Console.WriteLine(x);
            }


        }

        //raport: numarul de carti inchiriate intr-o anumita perioada calendaristica 
        //carespunzator celor 3 tipuri de carti
        public void CartiPeTip(DateTime date1,DateTime date2)
        {
            var map = from r in rrepo.FindAll()
                      where DateTime.Compare(r.Data, date1) >= 0 && DateTime.Compare(date2, r.Data) >= 0
                      group r by r.Book.Type into gr
                      select new
                      {
                          T = gr.Key,
                          Nr = gr.Count()
                      };
            foreach (var x in map)
                Console.WriteLine("Tip:{0}, Inchirieri:{1}", x.T,x.Nr);
        }

    }
}
