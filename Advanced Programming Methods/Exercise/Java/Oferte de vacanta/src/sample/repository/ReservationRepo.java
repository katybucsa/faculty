package sample.repository;

import javafx.util.Pair;
import sample.domain.Client;
import sample.domain.Location;
import sample.domain.Reservation;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class ReservationRepo extends AbstractRepository<Pair<Client, Location>, Reservation> {
    private ClientRepo prepo;
    private LocationRepo lrepo;
    private String file;

    public ReservationRepo(ClientRepo prepo, LocationRepo lrepo, String file){
        super();
        this.prepo=prepo;
        this.lrepo=lrepo;
        this.file=file;
        readFromFile();
    }

    private void readFromFile(){
        Path path = Paths.get(file);
        Stream<String> lines;
        map.clear();
        try {
            lines = Files.lines(path);
            lines.forEach(line -> {
                String[] s = line.split("[,]");
                if (s.length == 4) {
                    Client pt=prepo.findOne(Long.parseLong(s[0]));
                    Location loc=lrepo.findOne(s[1]);
                    Reservation spectacol=new Reservation(pt,loc, LocalDateTime.parse(s[2]),Integer.parseInt(s[3]));
                    super.save(spectacol);
                } else
                    throw new RuntimeException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RuntimeException("Citirea din fisier a esuat!\n");
        }
    }
}
