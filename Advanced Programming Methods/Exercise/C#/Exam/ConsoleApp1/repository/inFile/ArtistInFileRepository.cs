using ConsoleApp1.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.repository.inFile
{
    public class ArtistInFileRepository : InFileAbstractRepository<String, Artist>
    {
        private static Artist GetAngajat(string line)
        {
            String[] splits = line.Split(',');
            if (splits.Length != 3)
                return null;
            return new Artist(splits[0], splits[1], Double.Parse(splits[2]));
        }
        public ArtistInFileRepository(string fileN) : base(GetAngajat, fileN: fileN) { }
    }
}
