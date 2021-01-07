using Pontaje.domain;
using Pontaje.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Pontaje.service
{
    public class Service
    {
        private IRepository<String,Angajat> arepo;
        private IRepository<String,Sarcina> srepo;
        private IRepository<KeyValuePair<Angajat,Sarcina>, Pontaj> prepo;

        public Service(IRepository<String, Angajat> arepo, IRepository<String, Sarcina> srepo, IRepository<KeyValuePair<Angajat, Sarcina>, Pontaj> prepo)
        {
            this.arepo = arepo;
            this.srepo = srepo;
            this.prepo = prepo;
        }

        //Sa se afișeze lista de angajați, grupați după nivel. Mai întâi se vor afișa angajații care sunt juniori, 
        //apoi cei de nivel Medium, iar apoi cei care sunt de nivel Senior, in cadrul fiecărei grupe angajații 
        //se vor afișa ordonați descrescător după venitPeOra.
        public void ListaAngajati()
        {
            var map = from a in arepo.FindAll() group a by a.NivelAngajat into gr orderby gr.Key select gr;
            foreach (var gr in map)
                foreach (var a in gr.OrderByDescending(x => x.VenitPeOra))
                    Console.WriteLine(a);
        }

        //Sa se afișeze cate ore durează in medie (fiecare tip de sarcina(usoara, medie, grea).
        public void Medie()
        {
            var map = from s in srepo.FindAll()
                      group s by s.Dificultate into gr
                      select new
                      {
                          dif = gr.Key,
                          med = gr.Average(s => s.NrOreEstimate)
                      };
            foreach (var s in map)
                Console.WriteLine(s);
        }

        //Sa se afiseze primii 2 cei mai harnici angajați (nr de ore lucrate X venit pe ora ->maxim)
        public void VenitMaxim()
        {
            var map = from p in prepo.FindAll()
                      group p by p.Angajat into x
                      select new
                      {
                          Ang = x.Key,
                          Venit = x.Sum(y => y.Angajat.VenitPeOra * y.Sarcina.NrOreEstimate)
                      };
            foreach (var a in map.OrderByDescending(x => x.Venit).Take(2))
                Console.WriteLine(a);
        }

        //Oferiți utilizatorului posibilitatea generării unui raport care sa conțină  următoarele informații:
        //NumeleAngajatului, Nivel, Salar/Luna, afisandu-se mai intai angajatii Junior, apoi cei Medium , iar apoi cei Senior, 
        //crescător după salariul obținut, iar luna pentru care se afiseaza salariul se va citi de la tastatura.

        public void venitPeLuna(int luna)
        {
            var map = from p in prepo.FindAll()
                      where p.Data.Month == luna
                      group p by p.Angajat.NivelAngajat into x
                      select x;
            foreach(var gr in map)
            {
                var map2 = from p in gr
                           group p by p.Angajat into angajat
                           select new
                           {
                               Ang = angajat.Key,
                               Salariu = angajat.Sum(x => x.Angajat.VenitPeOra * x.Sarcina.NrOreEstimate)
                           };
                foreach (var a in map2.OrderBy(x => x.Salariu))
                    Console.WriteLine("Nume:{0},Nivel:{1},Salariu:{2}", a.Ang.Nume, a.Ang.NivelAngajat, a.Salariu);
            }
        }

    }
}
