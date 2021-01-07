using Practic.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Practic.domain
{
    public class Amenda:IHasID<string>
    {
        public Amenda(string id, Sofer sofer)
        {
            Id = id;
            Sofer = sofer;
        }

        public string Id { get; set;}
       public Sofer Sofer { get; set; }

        public override bool Equals(object obj)
        {
            var amenda = obj as Amenda;
            return amenda != null &&
                   Id == amenda.Id;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}", Id,Sofer.Id);
        }
    }
}
