package Curs5;

interface Formula {
    double pi = 3.14;
    double calculate(double a, double b);
    default double sqrt(double a){
        return Math.sqrt(a);
    }
    default double power(double a,double b){
        return Math.pow(a,b);
    }
    default double numarLaPatrat(double nr){
        return  power(nr,2);
    }
    default double numarLaCub(double nr){
        return power(nr,3);
    }
    default double patratBinom(double x,double y){
        return Math.pow(x+y,2);
    }
    default double cubBinom(double x,double y){
        return Math.pow(x+y,3);
    }
    default double suma(double x,double y){
        return x+y;
    }
}
