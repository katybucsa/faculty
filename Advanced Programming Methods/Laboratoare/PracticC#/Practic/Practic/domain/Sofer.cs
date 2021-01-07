using Practic.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Practic.domain
{
    public enum CategoriePermis
    {
        A,B,C
    }
    public class Sofer:Functionar,IHasID<string>
    {
        public Sofer(string id,string nume, double vechime,CategoriePermis categirie, DateTime primitLaData):base(id,nume,vechime)
        {
            Categorie = categirie;
            PrimitLaData = primitLaData;
            AmenziPrimite = new List<Amenda>();
        }

        public CategoriePermis Categorie { get; set; }
        public DateTime PrimitLaData { get; set; }
        public List<Amenda> AmenziPrimite { get; set; }

        public override string ToString()
        {
            return base.ToString() + string.Format("{0}|{1}", Categorie, PrimitLaData);
        }
    }
}
