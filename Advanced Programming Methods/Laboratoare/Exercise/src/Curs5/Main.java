package Curs5;

public class Main {
    public static void main(String[] args) {
        Formula patratBinom = new Formula() {
            @Override
            public double calculate(double a, double b) {
                return patratBinom(a, b);
            }
        };
        double a = 2.1, b = 2.2;
        double rez = patratBinom.calculate(a, b);
        System.out.format("(%.2f+%.2f)^2=%.2f\n", a, b, rez);

        Formula bin2=FormulaHelper::partatBinom;
        System.out.format("(%.2f+%.2f)^2=%.2f\n", a, b, bin2.calculate(a,b));
    }
}
