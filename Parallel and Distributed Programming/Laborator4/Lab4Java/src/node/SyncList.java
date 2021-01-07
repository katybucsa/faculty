package node;

import monomial.Monomial;
import node.Node;

/**
 * Created on: 14-Nov-19, 17:46
 *
 * @author: Katy Bucșa
 */

public class SyncList {

    private Node root;

    public SyncList() {

        this.root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public synchronized void aduna_monom(Monomial m) {

        if (this.root == null) {
            this.root = new Node(m.getCoefficient(), m.getExponent());
        } else {
            INode current = this.root;
            INode previous = this.root;
            while (current.getExponent() > m.getExponent() && current.getNext() != null) {
                previous = current;
                current = current.getNext();
            }
            if (current.getExponent() == m.getExponent()) {//equal exponents
                current.setCoeficient(current.getCoeficient() + m.getCoefficient());
            } else if (current.getExponent() < m.getExponent()) {//insert between 2 nodes or change root node
                Node nou = new Node(m.getCoefficient(), m.getExponent());
                if (current == this.root) {
                    nou.setNext(this.root);
                    this.root = nou;
                } else {
                    nou.setNext(previous.getNext());
                    previous.setNext(nou);
                }
            } else {//add in the end of the list
                Node nou = new Node(m.getCoefficient(), m.getExponent());
                current.setNext(nou);
            }
        }
    }

    public void print_list() {

        INode curent = this.root;
        while (curent != null) {
            if (curent.getCoeficient() == 0) {
                curent = curent.getNext();
            } else {
                if (curent.getExponent() == 1) {
                    System.out.print(curent.getCoeficient() + " X");
                } else if (curent.getExponent() == 0) {
                    System.out.print(curent.getCoeficient());
                } else {
                    System.out.print(curent.getCoeficient() + " X^" + curent.getExponent());
                }
                curent = curent.getNext();
                if (curent != null) {
                    System.out.print(" + ");
                }
            }
        }
    }
}
