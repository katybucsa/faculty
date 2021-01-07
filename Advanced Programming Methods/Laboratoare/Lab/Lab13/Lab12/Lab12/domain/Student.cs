using System;
using System.Collections.Generic;
using System.Text;
using Lab12.repository;

namespace Lab12.domain 
{
    public class Student:IHasID<int>
    {
        public int Id { get; set; }
        public string Nume { get; set; }
        public int Grupa { get; set; }
        public string Email { get; set; }
        public string Indrumator { get; set; }

        public Student(int id,string nume,int grupa, string email,string indrumator)
        {
            this.Id = id;
            this.Nume = nume;
            this.Grupa = grupa;
            this.Email = email;
            this.Indrumator = indrumator;
        }

        public override string ToString()
        {
            return Id + "\t\t" + Nume + "\t\t" + Grupa + "\t\t" + Email + "\t\t" + Indrumator;
        }

        public override bool Equals(object obj)
        {
            if( obj is Student)
            {
                Student s = obj as Student;
                return s.Id == this.Id;
            }
            return false;
        }
    }
}
