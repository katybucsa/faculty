package factory;

import domain.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * class FileStudentRepo makes the persistence in file of the entities of type Student
 */
public class FileStudentRepo extends StudentRepository {
    private String file;

    public FileStudentRepo(Validator<Student> v, String file) {
        super(v);
        this.file = file;
        readFromFile();
    }

    /**
     * reads data form file and adds the Student entities in memory
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
                    Student st = new Student(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), s[3], s[4]);
                    super.save(st);
                } else
                    throw new RepoException("Linie incompleta!\n");
            });
        } catch (IOException e) {
            throw new RepoException("Citirea din fisier a esuat!\n");
        }
    }

    /**
     * saves the Student entities form memory to file
     */
    private void writeToFile() {
        try (PrintWriter pw = new PrintWriter(file)) {
            super.findAll().forEach(s -> pw.println(s.getID() + "|" + s.getNume() + "|" + s.getGrupa() + "|" + s.getEmail() + "|" + s.getIndrumator()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Optional<Student> findOne(int id) {
        readFromFile();
        return super.findOne(id);
    }

    public Student save(Student s) {
        readFromFile();
        Student st = super.save(s);
        writeToFile();
        return st;
    }

    public Optional<Student> update(Student s) {
        readFromFile();
        Optional<Student> st = super.update(s);
        writeToFile();
        return st;
    }

    public int size() {
        readFromFile();
        return super.size();
    }

    public Iterable<Student> findAll() {
        readFromFile();
        return super.findAll();
    }

    public Optional<Student> delete(int id) {
        readFromFile();
        Optional<Student> s = super.delete(id);
        writeToFile();
        return s;
    }
}