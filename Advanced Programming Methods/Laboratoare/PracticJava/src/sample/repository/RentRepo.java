package sample.repository;

import javafx.util.Pair;
import sample.domain.Client;
import sample.domain.Book;
import sample.domain.Rent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class RentRepo extends AbstractRepository<Pair<Client, Book>, Rent> {
    private ClientRepo prepo;
    private BookRepo lrepo;
    private String file;

    public RentRepo(ClientRepo prepo, BookRepo lrepo, String file){
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
                if (s.length == 3) {
                    Client pt=prepo.findOne(s[1]);
                    Book loc=lrepo.findOne(s[0]);
                    Rent spectacol=new Rent(pt,loc, LocalDate.parse(s[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    super.save(spectacol);
                } else
                    throw new RuntimeException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RuntimeException("Citirea din fisier a esuat!\n");
        }
    }
}
