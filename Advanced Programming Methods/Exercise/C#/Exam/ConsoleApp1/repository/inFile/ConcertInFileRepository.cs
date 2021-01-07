using ConsoleApp1.domain;
using ConsoleApp1.utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.repository.inFile
{
    /*public class ConcertInFileRepository : InFileAbstractRepository<String, Concert>
    {
        private static Concert GetConcert(string line)
        {
            String[] splits = line.Split(',');
            if (splits.Length != 4)
                return null;
            //return new Concert(Convertor.GetFullEntitate1(splits[0]), Convertor.GetFullEntitate2(splits[1]), DateTime.ParseExact(splits[2], "d/M/yyyy", System.Globalization.CultureInfo.InvariantCulture));
            var solist = Convertor.GetFullEntitate2(splits[2]);
            var concert = new Concert(splits[0], splits[1], solist, DateTime.ParseExact(splits[3],"d/M/yyyy",System.Globalization.CultureInfo.InvariantCulture));
            solist.AddConcert(concert);
            return concert;
        }
        public ConcertInFileRepository(string fileN) : base(GetConcert, fileN) { }
    }*/
    public class ConcertInFileRepository: IRepository<String, Concert>
    {
        private List<Concert> lista = new List<Concert>();
        private string fileN;

        public ConcertInFileRepository(string fileN)
        {
            this.fileN = fileN;
            ReadFromFile();
        }

        private static Concert GetConcert(string line)
        {
            String[] splits = line.Split(',');
            if (splits.Length != 4)
                return null;
            //return new Concert(Convertor.GetFullEntitate1(splits[0]), Convertor.GetFullEntitate2(splits[1]), DateTime.ParseExact(splits[2], "d/M/yyyy", System.Globalization.CultureInfo.InvariantCulture));
            var solist = Convertor.GetFullEntitate2(splits[2]);
            var concert = new Concert(splits[0], splits[1], solist, DateTime.ParseExact(splits[3], "d/M/yyyy", System.Globalization.CultureInfo.InvariantCulture));
            solist.AddConcert(concert);
            return concert;
        }

        public IEnumerable<Concert> FindAll()
        {
            return lista;
        }

        private void ReadFromFile()
        {
            using (TextReader tr = File.OpenText(fileN))
            {
                String line;
                while ((line = tr.ReadLine()) != null)
                {
                    Concert entity = GetConcert (line);
                    if (entity != null)
                        lista.Add(entity);
                }
            }
        }
    }
}
