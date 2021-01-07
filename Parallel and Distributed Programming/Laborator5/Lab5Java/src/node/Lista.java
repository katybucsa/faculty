package node;

import monomial.Monomial;

/**
 * Created on: 16-Nov-19, 17:58
 *
 * @author: Katy Bucșa
 */

public class Lista {
    private SyncNode root;

    public Lista() {
        this.root = null;
    }

    public SyncNode getRoot() {
        return this.root;
    }

    public void setRoot(SyncNode root) {
        this.root = root;
    }

    public void aduna_monom(Monomial m) {

        synchronized (this) {
            if (this.root == null) {
                this.root = new SyncNode(m.getCoefficient(), m.getExponent());
                return;
            }
        }

        INode current, previous;
        synchronized (this.root) {
            current = this.root;
            previous = this.root;
        }
        synchronized (current) {
            synchronized (previous) {
                while (current.getExponent() > m.getExponent() && current.getNext() != null) {
                    previous = current;
                    current = current.getNext();
                }
                if (current.getExponent() == m.getExponent()) {//equal exponents
                    current.setCoeficient(current.getCoeficient() + m.getCoefficient());
                } else if (current.getExponent() < m.getExponent()) {//insert between 2 nodes or change root node
                    SyncNode nou = new SyncNode(m.getCoefficient(), m.getExponent());
                    synchronized (this.root) {
                        if (current == this.root) {
                            nou.setNext(this.root);
                            this.root = nou;
                        } else {
                            nou.setNext(previous.getNext());
                            previous.setNext(nou);
                        }
                    }
                } else {//add in the end of the list
                    SyncNode nou = new SyncNode(m.getCoefficient(), m.getExponent());
                    current.setNext(nou);
                }
            }
        }
    }


    public void print_list() {

        INode curent = this.root;
        while (curent != null) {
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