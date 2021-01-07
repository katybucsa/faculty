using Pontaje.repository;
using Pontaje.service;
using System;
using System.Collections.Generic;
using System.Text;

namespace Pontaje.ui
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
            Console.WriteLine("1. Lista angajati\n" +
                              "2. Medie\n" +
                              "3. Venit maxim, primii 2\n" +
                              "4.Venit pe luna");
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
                service.ListaAngajati();
            else if (cmd == 2)
                service.Medie();
            else if (cmd == 3)
                service.VenitMaxim();
            else if (cmd == 4)
                service.venitPeLuna(ReadInt("Introduceti luna"));
            else
                Console.WriteLine("Comanda invalida\n");
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
            }

        }

    }
}
