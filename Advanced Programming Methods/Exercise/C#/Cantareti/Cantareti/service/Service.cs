using Cantareti.domain;
using Cantareti.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Cantareti.service
{
    public class Service
    {
        private IRepository<String,Artist> arepo;
        private IRepository<String,Cantaret> crepo;
        private IRepository<string, Concert> corepo;

        public Service(IRepository<String, Artist> arepo, IRepository<String, Cantaret> crepo, IRepository<string,Concert>corepo)
        {
            this.arepo = arepo;
            this.crepo = crepo;
            this.corepo = corepo;
        }
        //Sa se afiseze toti artistii(numele,varsta), ordonati descrescator dupa nume, grupati in doua categorii:
        //tinetei-cei care au varsta mai mica decat varsta medie si EXPERIMENTATI-restul
        public void DescrescatorNume()
        {
            var nr = 0;
            double vmed = 0;
            foreach (var a in arepo.FindAll())
            {
                vmed += a.Varsta;
                nr += 1;
            }
            vmed /= nr;
            var map = from a in arepo.FindAll()
                      select new
                      {

                      }
        }

        //Sa se afiseze toti cantaretii(numele,varsta,genMuzical) ordonati crescator
        //dupa gen muzical.
        public void CrescatorDupaGen()
        {
            var rez = from c in crepo.FindAll()
                      orderby c.Gen
                      select new
                      {
                          Nume = c.Nume,
                          Varsta = c.Varsta,
                          GenMuzical = c.Gen
                      };
            foreach (var c in rez)
                Console.WriteLine(c);
        }

        //Sa se afiseza toti cantaretii(nume,genMuzical) care nu au cantat inca la niciun concert.
        public void NuAuCantat()
        {
            var rez = crepo.FindAll().ToList()
                        .Where(x => corepo.FindAll().ToList().Where(y => y.Solist.Id.Equals(x.Id)).Count() == 0)
                        .ToList();
            var r = from c in rez
                    select new
                    {
                        Nume = c.Nume,
                        GenMuzical = c.Gen
                    };
            foreach (var x in r)
                Console.WriteLine(x);
        }

        //Sa se afiseze toate concertele de la un anumit gen muzical citit in consola.
        public void ToateGen(GenMuzical gen)
        {
            var rez = from co in corepo.FindAll()
                      join c in crepo.FindAll() on co.Id equals c.Id
                      where c.Gen == gen
                      select co;
            foreach (var x in rez)
                Console.WriteLine(x);
        }

        //Sa se afiseze toate concertele(NumeConcert) dintr-o anumita perioada calendaristica 
        //citita de la tastatura.
        public void ToateDinPerioada(DateTime date1,DateTime date2)
        {
            var rez = from c in corepo.FindAll()
                      where c.Data >= date1 && c.Data <= date2
                      select new
                      {
                          NumeConcert = c.Nume
                      };
            foreach (var x in rez)
                Console.WriteLine(x);
        }

    }
}
