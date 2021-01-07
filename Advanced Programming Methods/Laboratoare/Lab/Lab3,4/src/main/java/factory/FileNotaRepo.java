package factory;

import domain.Nota;
import javafx.util.Pair;

import java.io.*;
import java.time.LocalDateTime;


public class FileNotaRepo extends NotaRepository {
    private String file;
    public FileNotaRepo(Validator<Nota> v, String file){
        super(v);
        this.file = file;
        readFromFile();
    }

    /**
     * reads data form file and adds the Nota entities in memory
     */
    private void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            map.clear();
            while ((line = br.readLine()) != null) {
                String[] s = line.split("[|]");
                if (s.length == 4) {
                    Nota n = new Nota(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Double.parseDouble(s[2]),LocalDateTime.parse(s[3]));
                    super.save(n);
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
     * saves the Nota entities form memory to file
     */
    private void writeToFile() {
        try(PrintWriter pw = new PrintWriter(file)) {
            for (Nota n : super.findAll()) {
                pw.println(n.getID().getKey() + "|" + n.getID().getValue() + "|" + n.getNota()+ "|" + n.getData().toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Nota findOne(Pair id){
        readFromFile();
        return super.findOne(id);
    }

    public Nota save(Nota n){
        readFromFile();
        Nota nt = super.save(n);
        writeToFile();
        return nt;
    }

    public Nota update(Nota n){
        readFromFile();
        Nota nt = super.update(n);
        writeToFile();
        return nt;
    }

    public int size(){
        readFromFile();
        return super.size();
    }

    public Iterable<Nota> findAll(){
        readFromFile();
        return super.findAll();
    }

    public Nota delete(Pair id){
        readFromFile();
        Nota s = super.delete(id);
        writeToFile();
        return s;
    }
}