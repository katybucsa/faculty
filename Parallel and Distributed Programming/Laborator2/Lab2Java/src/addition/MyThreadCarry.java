package addition;

/**
 * Created on: 17-Oct-19, 17:11
 *
 * @author: Katy Bucșa
 */

public class MyThreadCarry implements Runnable {

    int begin;
    int offset;

    public MyThreadCarry(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }

    @Override
    public void run() {
        //Elem elem = Elems.elems.get(this.p);
        for (int i = begin; i < begin + offset; i++) {
            if (i == 0) {
                Elems.CARRIES.set(0, 0);
                if (Elems.C.get(Elems.C.size() - 1).equals('C')) {
                    Elems.CARRIES.add(Elems.CARRIES.size(), 1);
//                    Elems.CARRIES.at(Elems.CARRIES.size() - 1) = 1;
                } else if (Elems.C.get(Elems.C.size() - 1).equals('M')) {
                    int x = Elems.C.get(Elems.C.size() - 1);
                    while (Elems.C.get(x).equals('M')) {
                        x--;
                    }
                    if (Elems.C.get(x).equals('C')) {
                        Elems.CARRIES.add(Elems.CARRIES.size(), 1);
                        // Elems.CARRIES.at(Elems.CARRIES.size() - 1) = 1;
                    }
                }
            } else {
                if (Elems.C.get(i - 1).equals('C')) {
                    Elems.CARRIES.set(i, 1);
                } else if (Elems.C.get(i - 1).equals('N')) {
                    Elems.CARRIES.set(i, 0);
                } else {
                    int x = i - 1;
                    while (Elems.C.get(x).equals('M')) {
                        x--;
                    }
                    if (Elems.C.get(x).equals('C')) {
                        Elems.CARRIES.set(i, 1);
                    } else {
                        Elems.CARRIES.set(i, 0);
                    }
                }
            }
        }
        //Elems.elems.set(this.p, elem);
    }
}
