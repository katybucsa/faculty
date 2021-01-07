public class FormeazaNrComplex {

    public NumarComplex formeaza(String numar) {
        String[] parts = new String[20];
        NumarComplex nr = new NumarComplex();
        int count = 0;
        //count how many operators contains the expression
        for (int i = 0; i < numar.length(); i++)
            if (numar.charAt(i) == '+' || numar.charAt(i) == '-')
                count++;

        if (numar.charAt(0) == '-') {
            if (count == 2) {
                if (numar.contains("+")) {
                    //complex number begins with "-",contains 2 operators and the second "+"
                    parts = numar.split("[+]");
                    if(parts[1].matches("[0-9]+i") || parts[1].matches("-[0-9]+i")){
                        String[] p2=parts[1].split("[i]");
                        nr = new NumarComplex(Float.parseFloat(parts[0]),Float.parseFloat(p2[0]));
                    }
                    else{
                        nr = new NumarComplex(Float.parseFloat(parts[0]),1);
                    }
                } else {
                    //complex number begins with "-",contains 2 operators, both "-"
                    parts = numar.split("[-]",2);
                    String[] part=parts[1].split("[-]");
                    if(part[1].matches("[0-9]*i") || part[1].matches("-[0-9]*i")) {
                        String[] p2 = part[1].split("[i]");
                        nr = new NumarComplex(-Float.parseFloat(part[0]), -1);
                    }
                    else{
                        nr = new NumarComplex(Float.parseFloat(parts[0]), -1);
                    }
                }
            } else {
                if(numar.contains("i")) {
                    //complex number begins with "-" and it has only imaginary part
                    if(numar.matches("[0-9]+i") || numar.matches("-[0-9]+i")){
                        String[] p = numar.split("[i]");
                        nr = new NumarComplex(0, Float.parseFloat(p[0]));
                    }
                    else{
                        nr = new NumarComplex(0, -1);
                    }
                }
                else{
                    //complex number begins with "-" and it has only real part
                    nr=new NumarComplex(Float.parseFloat(numar),0);
                }
            }
        } else {
            if (numar.contains("-")) {
                //complex number do not start with "-", but it contains an operator which is "-"
                parts = numar.split("(?=-)");
                if(parts[1].matches("[0-9]+i") || parts[1].matches("-[0-9]+i")) {
                    String[] p2 = parts[1].split("[i]");
                    nr = new NumarComplex(Float.parseFloat(parts[0]), Float.parseFloat(p2[0]));
                }
                else{
                    nr = new NumarComplex(Float.parseFloat(parts[0]), -1);
                }
            } else {
                if (numar.contains("+")) {
                    //complex number do not start with "-", but it contains an operator which is "+"
                    parts = numar.split("[+]");
                    if(parts[1].matches("[0-9]+i") || parts[1].matches("[0-9]+.+[0-9]+i") || parts[1].matches("-[0-9]+i") || parts[1].matches("-[0-9]+.+[0-9]+i")) {
                        String[] p2 = parts[1].split("[i]");
                        nr = new NumarComplex(Float.parseFloat(parts[0]), Float.parseFloat(p2[0]));
                    }
                    else {
                        nr = new NumarComplex(Float.parseFloat(parts[0]), 1);
                    }
                } else {
                    if (numar.contains("i")) {
                        //complex number do not contains operators and it has only imaginary part
                        if(numar.matches("[0-9]+i") || numar.matches("-[0-9]+i")) {
                            String[] p = numar.split("[i]");
                            nr = new NumarComplex(0, Float.parseFloat(p[0]));
                        }
                        else{
                            nr = new NumarComplex(0, 1);
                        }
                    } else
                        //complex number do not contains operators and it has only real part
                        nr = new NumarComplex(Float.parseFloat(numar), 0);
                }
            }

        }
        return nr;
    }
}