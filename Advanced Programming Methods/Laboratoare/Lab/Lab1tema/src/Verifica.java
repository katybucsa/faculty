
public class Verifica {
    public void verifica(String[] expresie) {
        for (int i = 1; i < expresie.length; i += 2)
            if (expresie.charAt(i) != expresie.charAt(1))
                throw new NumarComplexError("Expresie incorecta!\n");
    }
}