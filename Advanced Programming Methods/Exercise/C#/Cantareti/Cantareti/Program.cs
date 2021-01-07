using Cantareti.domain;
using Cantareti.repository;
using Cantareti.service;
using Cantareti.ui;
using Cantareti.util;
using System;
using System.Collections.Generic;

namespace Cantareti
{
    class Program
    {
        static void Main(string[] args)
        { 
            
            IRepository<String, Artist> arepo = new FileRepository<String,Artist>("D:\\A2S1\\MAP\\Exercise\\C#\\Cantareti\\Cantareti\\artisti.txt", S2E2S.StringToArtist, S2E2S.ArtistToString);
            IRepository<String, Cantaret> crepo = new CantaretFileRepo("D:\\A2S1\\MAP\\Exercise\\C#\\Cantareti\\Cantareti\\cantareti.txt",arepo);
            IRepository<String,Concert> corepo = new ConcertFileRepo("D:\\A2S1\\MAP\\Exercise\\C#\\Cantareti\\Cantareti\\concerte.txt",crepo);
            Service service = new Service(arepo, crepo, corepo);
            Ui ui = new Ui(service);
            ui.run();
        }
    }
}
