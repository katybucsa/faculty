package monomial;

/**
 * Created on: 22-Nov-19, 20:40
 *
 * @author: Katy Bucșa
 */

public class Monomial {
    private double coefficient;
    private int exponent;

    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return this.coefficient;
    }

    public int getExponent() {
        return this.exponent;
    }
}
