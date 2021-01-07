import monomial.Monomial;
import node.Lista;
import node.SyncList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on: 14-Nov-19, 18:03
 *
 * @author: Katy Bucșa
 */
public class Elems {
    public static SyncList syncList = new SyncList();
    public static Lista lista = new Lista();
    public static Queue<Monomial> QUEUE = new LinkedList<>();
    public static Integer NO_FILE = 1;
    public static boolean FINISHED = false;
    public static Lock mutex = new ReentrantLock();
}