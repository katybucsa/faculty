public class UI {
    private Service serv;

    public void UiAdd(String[] expresie) {
        try {
            serv.addElem(expresie);
        } catch (NumarComplexError err) {
            err.printStackTrace();
        }
    }
}
