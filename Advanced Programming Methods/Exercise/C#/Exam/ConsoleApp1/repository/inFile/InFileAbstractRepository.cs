using ConsoleApp1.domain;
using ConsoleApp1.repository.inMemory;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.repository.inFile
{
    public delegate E getEntity<E>(string s);

    public abstract class InFileAbstractRepository<ID,E>:InMemoryAbstractRepository<ID,E> where E:IHasID<ID>
    {
        private getEntity<E> functieStringToEntity;
        private string fileN;

        protected InFileAbstractRepository(getEntity<E> functieStringToEntity, string fileN):base()
        {
            this.functieStringToEntity = functieStringToEntity;
            this.fileN = fileN;
            ReadFromFile();
        }

        private void ReadFromFile()
        {
            using (TextReader tr = File.OpenText(fileN))
            {
                String line;
                while ((line = tr.ReadLine()) != null)
                {
                    E entity = functieStringToEntity(line);
                    if (entity != null)
                        base.Save(entity);
                }
            }
        }
    }
}
