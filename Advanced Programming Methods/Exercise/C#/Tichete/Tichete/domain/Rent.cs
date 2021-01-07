using Books.repository;
using System;
using System.Collections.Generic;
using System.Text;

namespace Books.domain
{
    public class Rent:IHasID<KeyValuePair<Book,Client>>
    {
        public Rent(Book book, Client client, DateTime data)
        {
            Book = book;
            Client = client;
            Data = data;
            Id = new KeyValuePair<Book, Client>(book, client);
        }

        public Book Book { get; set; }
        public Client Client { get; set; }
        public DateTime Data { get; set; }
        public KeyValuePair<Book, Client> Id { get ; set; }

        public override bool Equals(object obj)
        {
            if (obj is Rent)
            {
                Rent a = obj as Rent;
                return a.Book == this.Book && a.Client==this.Client;
            }
            return false;
        }

        public override string ToString()
        {
            return string.Format("{0}|{1}|{2}", Book.Id,Client.Id,Data);
        }
    }
}
