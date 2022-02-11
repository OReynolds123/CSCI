package cs1302.list;

import cs1302.listadt.StringList;

/**
 * Uses a sequence of {@link StringList.Node} objects for this class's storage.
 */
public class LinkedStringList extends AbstractStringList {
    StringList.Node head;
    int size;

    /**
     * The default constructor for {@code LinkedStringList}. Sets the size of the list to 0.
     */
    LinkedStringList() {
        this.size = 0; // size is 0
    } // LinkedStringList

    /**
     * Creates a deep copy of the list from the parameter.
     * @param other A {@link StringList} to copy.
     */
    LinkedStringList(StringList other) {
        checkStringListNull(other); // checks if the stringlist is null
        String [] tempArr = other.toArray(); // convert stringlist to an array
        this.size = tempArr.length; // sets size
        head = new StringList.Node(tempArr[0]); // sets head reference
        StringList.Node tempNode = head;
        for (int i = 1; i < tempArr.length; i++) {
            tempNode.setNext(new StringList.Node(tempArr[i])); // adds each element
            tempNode = tempNode.getNext();
        } // for
    } // LinkedStringList

    /**
     * {@inheritDoc}
     */
    public boolean add(int index, String s) {
        checkStringNull(s); // checks if the string is null
        checkStringEmpty(s); // checks if the string is empty
        checkIndexBounds(index); // checks if the index is in bounds
        if (this.size == 0) {
            head = new StringList.Node(s); // sets head reference
        } else if (index == 0) {
            StringList.Node tempNode = head;
            StringList.Node newNode = new StringList.Node(s, tempNode); // makes node with s
            head = newNode;
        } else {
            StringList.Node newNode = new StringList.Node(s);
            StringList.Node tempNode = head;
            for (int i = 1; i < index; i++) {
                tempNode = tempNode.getNext();
            } // for
            newNode.setNext(tempNode.getNext());
            tempNode.setNext(newNode);
        } // if
        this.size++;
        return true;
    } // add

    /**
     * {@inheritDoc}
     */
    public boolean add(int index, StringList sl) {
        checkStringListNull(sl); // checks if the stringlist is null
        checkIndexBounds(index); // checks if the index is in bounds
        if (sl.isEmpty() == true) { // checks if the stringlist is empty
            return false;
        } // if
        String [] tempArr = sl.toArray(); // stringlist to an array
        this.size += tempArr.length; // sets size
        if (this.size == 0) {
            head = new StringList.Node(tempArr[0]);
            StringList.Node tempNode = head;
            for (int i = 1; i < tempArr.length; i++) {
                tempNode.setNext(new StringList.Node(tempArr[i]));
                tempNode = tempNode.getNext();
            } // for
        } else if (index == 0) {
            StringList.Node tempNodeHead = new StringList.Node(tempArr[0]);
            StringList.Node tempNode = tempNodeHead;
            for (int i = 1; i < tempArr.length; i++) {
                tempNode.setNext(new StringList.Node(tempArr[i]));
                tempNode = tempNode.getNext();
            } // for
            tempNode.setNext(head);
            head = tempNodeHead;
        } else {
            StringList.Node nodeToIndex = head;
            for (int i = 1; i < index; i++) { // 0
                nodeToIndex = nodeToIndex.getNext();
            } // for
            StringList.Node tempSLNodeHead = new StringList.Node(tempArr[0]); // set temp head
            StringList.Node tempSLNode = tempSLNodeHead;
            for (int i = 1; i < tempArr.length; i++) {
                tempSLNode.setNext(new StringList.Node(tempArr[i])); // create each element node
                tempSLNode = tempSLNode.getNext();
            } // for
            tempSLNode.setNext(nodeToIndex.getNext());
            nodeToIndex.setNext(tempSLNodeHead);
        } // if
        return true;
    } // add

    /**
     * {@inheritDoc}
     */
    public StringList distinct() {
        StringList distinctLSL = new LinkedStringList();
        String [] tempArr = this.toArray(); // convert to array
        for (int i = 0; i < tempArr.length - 1; i++) {
            for (int j = i + 1; j < tempArr.length; j++) {
                if (tempArr[i] != null && tempArr[i].equals(tempArr[j])) {
                    tempArr[j] = null; // check each element for duplicates
                } // if
            } // for
        } // for
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] != null) {
                distinctLSL.add(tempArr[i]); // add the nonduplicates to a new array
            } // if
        } // for
        return distinctLSL;
    } // distinct

    /**
     * {@inheritDoc}
     */
    public String remove(int index) {
        checkIndexBounds(index); // checks if the index is in bounds
        String removedString = "";
        if (index == 0) {
            removedString = head.getStr();
            head = head.getNext(); // changes head value to next
        } else {
            StringList.Node tempNode = head;
            for (int i = 1; i < index; i++ ) {
                tempNode = tempNode.getNext();
            } // for
            removedString = tempNode.getNext().getStr(); // sets removed string
            tempNode.setNext(tempNode.getNext().getNext()); // shifts the nodes
        } // if
        this.size--; // decreases size
        return removedString;
    } // remove

    /**
     * {@inheritDoc}
     */
    public StringList reverse() {
        StringList reversedLSL = new LinkedStringList();
        String [] tempArr = this.toArray(); // creates a temp array
        for (int i = this.size - 1; i >= 0; i--) {
            reversedLSL.add(tempArr[i]); // adds each node in reverse order
        } // for
        return reversedLSL;
    } // reverse

    /**
     * {@inheritDoc}
     */
    public String set(int index, String s) {
        checkStringNull(s); // checks if the string is null
        checkStringEmpty(s); // checks if the string is empty
        checkIndexBounds(index); // checks if the index is in bounds
        String prevString = "";
        if (index == 0) {
            prevString = head.getStr(); // gets the old string value
            head.setStr(s); // changes the string at the node
        } else {
            StringList.Node tempNode = head;
            for (int i = 0; i < index; i++) {
                tempNode = tempNode.getNext(); // gets location of the node to change
            } // for
            prevString = tempNode.getStr(); // gets the old string value
            tempNode.setStr(s); // changes the string at the node
        } // if
        return prevString;
    } // set

    /**
     * {@inheritDoc}
     */
    public int size() {
        return this.size;
    } // size

    /**
     * {@inheritDoc}
     */
    public StringList splice(int fromIndex, int toIndex) {
        checkIndexesBounds(fromIndex, toIndex); // checks if the indexes are in bounds
        StringList splicedLSL = new LinkedStringList();
        String [] tempArr = this.toArray(); // creates an array
        for (int i = fromIndex; i < toIndex; i++) {
            splicedLSL.add(tempArr[i]); // gets the nodes within the index
        } // for
        return splicedLSL;
    } // splice

    /**
     * {@inheritDoc}
     */
    public String[] toArray() {
        String [] newArray = new String[this.size];
        StringList.Node tempNode = head;
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = tempNode.getStr(); // gets the string value at each node
            tempNode = tempNode.getNext();
        } // for
        return newArray;
    } // toArray
} // LinkedStringList
