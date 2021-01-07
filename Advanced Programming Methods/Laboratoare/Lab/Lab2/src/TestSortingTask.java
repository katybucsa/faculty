import domain.SortingTask;
import domain.Strategy;

import java.util.Vector;

public class TestSortingTask {
    public static void main(String[] args) {
        Vector<Integer> vect=new Vector<Integer>();
        vect.add(7);
        vect.add(-3);
        vect.add(324);
        vect.add(-234);
        vect.add(43);
        vect.add(-1);
        vect.add(53);
        Strategy s=Strategy.valueOf(args[0]);
        SortingTask st=new SortingTask(vect,s);
        st.execute();
    }
}
