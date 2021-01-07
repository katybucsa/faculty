/**
 * Created on: 17-Oct-19, 17:11
 *
 * @author: Katy Bucșa
 */

public class MyThreadCarry2 implements Runnable {
    int begin;
    int offset;

    public MyThreadCarry2(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }

    @Override
    public void run() {
        for (int i = begin; i < begin + offset; i++) {
            if (i == 0) {
                Res.carries.set(0, 0);
                if (Res.prefixes.get(Res.prefixes.size() - 1).equals('C')) {
                    Res.carries.add(Res.carries.size(), 1);
                }
            } else {
                if (Res.prefixes.get(i - 1).equals('C')) {
                    Res.carries.set(i, 1);
                } else {
                    Res.carries.set(i, 0);
                }
            }
        }
    }
}
