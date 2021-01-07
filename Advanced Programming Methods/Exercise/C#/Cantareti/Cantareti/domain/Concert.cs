using Cantareti.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Cantareti.domain
{
    public class Concert:IHasID<string>
    {
        public Concert(string id, string nume, Cantaret solist, DateTime data)
        {
            Id = id;
            Nume = nume;
            Solist = solist;
            Data = data;
        }

        public string Id { get; set; }
        public string Nume { get; set; }
        public Cantaret Solist { get; set; }
        public DateTime Data { get; set; }

        public override bool Equals(object obj)
        {
            var concert = obj as Concert;
            return concert != null &&
                   Id == concert.Id;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}|{2}|{3}", Id, Nume, Solist, Data);
        }
    }
}
