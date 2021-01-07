using System;
using System.Collections.Generic;
using System.Text;
using Lab12.domain;
using System.Linq;
using Lab12.repository;
using Lab12.validator;

namespace Lab12
{
    public class Service
    {
        private IRepository<int, Student> srepo;
        private IRepository<int, Tema> trepo;
        private IRepository<KeyValuePair<Student,Tema>, Nota> nrepo;

        public Service(IRepository<int, Student> srepo, IRepository<int, Tema> trepo, IRepository<KeyValuePair<Student, Tema>, Nota> nrepo)
        {
            this.srepo = srepo;
            this.trepo = trepo;
            this.nrepo = nrepo;
        }


        public Student AddStudent(int id, String nume, int grupa, String email, String indrumator)
        {
            return srepo.Save(new Student(id, nume, grupa, email, indrumator));
        }

        public Student UpdateStudent(int id, String nume, int grupa, String email, String indrumator)
        {
            return srepo.Update(new Student(id, nume, grupa, email, indrumator));
        }

        public IEnumerable<Student> FindAllStudents()
        {
            return srepo.FindAll();
        }

        public Student DeleteStudent(int id)
        {
            return srepo.Delete(id);
        }

        public Tema AddTema(int nr, String descriere, int deadline, int sPrimire)
        {
            return trepo.Save(new Tema(nr, descriere, deadline, sPrimire));
        }

        public IEnumerable<Tema> FindAllTeme()
        {
            return trepo.FindAll();
        }



        private int GetCurrentWeek()
        {
            return DateTime.UtcNow.DayOfYear / 7 + 13;
        }

        private int GetWeekFromDate(DateTime date)
        {
            if (date.Year == 2018)
            {
                var sapt=date.DayOfYear / 7 - 39;
                return sapt;
            }
            var sap = date.DayOfYear / 7 + 13;
            return sap;
        }

        public void PrelungesteTermen(int id)
        {
            int week = GetCurrentWeek();
            Tema t = trepo.FindOne(id);
            if (t == null)
                throw new ValidationException("Tema inexistenta!\n");
            else if (week <= t.Deadline)
            {
                t.Deadline += 1;
                trepo.Update(t);
            }
            else
                throw new ValidationException("Numarul saptamanii curente a depasit deadline-ul!\n");
        }


        public double AddNota(int idS, int idT, double nota, DateTime date)
        {
            Tema t; Student s;
            if ((s = srepo.FindOne(idS)) == null)
                throw new ValidationException("Nu exista student cu id-ul " + idS + "\n");
            if ((t = trepo.FindOne(idT)) == null)
                throw new ValidationException("Nu exista tema cu numarul " + idT + "\n");
            int depunctari = GetCurrentWeek() - t.Deadline;
            if (depunctari < 0)
                depunctari = 0;
            if (depunctari > 2)
                throw new ValidationException("Tema nu mai poate fi predata!\n");
            double n = 10 - 2.5 * depunctari;
            nrepo.Save(new Nota(s, t, nota - 2.5 * depunctari, date));
            return n;
        }


        public IEnumerable<Nota> FindAllGrades()
        {
            return nrepo.FindAll();
        }

        //toti studentii dintr-o anumita grupa
        public IEnumerable<Student> TotiDinGrupa(int grupa)
        {
            return from s in srepo.FindAll()
                   where s.Grupa == grupa
                   select s;
        }

        //toate temele care trebuiau predate pana la saptamana data ca parametru, inclusiv
        public IEnumerable<Tema> TemeInainteDeSapt(int saptamana)
        {
            return from t in trepo.FindAll()
                   where t.Deadline <= saptamana
                   select t;
        }

        //toate notele care sunt >7
        public IEnumerable<Nota> ToateNoteleMaiMari7(int nr) {
            return from n in nrepo.FindAll()
                   where n.Grade > 7 && n.Id.Value.Id==nr
                   select n;

        }

        //toti studenti al caror nume incepe cu string-ul dat
        public IEnumerable<Student> StudentiAlf(string str)
        {
            return from s in srepo.FindAll()
                   where s.Nume.ToLower().StartsWith(str.ToLower())
                   select s;
        }

        //Nota la laborator pentru fiecare student (media ponderată a notelor de la temele de laborator; 
        //pondere tema=nr de săptămâni alocate temei).

        public IEnumerable<object> NotaLaborator()
        {
            var ponderi = trepo.FindAll().Sum(x => x.Deadline - x.SaptPrimire);
           return from n in nrepo.FindAll()
                      group n by n.Id.Key.Id into gr
                      select new
                      {
                          NumeStudent = srepo.FindOne(gr.Key).Nume,
                          Nota=gr.Sum(x=>x.Grade*(x.Id.Value.Deadline- x.Id.Value.SaptPrimire))/ponderi
                      };
        }

        //Cea mai grea tema: media notelor la tema respectivă este cea mai mică.
        public object CeaMaiGreaTema()
        {
            var map=from n in nrepo.FindAll()
                   group n by n.Id.Value into gr
                   select new
                   {
                       NrTema = gr.Key.Id,
                       Medie = gr.Average(x => x.Grade)
                   };
            return map.ToList().OrderBy(x => x.Medie).First();
        }


        //Studenții care pot intra în examen (media mai mare sau egală cu 4).
        public IEnumerable<object> PotIntraInExamen()
        {
            var ponderi = trepo.FindAll().Sum(x => x.Deadline - x.SaptPrimire);
            var map = from n in nrepo.FindAll()
                      group n by n.Id.Key.Id into gr
                      select new
                      {
                          NumeStudent = srepo.FindOne(gr.Key).Nume,
                          Nota = gr.Sum(x => x.Grade * (x.Id.Value.Deadline - x.Id.Value.SaptPrimire)) / ponderi
                      };
            return from s in map
                   where s.Nota >= 4
                   select s;
        }

        //Studenții care au predat la timp toate temele.
        public IEnumerable<object> PredatLaTimp()
        {
            return from nota in nrepo.FindAll()
                    join stud in srepo.FindAll() on nota.Id.Key equals stud
                    where GetWeekFromDate(nota.Date) <= nota.Id.Value.Deadline
                    group nota by nota.Id.Key into gr
                    where gr.Count() == trepo.FindAll().Count()
                    select gr.Key;
        }
    }
}