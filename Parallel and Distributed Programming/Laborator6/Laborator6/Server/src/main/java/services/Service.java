package services;

import model.Bilet;
import model.Categorie;
import model.Loc;
import model.Spectacol;
import persistence.Repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created on: 19-Dec-19, 21:53
 *
 * @author: Katy Bucșa
 */

public class Service implements IService {

    private Repo repo;

    public Service(Repo repo) {
        this.repo = repo;
    }


    public List<Bilet> findAllBilete() {

        List<Bilet> bilets = new ArrayList<>();
        repo.findAllBilete().forEach(bilets::add);
        return bilets;
    }


    public List<Categorie> findAllCategorii() {
        List<Categorie> categorii = new ArrayList<>();
        repo.findAllCategorii().forEach(categorii::add);
        return categorii;
    }

    @Override
    public List<Loc> findAllLocuri() {
        List<Loc> locuri = new ArrayList<>();
        repo.findAllLocuri().forEach(locuri::add);
        return locuri;
    }

    @Override
    public Bilet buyTicket(int idSpectacol, int idCategorie) {
        Spectacol s = repo.getSpectacolById(idSpectacol);
        Loc locuri = repo.getLocByIdSpectacolAndIdCategorie(idSpectacol, idCategorie);
        Bilet b = new Bilet(s, idCategorie, locuri.getNrTotalLocuri() - locuri.getNrLocuriLibere() + 1, new Date());
        return repo.saveTicket(b);
    }

    @Override
    public void makeVerification() {
        System.out.println("=========================================================================================");
        List<Bilet> bilete = findAllBilete();
        Map<Spectacol, Long> spectacole = bilete.stream()
                .collect(Collectors.groupingBy(Bilet::getSpectacol, Collectors.counting()));
        spectacole.forEach((k, v) -> System.out.println("Id: " + k.getId() + ", Data: " + k.getData() + ", Locuri vandute: " + v));
        Map<Date, List<Bilet>> vanzari = bilete.stream()
                .collect(Collectors.groupingBy(Bilet::getDataVanzare, Collectors.toList()));
        System.out.println("Vanzare:\n");
        vanzari.forEach((k, v) -> {
            System.out.println("Data vanzare: " + k);
            Map<Spectacol, Long> map = v.stream()
                    .collect(Collectors.groupingBy(Bilet::getSpectacol, Collectors.counting()));
            map.forEach((x, y) -> System.out.println("Id spectacol: " + x.getId() + ", Numar bilete: " + y));
        });
        AtomicLong soldTotal = new AtomicLong();
        Map<Integer, Long> bilete1 = bilete.stream().collect(Collectors.groupingBy(Bilet::getIdCategorie, Collectors.counting()));
        List<Categorie> categories = findAllCategorii();
        bilete1.forEach((x, y) -> {
            for (Categorie category : categories) {
                if (category.getId() == x) {
                    soldTotal.addAndGet(y * category.getPret());
                }
            }
        });
        System.out.println("Sold total: " + soldTotal);
        System.out.println("=========================================================================================");
    }
}