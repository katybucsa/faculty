package factory;

import domain.Student;

import java.io.*;

/**
 * class FileStudentRepo makes the persistence in file of the entities of type Student
 */
public class FileStudentRepo extends StudentRepository{
    private String file;
    public FileStudentRepo(Validator<Student> v, String file){
        super(v);
        this.file = file;
        readFromFile();
    }

    /**
     * reads data form file and adds the Student entities in memory
     */
    private void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            map.clear();
            while ((line = br.readLine()) != null) {
                String[] s = line.split("[|]");
                if (s.length == 5) {
                    Student st = new Student(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), s[3], s[4]);
                    super.save(st);
                } else
                    throw new RepoException("Linie incompleta!\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a putut deschide fisierul pentru citire!\n");
        } catch (IOException e) {
            throw new RepoException("Citirea dinfisier a esuat!\n");
        }
    }

    /**
     * saves the Student entities form memory to file
     */
    private void writeToFile() {
        try(PrintWriter pw = new PrintWriter(file)) {
            for (Student s : super.findAll()) {
                pw.println(s.getID() + "|" + s.getNume() + "|" + s.getGrupa() + "|" + s.getEmail() + "|" + s.getIndrumator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Student findOne(int id){
        readFromFile();
        return super.findOne(id);
    }

    public Student save(Student s){
        readFromFile();
        Student st = super.save(s);
        writeToFile();
        return st;
    }

    public Student update(Student s){
        readFromFile();
        Student st = super.update(s);
        writeToFile();
        return st;
    }

    public int size(){
        readFromFile();
        return super.size();
    }

    public Iterable<Student> findAll(){
        readFromFile();
        return super.findAll();
    }

    public Student delete(int id){
        readFromFile();
        Student s = super.delete(id);
        writeToFile();
        return s;
    }
}
