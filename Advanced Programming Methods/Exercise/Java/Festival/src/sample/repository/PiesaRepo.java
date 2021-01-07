package sample.repository;

import sample.domain.PiesaTeatru;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

public class PiesaRepo extends AbstractRepository<String, PiesaTeatru> {
    private String file;

    public PiesaRepo(String file){
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
                String[] s = line.split("[|]");
                if (s.length == 3) {
                    PiesaTeatru pt=new PiesaTeatru(s[0],s[1],s[2]);
                    super.save(pt);
                } else
                    throw new RuntimeException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RuntimeException("Citirea din fisier a esuat!\n");
        }
    }
}
