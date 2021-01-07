package sample.service;

import javafx.util.Pair;
import sample.domain.Book;
import sample.domain.Client;
import sample.domain.Rent;
import sample.domain.Type;
import sample.repository.RepositoryInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

public class Service {
    private RepositoryInterface<String, Book> brepo;
    private RepositoryInterface<String, Client> crepo;
    private RepositoryInterface<Pair<Client, Book>, Rent> rrepo;

    public Service(RepositoryInterface<String, Book> prepo, RepositoryInterface<String, Client> lrepo, RepositoryInterface<Pair<Client, Book>, Rent> srepo) {
        this.brepo = prepo;
        this.crepo = lrepo;
        this.rrepo = srepo;
    }

    public Map<Type, List<Book>> getBooksByType() {
        return brepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getName).thenComparing(Book::getType))
                .collect(Collectors.groupingBy(Book::getType));
    }

    public Integer getNrCarti(String name) {
        List<Rent> rent = new ArrayList<>();
        rent = rrepo.findAll()
                .stream()
                .filter(x -> x.getClientName().equals(name))
                .collect(toList());
        return rent.size();
    }


    public List<Book> getFaraInchiriere(int year) {
        List<Book> list = new ArrayList<>();
        Map<Book, Long> map = rrepo.findAll()
                .stream()
                .filter(x -> x.getRentDate().getYear() == year)
                .collect(Collectors.groupingBy(Rent::getBook, counting()));
        for (Map.Entry<Book, Long> entry : map.entrySet()) {
            if (entry.getValue() <= 3)
                list.add(entry.getKey());
        }
        for (Book b : brepo.findAll()) {
            boolean ok = true;
            for (Rent r : rrepo.findAll())
                if (r.getBook().getID() == b.getID())
                    ok = false;
            if (ok)
                list.add(b);
        }
        return list;
    }

    /*public List<Book> getDinPerioada(LocalDate ds,LocalDate df){
        rrepo.findAll()
                .stream()
                .filter(x->x.getRentDate().isAfter(ds))
                .filter(x->x.getRentDate().isBefore(df))
    }*/


/*    public List<Book> getLocations(String location) {
        return prepo.findAll()
                .stream()
                .filter(x -> x.getLocationName().equals(location))
                .collect(Collectors.toList());
    }


    public Map<Client, Long> getClientNrReservations() {
        return srepo.findAll()
                .stream()
                .filter(x -> x.getStartDate().getYear() == LocalDateTime.now().getYear())
                .collect(Collectors.groupingBy(Rent::getClient, counting()));
    }


    public Map<String, Double> getSumaIncasataHotel() {

        List<Book> list = new ArrayList<>();
        srepo.findAll().forEach(x -> {
            for (int i = 0; i < x.getNoNights(); i++) list.add(x.getBook());
        });
        return list.stream()
                .collect(Collectors.groupingBy(Book::getHotel, Collectors.summingDouble(Book::getPricePerNight)));
    }

    public Collection<Rent> getAllReservations() {
        return srepo.findAll();
    }

    public Collection<Book> getAllLoc(){
        return prepo.findAll();
    }

    public List<String> getAllLocations() {
        List<String> list = new ArrayList<>();
        prepo.findAll().forEach(x -> list.add(x.getLocationName()));
        return list.stream().distinct().collect(Collectors.toList());
    }

    public Integer getNrNopti(String location) {
        Integer i = 0;
        for (Rent r : srepo.findAll())
            if (r.getLocationName().equals(location))
                i += r.getNoNights();
        return i;
    }*/
    }
