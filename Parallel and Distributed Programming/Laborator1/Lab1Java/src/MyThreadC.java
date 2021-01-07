/**
 * Created on: 16-Oct-19, 22:19
 *
 * @author: Katy Bucșa
 */
class MyThreadC implements Runnable {
    private int begin;
    private int offset;

    public MyThreadC(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }

    @Override
    public void run() {
        for (int i = begin; i < begin + offset; i++) {
            if (i < Res.N2.size()) {
                if (Res.N1.get(i) + Res.N2.get(i) > 9) {
                    Res.c.set(i, 'C');
                } else if (Res.N1.get(i) + Res.N2.get(i) == 9) {
                    Res.c.set(i, 'M');
                } else {
                    Res.c.set(i, 'N');
                }
            } else {
                if (Res.N1.get(i) == 9) {
                    Res.c.set(i, 'M');
                } else {
                    Res.c.set(i, 'N');
                }
            }
        }
    }
}
