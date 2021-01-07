using ConsoleApp1.repository.inFile;
using ConsoleApp1.service;
using ConsoleApp1.utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {

        private static Service service;

        private static void Filtrare1()
        {
            service.Filtrare1().ToList().ForEach(artist =>
            {
                Console.WriteLine(artist);
            });
        }

        private static void Filtrare2()
        {
            Console.WriteLine("\nCantareti ordonati crescator dupa gen");
            service.Filtrare2().ForEach(cantaret =>
            {
                Console.WriteLine("Nume: {0} | Varsta: {1} | GenMuzical:{2}", cantaret.Nume,cantaret.Varsta,cantaret.Gen);
            });
        }

        private static void Filtrare3()
        {
            Console.WriteLine("\nCantareti care nu au cantat la niciun concert:");
            service.Filtrare3().ForEach(cantaret =>
            {
                Console.WriteLine("Nume: {0} | Gen: {1}", cantaret.Nume, cantaret.Gen);
            });
        }

        private static void Filtrare4()
        {
            Console.Write("\nGenul muzical=");
            string gen = Console.ReadLine();
            Console.WriteLine("\nConcerte de un anumit gen");
            service.Filtrare4(gen).ForEach(concert =>
            {
                Console.WriteLine("NumeConcert: {0} | Solist: {1}", concert.Nume, concert.Solist.Nume);
            });
        }

        private static void Filtrare5()
        {
            Console.Write("\nData de inceput=");
            string dataInceput = Console.ReadLine();
            Console.Write("\nData de sfarsit=");
            string dataSfarsit = Console.ReadLine();
            service.Filtrare5(DateTime.ParseExact(dataInceput, "d/M/yyyy", System.Globalization.CultureInfo.InvariantCulture), DateTime.ParseExact(dataSfarsit, "d/M/yyyy", System.Globalization.CultureInfo.InvariantCulture)).ForEach(concert => {
                Console.WriteLine("NumeConcert:{0} | NumeArtist:{1} | Data:{2}", concert.Nume,concert.Solist.Nume,concert.Data.ToString()); });

        }

        static void Main(string[] args)
        {
            ArtistInFileRepository repoArtisti = new ArtistInFileRepository(@"C:\Users\Andrei\Desktop\Exam C#\Exam\ConsoleApp1\data\artisti.txt");
            Convertor.Repository1 = repoArtisti;
            CantaretInFileRepository repoCantareti = new CantaretInFileRepository(@"C:\Users\Andrei\Desktop\Exam C#\Exam\ConsoleApp1\data\cantareti.txt");
            Convertor.Repository2 = repoCantareti;

            ConcertInFileRepository repoConcerte = new ConcertInFileRepository(@"C:\Users\Andrei\Desktop\Exam C#\Exam\ConsoleApp1\data\concerte.txt");

            service = new Service(repoArtisti, repoCantareti, repoConcerte);

            Filtrare1();
            Filtrare2();
            Filtrare3();
            Filtrare4();
            Filtrare5();
        }
    }
}
