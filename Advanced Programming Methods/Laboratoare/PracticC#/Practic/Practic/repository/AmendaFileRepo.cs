using Practic.domain;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Practic.repository
{
    public class AmendaFileRepo:Repository<string,Amenda>
    {
        string file;
        IRepository<string, Sofer> srepo;
        public AmendaFileRepo(string file, IRepository<string, Sofer> srepo) :base()
        {
            this.file = file;
            this.srepo = srepo;
            ReadFromFile();
        }

        private void ReadFromFile()
        {
            using (TextReader tr = File.OpenText(file))
            {
                string str;
                while ((str = tr.ReadLine()) != null)
                {
                    String[] list = str.Split(",");
                    if (list.Length == 2)
                    {
                        Sofer s = srepo.FindAll().FirstOrDefault(x => x.Id == list[1]);
                        if (s == null)
                            throw new RepoException("Id sofer invalid!\n");
                        Amenda a = new Amenda(list[0], s);
                        List<Amenda> l = s.AmenziPrimite;
                        l.Add(a);
                        s.AmenziPrimite = l;
                        map[a.Id] = a;
                    }
                    else
                        throw new RepoException("Linie incompleta!");
                }
            }
        }
    }
}
