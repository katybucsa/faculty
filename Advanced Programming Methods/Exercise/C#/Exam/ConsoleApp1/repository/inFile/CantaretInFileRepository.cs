using ConsoleApp1.domain;
using ConsoleApp1.utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.repository.inFile
{
    public class CantaretInFileRepository : InFileAbstractRepository<string, Cantaret>
    {
        private static Cantaret GetCantaret (string line)
        {
            String[] splits = line.Split(',');
            if (splits.Length != 2)
                return null;
            Artist artist = Convertor.GetFullEntitate1(splits[0]);
            return new Cantaret(artist.Id, artist.Nume, artist.Varsta, (GenMuzical)Enum.Parse(typeof(GenMuzical), splits[1]));
        }

        public CantaretInFileRepository(string fileN) : base(GetCantaret, fileN) { }
    }
}
