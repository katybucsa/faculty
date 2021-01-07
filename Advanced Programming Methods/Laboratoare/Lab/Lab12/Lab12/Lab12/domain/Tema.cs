using System;
using System.Collections.Generic;
using System.Text;
using Lab12.repository;

namespace Lab12.domain 
{
    public class Tema:HasID<int>
    {
        public int Id { get; set; }
        public string Descriere { get; set; }
        public int Deadline { get; set; }
        public int SaptPrimire { get; set; }

        public Tema(int nrTema,string descriere,int deadline,int saptPrimire)
        {
            this.Id = nrTema;
            this.Descriere = descriere;
            this.Deadline = deadline;
            this.SaptPrimire = saptPrimire;
        }

        public override string ToString()
        {
            return Id + "\t\t\t" + Descriere + "\t\t   " + Deadline + "\t\t\t" + SaptPrimire;
        }

        public override bool Equals(object obj)
        {
            if(obj is Tema)
            {
                Tema t = obj as Tema;
                return t.Id == this.Id;
            }
            return false;
        }
    }
}
