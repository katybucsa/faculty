package sample.repository;


import javafx.util.Pair;
import sample.domain.Nota;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;


public class FileNotaRepo extends NotaRepository {
    private String file;

    public FileNotaRepo(Validator<Nota> v, String file) {
        super(v);
        this.file = file;
        readFromFile();
    }

    /**
     * reads data form file and adds the Nota entities in memory
     */
    private void readFromFile() {
        Path path = Paths.get(file);
        Stream<String> lines;
        map.clear();
        try {
            lines = Files.lines(path);
            lines.forEach(line -> {
                String[] s = line.split("[|]");
                if (s.length == 5) {
                    Nota n = new Nota(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Double.parseDouble(s[2]), LocalDateTime.parse(s[3]),s[4]);
                    super.save(n);
                } else
                    throw new RepoException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RepoException("Citirea din fisier a esuat!\n");
        }
    }

    /**
     * saves the Nota entities form memory to file
     */
    private void writeToFile() {
        try (PrintWriter pw = new PrintWriter(file)) {
            super.findAll().forEach(n -> pw.println(n.getID().getKey() + "|" + n.getID().getValue() + "|" + n.getNota() + "|" + n.getData()+"|"+n.getFeedback()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Optional<Nota> findOne(Pair id) {
        readFromFile();
        return super.findOne(id);
    }

    public Nota save(Nota n) {
        readFromFile();
        Nota nt = super.save(n);
        writeToFile();
        return nt;
    }

    public Optional<Nota> update(Nota n) {
        readFromFile();
        Optional<Nota> nt = super.update(n);
        writeToFile();
        return nt;
    }

    public int size() {
        readFromFile();
        return super.size();
    }

    public Iterable<Nota> findAll() {
        readFromFile();
        return super.findAll();
    }

    public Optional<Nota> delete(Pair id) {
        readFromFile();
        Optional<Nota> s = super.delete(id);
        writeToFile();
        return s;
    }
}