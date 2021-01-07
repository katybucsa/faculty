/**
 * Created on: 16-Oct-19, 22:19
 *
 * @author: Katy Bucșa
 */
class MyThreadFinal implements Runnable {
    private int begin;
    private int offset;

    public MyThreadFinal(int begin, int offset) {
        this.begin = begin;
        this.offset = offset;
    }


    @Override
    public void run() {
        for (int i = begin; i < begin + offset; i++) {
            int s;
            if (i == 0 && Res.carries.size() > Res.N1.size()) {
                Res.result.add(Res.result.size(), Res.carries.get(Res.carries.size() - 1));
            }
            if (i < Res.N2.size()) {
                s = (Res.N1.get(i) + Res.N2.get(i) + Res.carries.get(i)) % 10;
            } else {
                s = (Res.N1.get(i) + Res.carries.get(i)) % 10;

            }
            Res.result.set(i, s);
        }

    }
}
