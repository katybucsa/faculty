package sample.repository;

import javafx.util.Pair;
import sample.domain.Client;
import sample.domain.Laptop;
import sample.domain.Vanzare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Stream;

public class VanzareRepo extends AbstractRepository<Pair<Client, Laptop>, Vanzare> {
    private ClientRepo prepo;
    private LaptopRepo lrepo;
    private String file;

    public VanzareRepo(ClientRepo prepo, LaptopRepo lrepo, String file){
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
                String[] s = line.split("[|]");
                if (s.length == 4) {
                    Client pt=prepo.findOne(Long.parseLong(s[0]));
                    Laptop loc=lrepo.findOne(s[1]);
                    Vanzare spectacol=new Vanzare(pt,loc, LocalDateTime.parse(s[2]));
                    super.save(spectacol);
                } else
                    throw new RuntimeException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RuntimeException("Citirea din fisier a esuat!\n");
        }
    }
}
