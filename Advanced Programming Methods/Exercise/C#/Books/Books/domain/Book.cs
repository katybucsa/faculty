using Books.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Books.domain
{
    public enum Tip
    {
        fantasy,adventure,horror
    }

    public class Book:IHasID<String>
    {
        public Book(string id, string author, string nume, Tip type)
        {
            Id = id;
            Author = author;
            Nume = nume;
            Type = type;
        }

        public String Id { get; set;}
        public String Author { get; set; }
        public String Nume { get; set; }
        public Tip Type{ get; set; }

        public override bool Equals(object obj)
        {
            if(obj is Book)
            {
                Book a = obj as Book;
                return a.Id == this.Id;
            }
            return false;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}|{2}|{3}", Id, Author, Nume,Type);
        }
    }
}
