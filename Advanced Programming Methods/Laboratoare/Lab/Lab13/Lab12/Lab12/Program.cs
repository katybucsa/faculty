using System;
using System.Collections.Generic;
using Lab12.domain;
using Lab12.repository;
using Lab12.validator;
using Lab12.util;

namespace Lab12
{
    class Program
    {
        static void Main(string[] args)
        {
            IRepository<int, Student> srepo = new FileRepository<int,Student>("D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab13\\Lab12\\Lab12\\data\\studenti.txt", S2E2S.StringToStudent, S2E2S.StudentToString, new StudentValidator());
            IRepository<int, Tema> trepo = new FileRepository<int, Tema>("D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab13\\Lab12\\Lab12\\data\\teme.txt", S2E2S.StringToTema, S2E2S.TemaToString, new TemaValidator());
            IRepository<KeyValuePair<Student, Tema>, Nota> nrepo = new FileNotaRepo("D:\\A2S1\\MAP\\Laboratoare\\Lab\\Lab13\\Lab12\\Lab12\\data\\catalog.txt", S2E2S.NotaToString,new NotaValidator(),srepo,trepo);
            Service service = new Service(srepo, trepo, nrepo);
            Ui ui = new Ui(service);
            ui.run();
           
        }
    }
}