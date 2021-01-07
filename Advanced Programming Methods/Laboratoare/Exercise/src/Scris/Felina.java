package Scris;

import java.util.ArrayList;
import java.util.List;

class Felina {
    public String getIdentity() {
        return "Felina";
    }
}

class Pisica extends Felina {
    public String getIndentity() {
        return "Pisica";
    }
}


class Main11 {
    public static void main(String[] args) {

        List<Felina> l = new ArrayList<Felina>();
        Pisica p = new Pisica();
        System.out.println(p.getIndentity());
        Felina f = new Felina();
        System.out.println(f.getIdentity());
        l.add(p);
        l.add(f);
        l.forEach(x->System.out.println(x.getIdentity()));


    }
}