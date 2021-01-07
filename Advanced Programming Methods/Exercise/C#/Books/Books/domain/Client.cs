using Books.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Books.domain
{
    public class Client:IHasID<String>
    {
        public Client(string id, string name)
        {
            Id = id;
            Name = name;
        }

        public string Id { get; set; }
        public string Name { get; set; }

        public override bool Equals(object obj)
        {
            if (obj is Client)
            {
                Client a = obj as Client;
                return a.Id == this.Id;
            }
            return false;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}", Id, Name);
        }

    }
}
