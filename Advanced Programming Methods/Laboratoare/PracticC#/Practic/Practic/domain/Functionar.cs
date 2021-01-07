using Practic.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Practic.domain
{
    public class Functionar:IHasID<string>
    {
        public Functionar(string id, string nume, double vechime)
        {
            Id = id;
            Nume = nume;
            Vechime = vechime;
        }

        public string Id { get; set; }
        public string Nume { get; set; }
        public double Vechime { get; set; }

        public override bool Equals(object obj)
        {
            var functionar = obj as Functionar;
            return functionar != null &&
                   Id == functionar.Id;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}|{2}", Id, Nume, Vechime);
        }
    }
}
