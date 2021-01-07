using System;
using System.Collections.Generic;
using Lab12.domain;
using Lab12.repository;
using Lab12.validator;

namespace Lab12
{
    class Program
    {
        static void Main(string[] args)
        {
            IRepository<int, Student> srepo = new StudentRepo(new StudentValidator());
            IRepository<int, Tema> trepo = new TemaRepo(new TemaValidator());
            IRepository<KeyValuePair<Student, Tema>, Nota> nrepo = new NotaRepo(new NotaValidator());
            Service service = new Service(srepo, trepo, nrepo);
            Ui ui = new Ui(service);
            ui.run();
        }
    }
}
