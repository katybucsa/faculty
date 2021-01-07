package sample.service;

import javafx.util.Pair;
import sample.domain.Client;
import sample.domain.Location;
import sample.domain.Reservation;
import sample.repository.RepositoryInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.summingInt;

public class Service {
    private RepositoryInterface<String, Location> prepo;
    private RepositoryInterface<Long, Client> lrepo;
    private RepositoryInterface<Pair<Client, Location>, Reservation> srepo;

    public Service(RepositoryInterface<String, Location> prepo, RepositoryInterface<Long, Client> lrepo, RepositoryInterface<Pair<Client, Location>, Reservation> srepo) {
        this.prepo = prepo;
        this.lrepo = lrepo;
        this.srepo = srepo;
    }


    public List<Location> getLocations(String location) {
        return prepo.findAll()
                .stream()
                .filter(x -> x.getLocationName().equals(location))
                .collect(Collectors.toList());
    }


    public Map<Client, Long> getClientNrReservations() {
        return srepo.findAll()
                .stream()
                .filter(x -> x.getStartDate().getYear() == LocalDateTime.now().getYear())
                .collect(Collectors.groupingBy(Reservation::getClient, counting()));
    }


    public Map<String, Double> getSumaIncasataHotel() {

        List<Location> list = new ArrayList<>();
        srepo.findAll().forEach(x -> {
            for (int i = 0; i < x.getNoNights(); i++) list.add(x.getLocation());
        });
        return list.stream()
                .collect(Collectors.groupingBy(Location::getHotel, Collectors.summingDouble(Location::getPricePerNight)));
    }

    public Collection<Reservation> getAllReservations() {
        return srepo.findAll();
    }

    public Collection<Location> getAllLoc(){
        return prepo.findAll();
    }

    public List<String> getAllLocations() {
        List<String> list = new ArrayList<>();
        prepo.findAll().forEach(x -> list.add(x.getLocationName()));
        return list.stream().distinct().collect(Collectors.toList());
    }

    public Integer getNrNopti(String location) {
        Integer i = 0;
        for (Reservation r : srepo.findAll())
            if (r.getLocationName().equals(location))
                i += r.getNoNights();
        return i;
    }
}
