package sample.repository;

import javafx.util.Pair;
import sample.domain.Locatie;
import sample.domain.PiesaTeatru;
import sample.domain.Spectacol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

public class SpectacolRepo extends AbstractRepository<Pair<PiesaTeatru, Locatie>, Spectacol> {
    private PiesaRepo prepo;
    private LocatieRepo lrepo;
    private String file;

    public SpectacolRepo(PiesaRepo prepo,LocatieRepo lrepo,String file){
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
                    PiesaTeatru pt=prepo.findOne(s[0]);
                    Locatie loc=lrepo.findOne(s[1]);
                    Spectacol spectacol=new Spectacol(pt,loc,Integer.parseInt(s[2]),Integer.parseInt(s[3]));
                    super.save(spectacol);
                } else
                    throw new RuntimeException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RuntimeException("Citirea din fisier a esuat!\n");
        }
    }
}
