package node;

/**
 * Created on: 23-Nov-19, 19:25
 *
 * @author: Katy Bucșa
 */

public interface INode {
    double getCoeficient();

    void setCoeficient(double coeficient);

    int getExponent();

    INode getNext();

    void setNext(INode next);
}
