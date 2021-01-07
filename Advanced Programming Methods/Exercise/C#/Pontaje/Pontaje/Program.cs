using Pontaje.domain;
using Pontaje.repository;
using Pontaje.service;
using Pontaje.ui;
using Pontaje.util;
using System;
using System.Collections.Generic;

namespace Pontaje
{
    class Program
    {
        static void Main(string[] args)
        {
            IRepository<String, Angajat> arepo = new FileRepository<String,Angajat>("D:\\A2S1\\MAP\\Exercise\\Pontaje\\Pontaje\\angajati.txt", S2E2S.StringToAngajat, S2E2S.AngajatToString);
            IRepository<String, Sarcina> srepo = new FileRepository<String,Sarcina>("D:\\A2S1\\MAP\\Exercise\\Pontaje\\Pontaje\\sarcini.txt", S2E2S.StringToSarcina, S2E2S.SarcinaToString);
            IRepository<KeyValuePair<Angajat, Sarcina>, Pontaj> prepo = new PontajFileRepo("D:\\A2S1\\MAP\\Exercise\\Pontaje\\Pontaje\\pontaje.txt", arepo, srepo);
            Service service = new Service(arepo, srepo, prepo);
            Ui ui = new Ui(service);
            ui.run();
        }
    }
}
