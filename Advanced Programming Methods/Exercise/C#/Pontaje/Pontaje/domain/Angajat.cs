using Pontaje.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Pontaje.domain
{
    public enum Nivel
    {
        Junior,Medium,Senior
    }

    public class Angajat:IHasID<String>
    {
        public Angajat(string id, string nume, float venitPeOra, Nivel nivelAngajat)
        {
            Id = id;
            Nume = nume;
            VenitPeOra = venitPeOra;
            NivelAngajat = nivelAngajat;
        }

        public String Id { get; set;}
        public String Nume { get; set; }
        public float VenitPeOra { get; set; }
        public Nivel NivelAngajat { get; set; }

        public override bool Equals(object obj)
        {
            if(obj is Angajat)
            {
                Angajat a = obj as Angajat;
                return a.Id == this.Id;
            }
            return false;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}|{2}|{3}", Id, Nume, VenitPeOra, NivelAngajat);
        }
    }
}
