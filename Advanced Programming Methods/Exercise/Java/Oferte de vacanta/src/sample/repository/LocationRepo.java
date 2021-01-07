package sample.repository;

import sample.domain.Location;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LocationRepo extends AbstractRepository<String, Location> {
private String file;

public LocationRepo(String file){
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
        if (s.length == 5) {
        Location loc=new Location(s[0],s[1],s[2],Integer.parseInt(s[3]),Double.parseDouble(s[4]));
        super.save(loc);
        } else
        throw new RuntimeException("Linie incompleta!\n");
        });
        } catch (IOException e) {
        throw new RuntimeException("Citirea din fisier a esuat!\n");
        }
        }
        }