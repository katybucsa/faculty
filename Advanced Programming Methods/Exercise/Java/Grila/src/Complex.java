import javafx.scene.control.ComboBox;

public class Complex {
    private double real,imag;
    Complex(double real){
        this.real=real;
        this.imag=0;
    }

    Complex(double real, double imag){
        this.real=real;
        this.imag=imag;
    }

    public Complex suma(Complex c){
        return this;
    }

    public String toString(){
        return real+"  "+imag;
    }
}
