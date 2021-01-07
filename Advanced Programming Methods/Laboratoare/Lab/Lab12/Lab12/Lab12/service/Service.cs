using System;
using System.Collections.Generic;
using System.Text;
using Lab12.domain;
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
            return DateTime.UtcNow.DayOfYear / 7 - 38;
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
    }
}