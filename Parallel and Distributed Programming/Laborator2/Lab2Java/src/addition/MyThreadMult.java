package addition;

import multiplication.DigitMultiplication;

import java.util.Collections;
import java.util.List;

import static addition.Elems.*;

/**
 * Created on: 29-Oct-19, 00:12
 *
 * @author: Katy Bucșa
 */

public class MyThreadMult implements Runnable {
    private int begin;
    private int offset;

    public MyThreadMult(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }

    @Override
    public void run() {
        for (int i = begin; i < begin + offset; i++) {
            List<Integer> r = DigitMultiplication.multiplyVector(N1, Elems.N2.get(i));
            r.addAll(Collections.nCopies(Elems.N2.size() - i - 1, 0));
            Collections.reverse(r);
            //std::fill_n (std::back_inserter (r), Elems.N2.size() - i - 1, 0);
            //std::reverse (r.begin(), r.end());
            Elems.elems.set(i, r);
            Elems.lock.lock();
            if (r.size() > MAX_LENGTH) {
                MAX_LENGTH = r.size();
            }
            Elems.lock.unlock();
        }
    }
}
