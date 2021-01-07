using Books.domain;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Books.repository
{
    public class RentFileRepo:FileRepository<KeyValuePair<Book,Client>,Rent>
    {
        IRepository<String, Book> brepo;
        IRepository<String, Client> crepo;
        public RentFileRepo(string file, IRepository<String, Book> brepo, IRepository<String, Client> crepo) :base(file,null,null)
        {
            this.brepo = brepo;
            this.crepo = crepo;
            ReadFromFile();
        }

        protected override void ReadFromFile()
        {
            using (TextReader tr = File.OpenText(file))
            {
                string str;
                while ((str = tr.ReadLine()) != null)
                {
                    String[] list = str.Split(",");
                    bool v;
                    DateTime date;
                    if (list.Length == 3)
                    { 
                        v = DateTime.TryParse(list[2], out date);
                        if (!v)
                            throw new RepoException("Data invalida!\n");
                        Book a = brepo.FindAll().FirstOrDefault(x => x.Id == list[0]);
                        if (a==null)
                            throw new RepoException("Id carte invalid!\n");
                        Client s = crepo.FindAll().FirstOrDefault(x => x.Id == list[1]);
                        if (s == null)
                            throw new RepoException("Id client invalid!\n");
                        Rent p = new Rent(a, s, date);
                        if (v)
                            base.map[p.Id] = p;
                        else
                            throw new RepoException("Data invalida!\n");
                    }
                    else
                        throw new RepoException("Linie incompleta!");
                }
            }
        }
    }
}
