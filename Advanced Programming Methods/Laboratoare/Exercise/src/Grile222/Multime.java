package Grile222;

class Multime
{
    public static void main(String [] args)
    {
        Multime m = new Multime();
        m.start();
    }

    void start()
    {
        int [] v1 = {1, 2, 3};
        int [] v2 = modif(v1);
        System.out.print(""+v1[0] + v1[1] + v1[2] + " ");
        System.out.println(v2[0] + v2[1] + v2[2]);
    }

    int [] modif(int [] v3)
    {
        v3[1] = 21;
        return v3;
    }
}
