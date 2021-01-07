/**
 * Created on: 10-Dec-19, 16:12
 *
 * @author: Katy Bucșa
 */

public class Coada {

    private Node first;
    private Node last;
    private int size = 0;

    public synchronized boolean isEmpty() {

        return first == null;
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return size >= Elems.n;
    }

    public Integer push(int number) {

        if (first == null) {
            first = new Node(number);
            last = first;
            size++;
            return number;
        }

        last.next = new Node(number);
        last = last.next;
        size++;
        return number;
    }

    public Integer popFirst() {

        if (first == null) {
            return null;
        }
        Integer number = first.number;
        first = first.next;
        if (first == null)
            last = null;
        size--;
        if (size < 0) {
            size = 0;
        }
        return number;
    }

}
