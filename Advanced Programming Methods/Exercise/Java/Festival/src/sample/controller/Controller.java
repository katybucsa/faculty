package sample.controller;

import sample.domain.Locatie;
import sample.domain.PiesaTeatru;
import sample.domain.Spectacol;
import sample.service.Service;

import java.util.List;
import java.util.Map;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }


    public Map<PiesaTeatru, List<Spectacol>> getPieseLocatiiMap(){
        return service.getPieseLocatiiMap();
    }

    public Map<PiesaTeatru,Long> getNrSpectacole(){
        return service.getNrSpectacole();
    }

    public Map<Locatie,List<Spectacol>> getProgramFestival(){
        return service.getProgramFestival();
    }

    public Map<PiesaTeatru,Integer> getPieseNrBileteVandute(){
        return service.getPieseNrBileteVandute();
    }
}
