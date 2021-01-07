using Books.domain;
using Books.repository;
using Books.service;
using Books.ui;
using Books.util;
using System;
using System.Collections.Generic;

namespace Books
{
    class Program
    {
        static void Main(string[] args)
        { 
            
            IRepository<String, Book> brepo = new FileRepository<String,Book>("D:\\A2S1\\MAP\\Exercise\\C#\\Books\\Books\\books.txt", S2E2S.StringToBook, S2E2S.BookToString);
            IRepository<String, Client> crepo = new FileRepository<String,Client>("D:\\A2S1\\MAP\\Exercise\\C#\\Books\\Books\\clients.txt", S2E2S.StringToClient, S2E2S.ClientToString);
            IRepository<KeyValuePair<Book,Client>, Rent> rrepo = new RentFileRepo("D:\\A2S1\\MAP\\Exercise\\C#\\Books\\Books\\rent.txt", brepo, crepo);
            Service service = new Service(brepo, crepo, rrepo);
            Ui ui = new Ui(service);
            ui.run();
        }
    }
}
