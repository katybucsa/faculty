using Practic.repository;
using Practic.service;
using Practic.ui;
using System;
using System.Collections.Generic;

namespace Practic
{
    class Program
    {
        static void Main(string[] args)
        { 
            
            FunctionarFileRepo frepo = new FunctionarFileRepo("D:\\A2S1\\MAP\\Laboratoare\\PracticC#\\Practic\\Practic\\data\\functionari.txt");
            SoferFileRepo srepo = new SoferFileRepo("D:\\A2S1\\MAP\\Laboratoare\\PracticC#\\Practic\\Practic\\data\\soferi.txt", frepo);
            AmendaFileRepo arepo = new AmendaFileRepo("D:\\A2S1\\MAP\\Laboratoare\\PracticC#\\Practic\\Practic\\data\\amenzi.txt",srepo);
            Service service = new Service(frepo,srepo,arepo);
            Ui ui = new Ui(service);
            ui.run();
        }
    }
}
