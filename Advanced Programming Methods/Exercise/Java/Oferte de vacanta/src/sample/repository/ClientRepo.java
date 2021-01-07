package sample.repository;

import sample.domain.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ClientRepo extends AbstractRepository<Long, Client> {
    private String file;

    public ClientRepo(String file){
        super();
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
                if (s.length == 2) {
                    Client pt=new Client(Long.parseLong(s[0]),s[1]);
                    super.save(pt);
                } else
                    throw new RuntimeException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RuntimeException("Citirea din fisier a esuat!\n");
        }
    }
}
