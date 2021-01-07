using Cantareti.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Cantareti.domain
{
    public enum GenMuzical
    {
        Rock,Pop,Latino
    }
    public class Cantaret:Artist,IHasID<string>
    {
        public Cantaret(string id,string nume,double varsta,GenMuzical gen):base(id,nume,varsta)
        {
            Gen = gen;
        }

        public GenMuzical Gen { get; set; }

        public override string ToString()
        {
            return base.ToString() + string.Format("{0}", Gen);
        }
    }
}
