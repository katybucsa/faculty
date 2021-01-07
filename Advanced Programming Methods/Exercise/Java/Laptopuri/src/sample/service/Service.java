package sample.service;

import javafx.util.Pair;
import sample.domain.Client;
import sample.domain.Laptop;
import sample.domain.Vanzare;
import sample.repository.RepositoryInterface;
import sample.repository.Tip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Service {
    private RepositoryInterface<String, Laptop> lrepo;
    private RepositoryInterface<Long, Client> crepo;
    private RepositoryInterface<Pair<Client, Laptop>, Vanzare> vrepo;

    public Service(RepositoryInterface<String, Laptop> lrepo, RepositoryInterface<Long, Client> crepo, RepositoryInterface<Pair<Client, Laptop>, Vanzare> vrepo) {
        this.lrepo = lrepo;
        this.crepo = crepo;
        this.vrepo = vrepo;
    }

    private int compareTo(float p1,float p2){
        if(p1>p2)
            return 1;
        return 0;
    }


    public List<Laptop> getByProducator(){
         return lrepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Laptop::getProducator).thenComparing(Laptop::getPret).reversed())
                .collect(toList());
    }

    public List<Vanzare> getByType(Date date){
        return vrepo.findAll()
                .stream()
                .filter(x->x.getLaptop().getTip().equals(Tip.gaming))
                .filter(x->x.getLocalDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public Map<Month,Double> getProfitMaxim(){
        return vrepo.findAll()
                .stream()
                .filter(x->x.getLocalDateTime().getYear()== LocalDate.now().getYear())
                .collect(Collectors.groupingBy(Vanzare::getMonth,summingDouble(Vanzare::getPret)));
    }
}
