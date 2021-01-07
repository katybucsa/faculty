public class Rational {
    private int numarator;
    private int numitor;

    {
        numarator = 0;
        numitor = 1;
    }

    /*public Rational(){
        numarator=6;
        numitor=10;
    }

    public Rational(int x,int y){numarator=x;numitor=y;}*/

    @Override
    public String toString(){
        return numarator+" "+numitor;
    }
}

