using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1.domain
{
    public class Concert : IHasID<String>
    {
        public string Id { get; set; }
        public Cantaret Solist { get; set; }
        public string Nume { get; set; }
        public DateTime Data { get; set; }
        //public DateTime Date { get; set; }
        public override string ToString()
        {

            //return Angajat.ToString() + " " + Sarcina.ToString() +" "+Date.ToString("d/M/yyyy", System.Globalization.CultureInfo.InvariantCulture);
            // return String.Format("{0} {1} {2}", Angajat, Sarcina, Date.ToString("d/M/yyyy", System.Globalization.CultureInfo.InvariantCulture));
            //return $"{Angajat} {Sarcina} {Date.ToString("d/M/yyyy", System.Globalization.CultureInfo.InvariantCulture)}"; //C#6

            return Id + " | " + Solist.ToString() + " | " + " | " + Data;
        }

        public Concert(string id, string nume, Cantaret solist, DateTime data)
        {
            Id = id;
            Solist = solist;
            Nume = nume;
            Data = data;
        }
    }
}
