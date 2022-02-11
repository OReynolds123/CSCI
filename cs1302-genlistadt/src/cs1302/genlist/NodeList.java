package cs1302.genlist;

/**
 * A {@code node} provides a generic node for a linked list. Each {@code node} contains an
 * {@link Object} reference and a link to the next {@code Node} in the list. All objects in a
 * {@code node} must be of the same type.
 *
 */
public class NodeList<E> {
    private E object;
    private NodeList<E> next;

    /**
     * Constructs a new node with all properties unset.
     */
    public NodeList() {
        object = null;
        next = null;
    } // NodeList

    /**
     * Constructs a new node with the {@code object} property set and the {@code next} property
     * unset.
     * @param obj The reference to the object being set.
     */
    public NodeList(E obj) {
        object = obj;
        next = null;
    } // NodeList

    /**
     * Constructs a new node with the {@code object} and {@code next} properties set.
     * @param obj The reference to the object being set.
     * @param nextNode The reference to the next node.
     */
    public NodeList(E obj, NodeList<E> nextNode) {
        object = obj;
        next = nextNode;
    } // NodeList

    /**
     * Returns the value of the {@code next} property for this node.
     * @return nextNode The reference to the next node.
     */
    public NodeList<E> getNext() {
        return next;
    } // getNext

    /**
     * Returns the value of the {@code object} property for this node.
     * @return object The reference to the associated object.
     */
    public E getObj() {
        return object;
    } // getObject

    /**
     * Sets the value of the {@code next} property for this node.
     * @param nextNode The reference to the next node.
     */
    public void setNext(NodeList<E> nextNode) {
        next = nextNode;
    } // setNext

    /**
     * Sets the value of the {@code object} property for this node.
     * @param obj The reference to the object being set.
     */
    public void setObj(E obj) {
        object = obj;
    } // setObj

} // NodeList
