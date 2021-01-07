package sample.repository;

import sample.domain.Book;
import sample.domain.Type;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class BookRepo extends AbstractRepository<String, Book> {
    private String file;

    public BookRepo(String file) {
        super();
        this.file = file;
        readFromFile();
    }

    private void readFromFile() {
        Path path = Paths.get(file);
        Stream<String> lines;
        map.clear();
        try {
            lines = Files.lines(path);
            lines.forEach(line -> {
                String[] s = line.split("[,]");
                if (s.length == 4) {
                    Book loc = new Book(s[0], s[1], s[2], Type.valueOf(s[3]));
                    super.save(loc);
                } else
                    throw new RuntimeException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RuntimeException("Citirea din fisier a esuat!\n");
        }
    }
}