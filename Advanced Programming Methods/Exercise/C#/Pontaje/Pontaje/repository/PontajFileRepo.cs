using Pontaje.domain;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Pontaje.repository
{
    public class PontajFileRepo:FileRepository<KeyValuePair<Angajat,Sarcina>,Pontaj>
    {
        IRepository<String, Angajat> arepo;
        IRepository<String, Sarcina> srepo;
        public PontajFileRepo(string file, IRepository<String, Angajat> arepo, IRepository<String, Sarcina> srepo) :base(file,null,null)
        {
            this.arepo = arepo;
            this.srepo = srepo;
        }

        protected override void ReadFromFile()
        {
            using (TextReader tr = File.OpenText(file))
            {
                string str;
                while ((str = tr.ReadLine()) != null)
                {
                    String[] list = str.Split("|");
                    bool v;
                    DateTime date;
                    if (list.Length == 3)
                    { 
                        v = DateTime.TryParse(list[2], out date);
                        if (!v)
                            throw new RepoException("Data invalida!\n");
                        Angajat a = arepo.FindAll().FirstOrDefault(x => x.Id == list[0]);
                        if (a==null)
                            throw new RepoException("Id angajat invalid!\n");
                        Sarcina s = srepo.FindAll().FirstOrDefault(x => x.Id == list[1]);
                        if (s == null)
                            throw new RepoException("Id sarcina invalid!\n");
                        Pontaj p = new Pontaj(a, s, date);
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
