using Cantareti.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Cantareti.domain
{
    public class Artist:IHasID<string>
    {
        public Artist(string id, string nume, double varsta)
        {
            Id = id;
            Nume = nume;
            Varsta = varsta;
        }

        public string Id { get; set; }
        public string Nume { get; set; }
        public double Varsta { get; set; }

        public override bool Equals(object obj)
        {
            var artist = obj as Artist;
            return artist != null &&
                   Id == artist.Id;
        }

        public override string ToString() 
        {
            return string.Format("{0}|{1}|{2}", Id, Nume, Varsta);
        }
    }
}
