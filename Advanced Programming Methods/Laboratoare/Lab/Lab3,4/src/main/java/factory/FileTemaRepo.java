package factory;

import domain.Tema;

import java.io.*;

public class FileTemaRepo extends TemaRepository {
    private String file;
    public FileTemaRepo(Validator<Tema> v, String file){
        super(v);
        this.file = file;
        readFromFile();
    }

    /**
     * reads data form file and adds the Tema entities in memory
     */
    private void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            map.clear();
            while ((line = br.readLine()) != null) {
                String[] s = line.split("[|]");
                if (s.length == 4) {
                    Tema t = new Tema(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]));
                    super.save(t);
                } else
                    throw new RepoException("Fisier corupt!\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a putut deschide fisierul pentru citire!\n");
        } catch (IOException e) {
            throw new RepoException("Citirea dinfisier a esuat!\n");
        }
    }

    /**
     * saves the Tema entities form memory to file
     */
    private void writeToFile() {
        try(PrintWriter pw = new PrintWriter(file)) {
            for (Tema t : super.findAll()) {
                pw.println(t.getID() + "|" + t.getDescriere() + "|" + t.getDeadline()+ "|" + t.getSaptPrimireTema());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nu s-a putut deschide fisierul pentru scriere!\n");
        }
    }

    public Tema findOne(int id){
        readFromFile();
        return super.findOne(id);
    }

    public Tema save(Tema t){
        readFromFile();
        Tema tm = super.save(t);
        writeToFile();
        return tm;
    }

    public Tema update(Tema t){
        readFromFile();
        Tema tm = super.update(t);
        writeToFile();
        return tm;
    }

    public int size(){
        readFromFile();
        return super.size();
    }

    public Iterable<Tema> findAll(){
        readFromFile();
        return super.findAll();
    }

    public Tema delete(int id){
        readFromFile();
        Tema t = super.delete(id);
        writeToFile();
        return t;
    }
}
