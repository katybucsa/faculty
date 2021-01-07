using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using Lab12.domain;
using Lab12.validator;

namespace Lab12.repository
{
    public delegate String NotaToString(Nota n);

    public class FileNotaRepo : Repository<KeyValuePair<Student,Tema>, Nota>
    {
        private IRepository<int, Student> srepo;
        private NotaToString n2s;
        private IRepository<int, Tema> trepo;
        private string file;

        public FileNotaRepo(string file, NotaToString E2S, IValidator<Nota> v, IRepository<int, Student> srepo, IRepository<int, Tema> trepo) : base(v)
        {
            this.file = file;
            this.n2s = E2S;
            this.srepo = srepo;
            this.trepo = trepo;
            ReadFromFile();
        }

        private void ReadFromFile()
        {
            using (TextReader tr = File.OpenText(file))
            {
                string str;
                while ((str = tr.ReadLine()) != null)
                {
                    String[] list = str.Split("|");
                    bool v1, v2, v3, v4;
                    int ids, idt;
                    double grade;
                    DateTime date;
                    if (list.Length == 4)
                    {
                        v1 = int.TryParse(list[0], out ids);
                        if (!v1)
                            throw new Exception("Id student invalid!\n");
                        v2 = int.TryParse(list[1], out idt);
                        if (!v2)
                            throw new Exception("Numar tema invalid!\n");
                        v3 = double.TryParse(list[2], out grade);
                        if (!v3)
                            throw new Exception("nota invalida!\n");
                        v4 = DateTime.TryParse(list[3], out date);
                        Student s = srepo.FindOne(ids);
                        Tema t = trepo.FindOne(idt);
                        Nota nt = new Nota(s, t, grade, date);
                        if (v1 && v2 && v3)
                            base.map[nt.Id] = nt;
                    }
                    else
                        throw new RepoException("Linie incompleta!");
                }
            }
        }


        public void SaveToFile()
        {
            using (TextWriter tw = File.CreateText(file))
            {
                foreach (Nota e in FindAll())
                {
                    tw.WriteLine(n2s(e));
                }
            }
        }

        public override Nota Save(Nota e)
        {
            Nota entity = base.Save(e);
            if (entity != null)
                SaveToFile();
            return entity;
        }

        public override Nota Delete(KeyValuePair<Student,Tema> id)
        {
            Nota e = base.Delete(id);
            if (e != null)
                SaveToFile();
            return e;
        }

        public override Nota Update(Nota e)
        {
            Nota entity = base.Update(e);
            if (e == null)
                SaveToFile();
            return e;
        }
    }
}