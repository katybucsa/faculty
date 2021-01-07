using Practic.domain;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace Practic.repository
{
    public class FunctionarFileRepo:Repository<string,Functionar>
    {
        string file;
        public FunctionarFileRepo(string file):base()
        {
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
                        Functionar f = new Functionar(list[0], list[1], double.Parse(list[2]));
                        map[f.Id] = f;
                    }
                    else
                        throw new RepoException("Linie incompleta!");
                }
            }
        }
    }
}
