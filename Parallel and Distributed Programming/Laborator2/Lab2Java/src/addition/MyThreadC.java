package addition;


/**
 * Created on: 16-Oct-19, 22:19
 *
 * @author: Katy Bucșa
 */
public class MyThreadC implements Runnable {
    private int begin;
    private int offset;

    public MyThreadC(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }

    @Override
    public void run() {
        //Elem elem = Elems.elems.get(this.p);
        for (int i = begin; i < begin + offset; i++) {
            if (i < Elems.N2.size()) {
                if (Elems.N1.get(i) + Elems.N2.get(i) > 9) {
                    Elems.C.set(i, 'C');
                } else if (Elems.N1.get(i) + Elems.N2.get(i) == 9) {
                    Elems.C.set(i, 'M');
                } else {
                    Elems.C.set(i, 'N');
                }
            } else {
                if (Elems.N1.get(i) == 9) {
                    Elems.C.set(i, 'M');
                } else {
                    Elems.C.set(i, 'N');
                }
            }
        }
        //Elems.elems.set(this.p, elem);
    }
}
