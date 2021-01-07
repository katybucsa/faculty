public class Complex {
    private double real, imag;

    Complex(double real) {
        this(real, 0);
    }

    Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

   public Complex suma(Complex c){
       // this(real+c.real,imag+c.imag); -GRESIT! -un constructor nu poate fi apelat din interiorul altor metode
        return this;
    }

    public void aduna(double real){
        this.real+=real;
    }

    public void aduna(Complex c){
        this.real+=c.real;
        this.imag+=c.imag;
    }

   /* public Complex aduna(Complex c){
        this.real+=c.real;
        this.imag+=c.imag;
        return this;
    }*/
}
