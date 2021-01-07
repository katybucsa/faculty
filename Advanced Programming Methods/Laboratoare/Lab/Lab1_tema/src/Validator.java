public class Validator {
    public boolean validate(String[] expresie) {
        //validates the given expression
        for (int i = 1; i < expresie.length; i += 2) {
            if (expresie[1].charAt(0)!= expresie[i].charAt(0)) {
                return false;
            }
        }
        for(int i=0;i<expresie.length;i+=2){
            if(!expresie[i].matches("[-]?[0-9]*i")  && !expresie[i].matches("[-]?[0-9]+[+][0-9]*i") && !expresie[i].matches("[-]?[0-9]") && !expresie[i].matches("[-]?[0-9]+[-][0-9]*i") && !expresie[i].matches("[0-9]+i") && !expresie[i].matches("[0-9]+[.][0-9]+[0-9]*i") && expresie[i].matches("-[0-9]+i") && !expresie[i].matches("-[0-9]+[.]+[0-9]+i"))
                return false;
        }
        return true;
    }
}
