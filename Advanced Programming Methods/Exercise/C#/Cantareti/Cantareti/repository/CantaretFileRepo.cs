using Cantareti.domain;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Cantareti.repository
{
    public class CantaretFileRepo:FileRepository<string,Cantaret>
    {
        IRepository<String, Artist> arepo;
        public CantaretFileRepo(string file, IRepository<String, Artist> arepo) :base(file,null,null)
        {
            this.arepo = arepo;
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
                    GenMuzical gen;
                    if (list.Length == 2)
                    {
                        string err = null;
                        v = GenMuzical.TryParse(list[1], out gen);
                        if (!v)
                            err += "Gen muzical invalid!\n";
                        Artist a = arepo.FindAll().FirstOrDefault(x=>x.Id==list[0]);
                        if (a == null)
                            err += "Id artist invalid\n";
                        if(err!=null)
                            throw new RepoException(err);
                        map[a.Id] = new Cantaret(a.Id, a.Nume, a.Varsta, gen);
                    }
                    else
                        throw new RepoException("Linie incompleta!");
                }
            }
        }
    }
}
