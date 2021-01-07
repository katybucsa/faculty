using Practic.domain;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Practic.repository
{
    class SoferFileRepo : Repository<string,Sofer>
    {
        IRepository<String, Functionar> frepo;
        string file;
        public SoferFileRepo(string file, IRepository<String, Functionar> frepo) : base()
        {
            this.frepo = frepo;
            this.file = file;
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
                    if (list.Length == 3)
                    {
                        Functionar f = frepo.FindAll().FirstOrDefault(x => x.Id == list[0]);
                        if (f == null)
                            throw new RepoException("Id functionar invalid!\n");
                        CategoriePermis cat;
                        CategoriePermis.TryParse(list[1], out cat);
                        Sofer s = new Sofer(f.Id, f.Nume, f.Vechime, cat, DateTime.Parse(list[2]));
                        map[s.Id] = s;
                    }
                    else
                        throw new RepoException("Linie incompleta!");
                }
            }
        }
    }
}
