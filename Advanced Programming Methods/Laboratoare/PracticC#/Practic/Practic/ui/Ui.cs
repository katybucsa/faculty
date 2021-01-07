using Practic.domain;
using Practic.repository;
using Practic.service;
using System;
using System.Collections.Generic;
using System.Text;

namespace Practic.ui
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
            Console.WriteLine("1. Descr. vechime\n" +
                              "2. Permis 2018\n" +
                              "3. Nu are amenda\n" +
                              "4. Permis 2019, categ. C\n"+
                              "5. Numar amenzi primite\n");
            Console.WriteLine("0.  Exit\n");
            Console.WriteLine("Introduceti comanda: ");
        }

        private int ReadCmd()
        {
            int cmd;
            bool v = int.TryParse(Console.ReadLine(), out cmd);
            if (v)
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
            throw new RepoException("Introduceti un intreg!\n");
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
            throw new RepoException("Introduceti un numar!\n");
        }


        private void Execute(int cmd)
        {

            if (cmd == 1)
                DescrescatorVechime();
            else if (cmd == 2)
                Permis2018();
            else if (cmd == 3)
                NuAreAmenda();
            else if (cmd == 4)
                ToateAmenzi();
            else if (cmd == 5)
                NumarAmenzi();
            else
                Console.WriteLine("Comanda invalida\n");
        }

        public void DescrescatorVechime()
        {
            foreach (var x in service.DescrescatorVechime())
                Console.WriteLine(x);
        }

        public void Permis2018()
        {
            foreach (var x in service.Permis2018())
                Console.WriteLine(x);
        }

        public void NuAreAmenda()
        {
            foreach (var x in service.NuAreAmenda())
                Console.WriteLine(x);
        }

        public void ToateAmenzi()
        {
            foreach (var x in service.ToateAmenzi())
                Console.WriteLine(x);
        }

        public void NumarAmenzi()
        {
            foreach (var x in service.NumarAmenzi())
                Console.WriteLine(x);
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
                }
                catch (RepoException re)
                {
                    Console.WriteLine(re.Message);
                }
                catch(FormatException fe)
                {
                    Console.WriteLine(fe.Message);
                }
            }

        }

    }
}
