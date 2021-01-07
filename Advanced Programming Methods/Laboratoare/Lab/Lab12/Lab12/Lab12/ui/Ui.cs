using Lab12.domain;
using Lab12.repository;
using Lab12.validator;
using System;
using System.Collections.Generic;
using System.Text;

namespace Lab12
{
    class Ui
    {
        private Service service;
        public Ui(Service service)
        {
            this.service = service;
        }

        private void PrintMeniu()
        {
            Console.WriteLine("    Studenti\t\t\t   Teme\t\t\t     Note\n");
            Console.WriteLine("1. Adauga student\t\t5. Adauga tema\t\t  8. Adauga nota\n" +
                              "2. Modifica student\t\t6. Afiseaza teme\t  9. Afiseaza note\n"+
                              "3. Sterge student\t\t7. Prelungeste termen\n4. Afiseaza studenti" );
            Console.WriteLine("0.  Exit\n");
            Console.WriteLine("Introduceti comanda: ");
        }

        private int ReadCmd()
        {
            int cmd;
            bool v = int.TryParse(Console.ReadLine(),out cmd);
            if(v)
                return cmd;
            return -1;
        }

        private int ReadInt(String mesaj)
        {
            Console.WriteLine(mesaj);
            int x;
            bool v = int.TryParse(Console.ReadLine(), out x);
            if (v)
                return x;
            throw new ValidationException("Introduceti un intreg!\n");
        }

        private string ReadString(String mesaj)
        {
            Console.WriteLine(mesaj);
            return Console.ReadLine();
        }

        private double ReadDouble(String mesaj)
        {
            Console.WriteLine(mesaj);
            double x;
            bool v = double.TryParse(Console.ReadLine(), out x);
            if (v)
                return x;
            throw new ValidationException("Introduceti un numar!\n");
        }


        private void Execute(int cmd){

            if (cmd == 1)
                AddStudent();
            else if (cmd == 2)
                UpdateStudent();
            else if (cmd == 3)
                DeleteStudent();
            else if (cmd == 4)
                AfiseazaStudenti();
            else if (cmd == 5)
                AddTema();
            else if (cmd == 6)
                AfiseazaTeme();
            else if (cmd == 7)
                PrelungesteTermen();
            else if (cmd == 8)
                AddNota();
            else if (cmd == 9)
                AfiseazaNote();
            else
                Console.WriteLine("Comanda invalida\n");
        }

        private void AddStudent()
        {
            int id, grupa;
            String nume, email, indrumator;
            id = ReadInt("Introduceti id student: ");
            nume = ReadString("Introduceti nume student: ");
            grupa = ReadInt("Introduceti grupa student: ");
            email = ReadString("Introduceti email student: ");
            indrumator = ReadString("Introduceti indrumator student: ");
            Student s = service.AddStudent(id, nume, grupa, email, indrumator);
            if (s == null)
                Console.WriteLine("Studentul cu datele " + s + " a fost actualizat cu succes!\n");
        else
                Console.WriteLine("Studentul a fost adaugat cu succes!\n");
        }



        private void UpdateStudent()
        {
            int id, grupa;
            String nume, email, indrumator;
            id = ReadInt("Introduceti id student: ");
            nume = ReadString("Introduceti nume student: ");
            grupa = ReadInt("Introduceti grupa student: ");
            email = ReadString("Introduceti email student: ");
            indrumator = ReadString("Introduceti indrumator student: ");
            Student s = service.UpdateStudent(id, nume, grupa, email, indrumator);
            if (s == null)
                Console.WriteLine("Studentul cu id-ul " + id + " nu exista in lista de studenti, iar acesta a fost adaugat!\n");
            else
                Console.WriteLine("Studentul a fost actualizat cu succes!\n");
        }


        private void DeleteStudent()
        {
            int id = ReadInt("Introduceti id-ul studentului pe care vreti sa il eliminati din catalog: ");
            Student s = service.DeleteStudent(id);
            if (s == null)
            Console.WriteLine("Studentul cu datele " + s + " a fost eliminat cu succes din catelog!\n");
            else
                Console.WriteLine("Nu exista studentul cu id-ul " + id + "\n");
        }


        private void AfiseazaStudenti()
        {
            IEnumerable<Student> students = service.FindAllStudents();
            Console.WriteLine("\n");
            Console.WriteLine("Id\t\tNume\t\tGrupa\t\tEmail\t\tIndrumator");
            foreach (Student st in students)
                Console.WriteLine(st);
            Console.WriteLine("\n");
        }



        private void AddTema()
        {
            int nr, deadline, sPrimire;
            String descriere;
            nr = ReadInt("Introduceti numarul temei: ");
            descriere = ReadString("Introduceti cerinta tema: ");
            deadline = ReadInt("Introduceti deadline tema: ");
            sPrimire = ReadInt("Introduceti numarul saptamanii de primire: ");
            Tema t = service.AddTema(nr, descriere, deadline, sPrimire);
            if (t == null)
                Console.WriteLine("Tema cu datele " + t + " a fost actualizata cu succes!\n");

            else
                Console.WriteLine("Tema a fost adaugata cu succes!\n");
        }


        private void AfiseazaTeme()
        {
            IEnumerable<Tema> teme = service.FindAllTeme();
            Console.WriteLine("\n");
            Console.WriteLine("Numar tema\t\tCerinta\t\tDeadline\t\tSaptamana primire");
            foreach (Tema t in teme)
                Console.WriteLine(t);
            Console.WriteLine("\n");
        }


        private void PrelungesteTermen()
        {
            int nr = ReadInt("Introduceti numarul temei pentru care doriti sa prelungiti deadline-ul: ");
            service.PrelungesteTermen(nr);
            Console.WriteLine("Deadline-ul temei cu numarul " + nr + " a fost prelungit cu o saptamana");
        }


        private void AddNota()
        {
            int idS = ReadInt("Introduceti id-ul studentului caruia ii dati nota:");
            int idT = ReadInt("Introduceti numarul temei pentru care dati nota:");
            double nota = ReadDouble("Introduceti nota:");
            DateTime date = DateTime.Now;
            double n = service.AddNota(idS, idT, nota, date);
            Console.WriteLine("Nota a fost adaugata cu succes!\nNota maxima pe care studentul o putea primi este: " + n + "\n");
        }


        private void AfiseazaNote()
        {
            IEnumerable<Nota> note = service.FindAllGrades();
            Console.WriteLine("IdStudent\t\t\tNrTema\t\t\tData");
            foreach(Nota n in note)
                Console.WriteLine(n);
            Console.WriteLine("\n");
        }


        public void run()
        {
            int cmd;
            while (true)
            {
                try
                {
                    PrintMeniu();
                    cmd = ReadCmd();
                    if (cmd == 0)
                        return;
                    if (cmd == -1)
                    {
                        Console.WriteLine("Comanda invalida!\n");
                        continue;
                    }
                    Execute(cmd);
                }catch(ValidationException ve)
                {
                    Console.WriteLine(ve.Message);
                }
                catch (RepoException re)
                {
                    Console.WriteLine(re.Message);
                }
            }

        }
    }
}