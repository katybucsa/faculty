using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using Lab12.repository;
using Lab12.validator;

namespace Lab12.repository
{
    public delegate String EntityToString<E>(E e);
    public delegate E StringToEntity<E>(String s);
    public class FileRepository<ID,E>:Repository<ID,E> where E: class,IHasID<ID>
    {
        protected string file;
        protected StringToEntity<E> S2E;
        private EntityToString<E> E2S;
        public FileRepository(string file, StringToEntity<E> S2E,EntityToString<E> E2S,IValidator<E> val) : base(val)
        {
            this.file = file;
            this.S2E = S2E;
            this.E2S = E2S;
            ReadFromFile();
        }

        protected virtual void ReadFromFile()
        {
            using (TextReader tr = File.OpenText(file))
            {
                string line;
                while ((line = tr.ReadLine()) != null)
                {
                    E e = S2E(line);
                    if (e != null)
                        base.Save(e);
                    else
                        throw new RepoException("Linie incompleta!");
                }
            }
        }
        public void SaveToFile()
        {
            using (TextWriter tw = File.CreateText(file))
            {
                foreach(E e in FindAll())
                {
                    tw.WriteLine(E2S(e));
                }
            }
        }

        public override E Save(E e)
        {
            E entity = base.Save(e);
            if (entity != null)
                SaveToFile();
            return entity;
        }

        public override E Delete(ID id)
        {
            E e = base.Delete(id);
            if (e != null)
                SaveToFile();
            return e;
        }

        public override E Update(E e)
        {
            E entity = base.Update(e);
            if (e == null)
                SaveToFile();
            return e;
        }
    }
}
