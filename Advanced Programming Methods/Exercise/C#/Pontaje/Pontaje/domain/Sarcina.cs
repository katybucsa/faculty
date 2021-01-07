using Pontaje.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Pontaje.domain
{
    public enum Dificulty
    {
        Usoara,Medie,Grea
    }
    public class Sarcina:IHasID<String>
    {
        public Sarcina(string id, Dificulty dificultate, int nrOreEstimate)
        {
            Id = id;
            Dificultate = dificultate;
            NrOreEstimate = nrOreEstimate;
        }

        public string Id { get; set; }
        public Dificulty Dificultate { get; set; }
        public int NrOreEstimate { get; set; }

        public override bool Equals(object obj)
        {
            if (obj is Sarcina)
            {
                Sarcina a = obj as Sarcina;
                return a.Id == this.Id;
            }
            return false;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}|{2}", Id, Dificultate, NrOreEstimate);
        }

    }
}
