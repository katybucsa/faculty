using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.domain
{
    public enum GenMuzical { Rock,Pop,Latino }
    public class Cantaret : Artist
    {
        private List<Concert> lista;
        public GenMuzical Gen { get; set; }
        public List<Concert> ListaConcerte
        {
            get
            {
                return lista;
            }
        }

        public override string ToString()
        {
            return base.ToString() + " " + Gen + " " + lista;
        }

        public Cantaret(string id,string nume, double varsta,GenMuzical gen):base(id,nume,varsta)
        {
            Gen = gen;
            lista = new List<Concert>();
        }

        public void AddConcert(Concert concert)
        {
            lista.Add(concert);
        }
    }
}
