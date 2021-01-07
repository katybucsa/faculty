using Pontaje.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Pontaje.domain
{
    public class Pontaj:IHasID<KeyValuePair<Angajat,Sarcina>>
    {
        public Pontaj(Angajat angajat, Sarcina sarcina, DateTime data)
        {
            Angajat = angajat;
            Sarcina = sarcina;
            Data = data;
        }

        public Angajat Angajat { get; set; }
        public Sarcina Sarcina { get; set; }
        public DateTime Data { get; set; }
        public KeyValuePair<Angajat, Sarcina> Id { get ; set; }

        public override bool Equals(object obj)
        {
            if (obj is Pontaj)
            {
                Pontaj a = obj as Pontaj;
                return a.Angajat == this.Angajat && a.Sarcina==this.Sarcina;
            }
            return false;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}|{2}", Angajat.Id,Sarcina.Id,Data);
        }
    }
}
