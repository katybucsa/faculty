package addition;



/**
 * Created on: 16-Oct-19, 22:19
 *
 * @author: Katy Bucșa
 */
public class MyThreadFinal implements Runnable {
    private int begin;
    private int offset;

    public MyThreadFinal(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }


    @Override
    public void run() {
        //Elem elem = Elems.elems.get(this.p);
        for (int i = begin; i < begin + offset; i++) {
            int s;
            if (i == 0 && Elems.CARRIES.size() > Elems.N1.size()) {
                Elems.RESULT.add(Elems.RESULT.size(),1);
                //Elems.RESULT.at(Elems.result.size() - 1) = Elems.CARRIES.at(CARRIES.size() - 1);
            }
            if (i < Elems.N2.size()) {
                s = (Elems.N1.get(i) + Elems.N2.get(i) + Elems.CARRIES.get(i)) % 10;
            } else {
                s = (Elems.N1.get(i) + Elems.CARRIES.get(i)) % 10;
            }
            Elems.RESULT.set(i, s);
        }

    }
}
