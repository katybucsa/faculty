using Practic.domain;
using Practic.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Practic.service
{
    public class Service
    {
        private IRepository<String,Functionar> frepo;
        private IRepository<String,Sofer> srepo;
        private IRepository<string, Amenda> arepo;

        public Service(IRepository<String, Functionar> arepo, IRepository<String, Sofer> crepo, IRepository<string,Amenda>corepo)
        {
            this.frepo = arepo;
            this.srepo = crepo;
            this.arepo = corepo;
        }
        
        //Sa se afiseze toti functionarii(nume,vechime), ord. descr. dupa vechimea in cadrul firmei
        public IEnumerable<object> DescrescatorVechime()
        {
            var map = from f in frepo.FindAll()
                      orderby f.Vechime descending
                      select new
                      {
                          Nume = f.Nume,
                          Vechime = f.Vechime
                      };
            return map;
        }

        //Sa se afiseze toti soferii(nume,categoriePermis) al caror permis a fost primit in anul 2018, ordonati alfabetic
        public IEnumerable<object> Permis2018()
        {
            var map = from s in srepo.FindAll()
                      where s.PrimitLaData.Year == 2018
                      orderby s.Nume
                      select new
                      {
                          Nume = s.Nume,
                          Categorie = s.Categorie
                      };
            return map;
        }

        //Sa se afiseze toti soferii(nume,vechime) care nu au primit nicio amenda.
        public IEnumerable<object> NuAreAmenda()
        {
            var map = from s in srepo.FindAll()
                      where s.AmenziPrimite.Count == 0
                      select new
                      {
                          Nume = s.Nume,
                          Vechime = s.Vechime
                      };
            return map;
        }

        //Sa se afiseze toate amenzile(idAmenda) ale soferilor cu permisul primit in 2019 si categorie C.
        public IEnumerable<object> ToateAmenzi()
        {
            var map = from a in arepo.FindAll()
                      join s in srepo.FindAll() on a.Sofer.Id equals s.Id
                      where s.PrimitLaData.Year == 2019 && s.Categorie == CategoriePermis.C
                      select new
                      {
                          IdAmenda = a.Id
                      };
            return map;
        }


        public IEnumerable<object> NumarAmenzi()
        {
            var map = from s in srepo.FindAll()
                      select new
                      {
                          Nume = s.Nume,
                          NumarAmenzi = s.AmenziPrimite.Count
                      };
            return map;
        }
    }
}
