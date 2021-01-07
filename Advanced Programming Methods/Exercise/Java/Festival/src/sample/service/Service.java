package sample.service;

import javafx.util.Pair;
import sample.domain.Locatie;
import sample.domain.PiesaTeatru;
import sample.domain.Spectacol;
import sample.repository.RepositoryInterface;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

public class Service {
    private RepositoryInterface<String, PiesaTeatru> prepo;
    private RepositoryInterface<String, Locatie> lrepo;
    private RepositoryInterface<Pair<PiesaTeatru, Locatie>, Spectacol> srepo;


    public Service(RepositoryInterface<String, PiesaTeatru> prepo, RepositoryInterface<String, Locatie> lrepo, RepositoryInterface<Pair<PiesaTeatru, Locatie>, Spectacol> srepo) {
        this.prepo = prepo;
        this.lrepo = lrepo;
        this.srepo = srepo;
    }

    public Map<PiesaTeatru, List<Spectacol>> getPieseLocatiiMap() {
        return srepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(Spectacol::getPiesa, toList()));
    }

    public Map<PiesaTeatru, Long> getNrSpectacole() {
        return srepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(Spectacol::getPiesa, Collectors.counting()));
    }

    public Map<Locatie,List<Spectacol>> getProgramFestival(){
        return srepo.findAll()
                .stream()
                .sorted((x,y)->x.getOra()-y.getOra())
                .collect(Collectors.groupingBy(Spectacol::getLocatie,Collectors.toList()));
    }

    public Map<PiesaTeatru,Integer> getPieseNrBileteVandute(){
        return  srepo.findAll()
                .stream()
                .collect(Collectors.groupingBy(Spectacol::getPiesa,summingInt(Spectacol::getBileteVandute)));
    }
}
