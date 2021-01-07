import java.util.stream.IntStream;

/**
 * Created on: 09-Dec-19, 22:47
 *
 * @author: Katy Bucșa
 */

public class MyThread implements Runnable {

    private int start;
    private int offset;

    public MyThread(int start, int offset) {
        this.start = start;
        this.offset = offset;
    }

    @Override
    public void run() {

        for (int i = start; i < start + offset; i++) {
            if(i==1){
                System.out.println();
            }
            int s = IntStream.rangeClosed(0, i).map(j -> Elems.A.get(j)).sum();
            int b = IntStream.range(0, Elems.C.size()).map(j -> (int) (Elems.C.get(j) * Math.pow(s, j))).sum();
            Elems.B.set(i, b);
        }
    }
}