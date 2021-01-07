using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.domain
{
    public enum KnowledgeLevel
    {
        Junior = 1, Medium, Senior
    }
    public class Artist : IHasID<String>
    {
        public string Id { get; set; }
        public string Nume { get; set; }
        public double Varsta{ get; set; }

        public override string ToString()
        {
            return Id + " " + Varsta;
        }

        public Artist(string id,string nume, double varsta)
        {
            Id = id;
            Nume = nume;
            Varsta = varsta;
        }
    }
}
