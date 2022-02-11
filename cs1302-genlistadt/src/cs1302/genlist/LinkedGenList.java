package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Uses a sequence of {@link NodeList} objects for this class's storage.
 */
public class LinkedGenList<T> extends BaseGenList<T> {
    private NodeList<T> head;
    private int size;

    /**
     * The default constructor for {@code LinkedGenList<T>}. Sets the size of the list to 0.
     */
    public LinkedGenList() {
        this.size = 0; // size is 0
    } // LinkedGenList

    /**
     * Creates a deep copy of the list from the parameter.
     * @param other A {@link LinkedGenList} to copy.
     * @param <U> A reference type bounded by T.
     */
    public <U extends T> LinkedGenList(GenList<U> other) {
        checkListNull(other); // checks if the list is null
        NodeList<T> tempNodeHead = new NodeList<T>();
        NodeList<T> tempNode = tempNodeHead;
        for (int i = 0; i < other.size(); i++) {
            tempNode.setObj(other.get(i));
            tempNode.setNext(new NodeList<T>());
            tempNode = tempNode.getNext();
        } // for
        head = tempNodeHead;
        this.size = other.size();
    } // LinkedList

    /**
     * {@inheritDoc}
     */
    public <U extends T> boolean add(int index, GenList<U> list) {
        checkListNull(list); // checks if the list is null
        checkIndexBounds(index); // checks if the index is in bounds
        if (list.isEmpty() == true) { // checks if the list is empty
            return false;
        } // if
        NodeList<T> tempNodeHead = new NodeList<T>(); // head of the list
        NodeList<T> tempNode = tempNodeHead;
        NodeList<T> tempNodeOneBack = tempNode;
        for (int i = 0; i < list.size(); i++) { // putting the list into nodes
            tempNode.setObj(list.get(i));
            tempNode.setNext(new NodeList<T>());
            tempNodeOneBack = tempNode;
            tempNode = tempNode.getNext();
        } // for
        this.size += list.size(); // sets size
        if (this.size == 0) { // add the inputed list to an empty list
            head = tempNodeHead; // set head to the inputed list
        } else if (index == 0) { // add the inputed list at index 0
            tempNodeOneBack.setNext(head); // set the inputed list's next reference to head
            head = tempNodeHead; // set head to the inputed list
        } else { // add the inputed list at an index greater than 0
            NodeList<T> nodeToIndex = head;
            for (int i = 1; i < index; i++) { // go to the index
                nodeToIndex = nodeToIndex.getNext();
            } // for
            tempNodeOneBack.setNext(nodeToIndex.getNext());
            nodeToIndex.setNext(tempNodeHead); // insert the inputed list
        } // if
        return true;
    } // add

    /**
     * {@inheritDoc}
     */
    public boolean add(int index, T obj) {
        checkNull(obj); // checks if the object is null
        checkIndexBounds(index); // checks if the index is in bounds
        if (this.size == 0) { // add obj to an empty list
            head = new NodeList<T>(obj); // sets head reference
        } else if (index == 0) { // add obj at index 0
            NodeList<T> tempNode = head;
            NodeList<T> newNode = new NodeList<T>(obj, tempNode); // makes node with obj
            head = newNode;
        } else { // add obj at an index greater than 0
            NodeList<T> newNode = new NodeList<T>(obj); // put the object in a node
            NodeList<T> tempNode = head;
            for (int i = 1; i < index; i++) {
                tempNode = tempNode.getNext(); // go to the index in the list
            } // for
            newNode.setNext(tempNode.getNext());
            tempNode.setNext(newNode); // insert the object node in the list
        } // if
        this.size++;
        return true;
    } // add

    /**
     * {@inheritDoc}
     */
    public GenList<T> distinct() {
        GenList<T> lgl = new LinkedGenList<T>();
        for (int i = 0; i < this.size(); i++) {
            lgl.add(this.get(i)); // create a duplicated list
        } // for
        for (int i = 0; i < lgl.size() - 1; i++) {
            for (int j = i + 1; j < lgl.size(); j++) {
                if (lgl.get(i).equals(lgl.get(j))) { // check each element for duplicates
                    lgl.remove(j); // remove the duplicated element
                    j--;
                } // if
            } // for
        } // for
        return lgl;
    } // distinct

    /**
     * {@inheritDoc}
     */
    public GenList<T> filter(Predicate<T> p) {
        checkPredicateNull(p); // checks if the predicate is null
        GenList<T> filterLGL = new LinkedGenList<T>();
        for (int i = 0; i < this.size(); i++) {
            if (p.test(this.get(i)) == true) { // check if the test is true
                filterLGL.add(this.get(i)); // add the element to the new list
            } // if
        } // for
        return filterLGL;
    } // filter

    /**
     * {@inheritDoc}
     */
    public T get(int index) {
        checkIndexEqualBounds(index); // checks if the index is in bounds
        NodeList<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.getNext(); // go to the index
        } // for
        return tempNode.getObj();
    } // get

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new IteratorList<T>(this);
        return it;
    } // iterator

    /**
     * {@inheritDoc}
     */
    public <R> GenList<R> map(Function<T,R> f) {
        if (f == null) { // if function is null
            throw new NullPointerException("Specified function is null.");
        } // if
        GenList<R> mapLGL = new LinkedGenList<R>();
        for (int i = 0; i < this.size(); i++) {
            if (f.apply(this.get(i)) == null) { // if the function results in null
                throw new NullPointerException("Function resulted in null.");
            } // if
            mapLGL.add(f.apply(this.get(i))); // add the mapped element to the new list
        } // for
        return mapLGL;
    } // map

    /**
     * {@inheritDoc}
     */
    public T remove(int index) {
        checkIndexEqualBounds(index); // checks if the index is in bounds
        NodeList<T> tempNode = head;
        for (int i = 0; i < index - 1; i++) {
            tempNode = tempNode.getNext(); // go to the index
        } // for
        T removedObj =  tempNode.getNext().getObj(); // get the element
        tempNode.setNext(tempNode.getNext().getNext()); // remove the element
        this.size--; // decrease size
        return removedObj;
    } // remove

    /**
     * {@inheritDoc}
     */
    public GenList<T> reverse() {
        GenList<T> reversedLGL = new LinkedGenList<T>();
        for (int i = this.size - 1; i >= 0; i--) {
            reversedLGL.add(this.get(i)); // add each element in reversed order
        } // for
        return reversedLGL;
    } // reverse

    /**
     * {@inheritDoc}
     */
    public T set(int index, T obj) {
        checkNull(obj);
        checkIndexEqualBounds(index); // check if the index is in bounds.
        NodeList<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.getNext();
        } // for
        T setObj = tempNode.getObj();
        tempNode.setObj(obj);
        return setObj;
    } // set

    /**
     * {@inheritDoc}
     */
    public int size() {
        int returnedSize = 0;
        if (this.size > Integer.MAX_VALUE) {
            returnedSize = Integer.MAX_VALUE;
        } else {
            returnedSize = this.size;
        } // if
        return returnedSize;
    } // size

    /**
     * {@inheritDoc}
     */
    public GenList<T> splice(int fromIndex, int toIndex) {
        checkIndexesBounds(fromIndex, toIndex); // checks if the indexes are in bounds
        GenList<T> splicedLGL = new LinkedGenList<T>();
        for (int i = fromIndex; i < toIndex; i++) {
            splicedLGL.add(this.get(i)); // gets the nodes within the index
        } // for
        return splicedLGL;
    } // splice

    /**
     * {@inheritDoc}
     */
    public T[] toArray(IntFunction<T[]> gen) {
        NodeList<T> tempNode = head;
        T [] newArray = gen.apply(this.size());
        for (int i = 0; i < this.size(); i++) {
            newArray[i] = tempNode.getObj();
            tempNode = tempNode.getNext();
        } // for
        return newArray;
    } // toArray

} // LinkedGenList
