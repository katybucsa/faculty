/**
 * Created on: 16-Oct-19, 22:22
 *
 * @author: Katy Bucșa
 */

public class MyThreadPrefix implements Runnable {
    int begin;
    int offset;

    public MyThreadPrefix(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }

    @Override
    public void run() {

        for (int i = begin; i < begin + offset; i++) {
            if (i == 0) {
                Res.prefixes.set(0, Res.c.get(0));
            } else if (Res.c.get(i).equals('C') || Res.c.get(i).equals('N')) {
                Res.prefixes.set(i, Res.c.get(i));
            } else {
                int x = i;
                while (Res.c.get(x).equals('M')) {
                    x--;
                }
                Res.prefixes.set(i, Res.c.get(x));
            }
        }
    }
}
