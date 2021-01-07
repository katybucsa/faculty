/**
 * Created on: 16-Oct-19, 22:19
 *
 * @author: Katy Bucșa
 */
class MyThreadCarry implements Runnable {
    private int begin;
    private int offset;

    public MyThreadCarry(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }

    @Override
    public void run() {
        int s;
        for (int i = begin; i < begin + offset; i++) {
            if (i < Res.N2.size()) {
                s = Res.N1.get(i) + Res.N2.get(i);
                Res.result.set(i, s % 10);
                Res.carries.set(i, s / 10);
            } else {
                Res.result.set(i, Res.N1.get(i));
            }
        }
    }
}
