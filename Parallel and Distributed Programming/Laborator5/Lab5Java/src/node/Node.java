package node;

/**
 * Created on: 14-Nov-19, 17:46
 *
 * @author: Katy Bucșa
 */

public class Node implements INode {
    private double coeficient;
    private int exponent;
    private INode next;

    public Node(double coeficient, int exponent) {
        this.coeficient = coeficient;
        this.exponent = exponent;
        this.next = null;
    }

    public double getCoeficient() {
        return this.coeficient;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    public int getExponent() {
        return this.exponent;
    }

    public INode getNext() {
        return this.next;
    }

    public void setNext(INode next) {
        this.next = next;
    }
}