public class UI {
    private  Poligon pol;
    private Validator valid;
    private FormeazaNrComplex form;

    public UI(){
        //constructor
        pol=new Poligon();
        valid=new Validator();
        form=new FormeazaNrComplex();
    }


    public void run(String[] expresie) {

            if (!valid.validate(expresie)) {
                System.out.println("Expresie incorecta!\n");
            } else {
                for (int i = 0; i < expresie.length; i += 2) {
                    pol.add(form.formeaza(expresie[i]));
                }
                String value = pol.makeValue(expresie[1]);
                System.out.println("\nValoarea expresiei este:  " + value + "\n");
                double P=0;
                try {
                P = pol.makePerimeter();
                }catch (RuntimeException e){
                    System.out.print(e.getMessage());
                }
                System.out.println("\nPerimetrul poligonului este:  " + P + "\n");
            }
    }
}
