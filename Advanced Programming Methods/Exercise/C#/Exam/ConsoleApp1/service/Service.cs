using ConsoleApp1.domain;
using ConsoleApp1.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.service
{
    public class Service
    {
        private IRepository<String, Artist> repoArtisti;
        private IRepository<String, Cantaret> repoCantareti;
        private IRepository<String, Concert> repoConcerte;

        public Service(IRepository<string, Artist> repoArtisti, IRepository<string, Cantaret> repoCantareti, IRepository<string, Concert> repoConcerte)
        {
            this.repoArtisti = repoArtisti;
            this.repoCantareti = repoCantareti;
            this.repoConcerte = repoConcerte;
        }

        public IEnumerable<object> Filtrare1()
        {
            //Artistii numele si varsta ordonati descrescator dupa nume
            double varsta = repoArtisti.FindAll().Average(artist => artist.Varsta);
            var map = from artist in repoArtisti.FindAll()
                      select new
                      {
                          NumeArtist = artist.Nume,
                          VarstaArtist = artist.Varsta,
                          Categorie = (artist.Varsta<=varsta) ? "TINEREL":"EXPERIMENTAT"
                      };
            var map2 = from artist in map
                       orderby artist.Categorie descending,artist.NumeArtist descending
                       select new
                       {
                           NumeArtist = artist.NumeArtist,
                           VarstaArtist = artist.VarstaArtist,
                           Categori=artist.Categorie
                       };
            return map2;
        }

        public List<Cantaret> Filtrare2()
        {
            //cantaretii ordonati descrescator dupa genul muzical
            var map = from cantaret in repoCantareti.FindAll()
                      orderby cantaret.Gen.ToString()
                      select cantaret;

           return map.ToList();
        }

        public List<Concert> Filtrare5(DateTime d1, DateTime d2)
        {
            var map = from concert in repoConcerte.FindAll()
                      where (concert.Data>=d1 && concert.Data<=d2)
                      select concert;
            return map.ToList();
        }

        public List<Cantaret> Filtrare3()
        {
            //cantaretii care nu au cantat inca la niciun concert
            var map = from cantaret in repoCantareti.FindAll()
                      where cantaret.ListaConcerte.Count == 0
                      select cantaret;
            return map.ToList();
        }

        public List<Concert> Filtrare4(string gen)
        {
            //concerte de un anumit gen muzical
            var map = from concert in repoConcerte.FindAll()
                      where concert.Solist.Gen.ToString() == gen
                      select concert;
            return map.ToList();
        }
    }
}
