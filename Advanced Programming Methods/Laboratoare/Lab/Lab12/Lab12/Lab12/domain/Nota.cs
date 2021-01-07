using System;
using System.Collections.Generic;
using System.Text;
using Lab12.repository;

namespace Lab12.domain
{
    public class Nota:HasID<KeyValuePair<Student,Tema>>
    {
        private Student Student;
        private Tema Tema;
 
        public Nota(Student s,Tema t,double n,DateTime d)
        {
            this.Student = s;
            this.Tema = t;
            this.Grade = n;
            this.Date = d;  
        }

        public KeyValuePair<Student,Tema> Id { get { return new KeyValuePair<Student,Tema>(Student, Tema); } set { Student = value.Key;Tema = value.Value; } }
        public double Grade { get; set; }
        public DateTime Date { get; set; }

        public override string ToString()
        {
            return Student.Id + "\t\t" + Tema.Id + "\t\t" + Grade + "\t\t" + Date;
        }

        public override bool Equals(object obj)
        {
            if(obj is Nota)
            {
                Nota n = obj as Nota;
                return n.Student == this.Student && n.Tema == this.Tema;
            }
            return false;
        }
    }
}
