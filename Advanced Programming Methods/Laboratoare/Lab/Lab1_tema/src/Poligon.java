import java.awt.font.NumericShaper;

public class Poligon {
    private NumarComplex[] nrC;
    private int lenght;

    public Poligon(){
        //constructor for objects of type Poligon
        nrC=new NumarComplex[50];
        lenght=0;
    }

    public void add(NumarComplex nr){
        //adds a complex number in the vector
        nrC[lenght]=nr;
        lenght++;
    }


    public String makeValue(String operator){
        //calculates the result of the given expression
        NumarComplex n=nrC[0];
        if(operator.charAt(0)=='+') {
            for (int i = 1; i < lenght; i++) {
                n.add(nrC[i]);
            }
        }
        else if(operator.charAt(0)=='-'){
            for (int i = 1; i < lenght; i++) {
                n.substract(nrC[i]);
            }
        }
        else if(operator.charAt(0)=='*'){
            for(int i=1;i<lenght;i++){
                n.multiply(nrC[i]);
            }
        }
        else{
            for(int i=1;i<lenght;i++){
                n.divide(nrC[i]);
            }
        }
        if(n.getIm()>0)
            return String.valueOf(n.getRe())+"+"+String.valueOf(n.getIm())+"i";
        return String.valueOf(n.getRe())+String.valueOf(n.getIm())+"i";
    }

    public double makePerimeter(){

        double P=0,l;
        if(lenght<3)
            throw new RuntimeException("Sunt prea putine puncte pentru a forma un poligon!\n");
        for(int i=0;i<lenght-1;i++){
            l=Math.sqrt(Math.pow(nrC[i+1].getRe()-nrC[i].getRe(),2) +Math.pow(nrC[i+1].getIm()-nrC[i].getIm(),2));
            P+=l;
        }
        l=Math.sqrt(Math.pow(nrC[lenght-1].getRe()-nrC[0].getRe(),2) +Math.pow(nrC[lenght-1].getIm()-nrC[0].getIm(),2));
        P+=l;
        return P;
    }

}
