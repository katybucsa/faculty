package factory;

import domain.Tema;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class FileTemaRepo extends TemaRepository {
    private String file;

    public FileTemaRepo(Validator<Tema> v, String file) {
        super(v);
        this.file = file;
        readFromFile();
    }

    /**
     * reads data form file and adds the Tema entities in memory
     */
    private void readFromFile() {
        Path path = Paths.get(file);
        Stream<String> lines;
        map.clear();
        try {
            lines = Files.lines(path);
            lines.forEach(line -> {
                String[] s = line.split("[|]");
                if (s.length == 4) {
                    Tema t = new Tema(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]));
                    super.save(t);
                } else
                    throw new RepoException("Fisier corupt!\n");
            });
        } catch (IOException e) {
            throw new RepoException("Citirea dinfisier a esuat!\n");
        }
    }

    /**
     * saves the Tema entities form memory to file
     */
    private void writeToFile() {
        try (PrintWriter pw = new PrintWriter(file)) {
            super.findAll().forEach(t -> pw.println(t.getID() + "|" + t.getDescriere() + "|" + t.getDeadline() + "|" + t.getSaptPrimireTema()));
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a putut deschide fisierul pentru scriere!\n");
        }
    }

    public Optional<Tema> findOne(int id) {
        readFromFile();
        return super.findOne(id);
    }

    public Tema save(Tema t) {
        readFromFile();
        Tema tm = super.save(t);
        writeToFile();
        return tm;
    }

    public Optional<Tema> update(Tema t) {
        readFromFile();
        Optional<Tema> tm = super.update(t);
        writeToFile();
        return tm;
    }

    public int size() {
        readFromFile();
        return super.size();
    }

    public Iterable<Tema> findAll() {
        readFromFile();
        return super.findAll();
    }

    public Optional<Tema> delete(int id) {
        readFromFile();
        Optional<Tema> t = super.delete(id);
        writeToFile();
        return t;
    }
}
