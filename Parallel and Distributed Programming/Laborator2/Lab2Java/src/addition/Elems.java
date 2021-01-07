package addition;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Elems {
    public static List<List<Integer>> elems;
    public static List<Character> C;
    public static List<Integer> CARRIES;
    public static List<Integer> N1;
    public static List<Integer> N2;
    public static List<Integer> RESULT;
    public static int MAX_LENGTH = 0;
    public static ReentrantLock lock = new ReentrantLock();
    public static String VARIANT = "S";

}