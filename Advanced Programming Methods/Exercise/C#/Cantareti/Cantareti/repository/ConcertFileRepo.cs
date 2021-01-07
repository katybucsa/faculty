using Cantareti.domain;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Cantareti.repository
{
    class ConcertFileRepo : FileRepository<string, Concert>
    {
        IRepository<String, Cantaret> crepo;
        public ConcertFileRepo(string file, IRepository<String, Cantaret> crepo) : base(file, null, null)
        {
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
                    string err = null;
                    String[] list = str.Split(",");
                    bool v;
                    DateTime data;
                    if (list.Length == 4)
                    {
                        v = DateTime.TryParse(list[3], out data);
                        if (!v)
                            err += "Data invalida!\n";
                        Cantaret c = crepo.FindAll().FirstOrDefault(x => x.Id == list[0]);
                        if (c == null)
                            err += "Id cantaret invalid!\n";
                        if (err != null)
                            throw new RepoException(err);
                        map[list[0]] = new Concert(list[0], list[1], c, data);
                    }
                    else
                        throw new RepoException("Linie incompleta!");
                }
            }
        }
    }
}
