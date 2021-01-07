package Grile222;

class X
{
    protected char c = '1';
    protected int x=0;
    char getValoare()
    {
        return c;
    }
}

class Y extends X
{
    protected char c = '2';
    char getValoare()
    {
        return c;
    }
    char getSuper()
    {
        return super.c;
    }
}

class Main3
{
    public static void main(String[] args)
    {
        X ob1 = new Y();
        Y ob2 = new Y();

        System.out.println(ob1.c + " " + ob1.getValoare()
                + " " + ob2.getValoare() + " " + ob2.getSuper());
        System.out.println(ob2.c);
    }
}
