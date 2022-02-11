package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.Iterator;

/**
 * A generic iterator class for iterating over a collection of objects. This class takes in a
 * {@code GenList} and turns them into a {@code NodeList} to read through the elements.
 */
public class IteratorList<E> implements Iterator<E> {
    private NodeList<E> loc;

    /**
     * Creates a node list from the {@code GenList<E>} in the parameter.
     * @param ls A {@code GenList} to turn into nodes.
     */
    public IteratorList(GenList<E> ls) {
        NodeList<E> tempNodeHead = new NodeList<E>();
        NodeList<E> tempNode = tempNodeHead;
        for (int i = 0; i < ls.size(); i++) { // puts the genlist into nodes
            tempNode.setObj(ls.get(i));
            tempNode.setNext(new NodeList<E>());
            tempNode = tempNode.getNext();
        } // for
        loc = tempNodeHead;
    } // IteratorList

    /**
     * Checks if the iteration has more elements.
     * @return true if the iteration has more elements, otherwise returns false
     */
    public boolean hasNext() {
        return loc.getNext() != null;
    } // hasNext

    /**
     * Gets the next element in the iteration.
     * @return the next element in the iteration
     */
    public E next() {
        E obj = loc.getObj();
        loc = loc.getNext();
        return obj;
    } // next

} // IteratorList
