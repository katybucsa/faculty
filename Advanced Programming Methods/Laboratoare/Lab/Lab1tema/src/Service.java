public class Service {
    private Repository repo=new Repository();
    private Verifica verif=new Verifica();

    public void addElem(String[] str){
        verif.verifica(str);
    }
}
