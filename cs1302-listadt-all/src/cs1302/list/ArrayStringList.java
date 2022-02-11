package cs1302.list;

import cs1302.listadt.StringList;

/**
 * Uses an internal array for this class's storage.
 */
public class ArrayStringList extends AbstractStringList {
    String[] asl;
    int size;

    /**
     * The default constructor for {@code ArrayStringList}. Initializes the internal array
     * storage to 5 and sets the size of the list to 0.
     */
    ArrayStringList() {
        this.asl = new String[5]; // default internal array size is 5
        this.size = 0; // size is 0
    } // ArrayStringList

    /**
     * The overloaded constructor for {@code ArrayStringList}. Creates a deep copy of the list from
     * the parameter.
     * @param other A {@link StringList} to copy.
     */
    ArrayStringList(StringList other) {
        checkStringListNull(other); // checks if the stringlist is null
        String [] slArray = other.toArray(); // convert stringlist to an array
        this.asl = new String[slArray.length]; // set asl length to stringlist length
        this.size = slArray.length; // size equals stringlist length
        for (int i = 0; i < this.asl.length; i++) {
            this.asl[i] = slArray[i]; // deep copy of the stringlist
        } // for
    } // ArrayStringList

    /**
     * {@inheritDoc}
     */
    public boolean add(int index, String s) {
        checkStringNull(s); // checks if the string is null
        checkStringEmpty(s); // checks if the string is empty
        checkIndexBounds(index); // checks if the index is in bounds
        checkLength(); // checks if the array size need to increase
        String [] temp = new String[this.asl.length + 1];
        for (int i = 0; i < this.asl.length - 1; i++) {
            temp[i] = this.asl[i]; // create a temp array
        } // for
        this.asl = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            if (i < index) {
                this.asl[i] = temp[i]; // store up to index
            } else if (i == index) {
                this.asl[i] = s; // insert string
            } else {
                this.asl[i] = temp[i - 1]; // store the rest
            } // if
        } // for
        this.size++; // increase size
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
        String [] slArray = sl.toArray(); // convert to array
        String [] temp = new String[this.asl.length + slArray.length];
        for (int i = 0; i < index; i++) {
            temp[i] = this.asl[i]; // store up to index
        } // for
        for (int i = 0; i < slArray.length; i++) {
            temp[i + index] = slArray[i]; // store the stringlist elements
            this.size++;
        } // for
        for (int i = 0; i < this.asl.length - index; i++) {
            temp[i + index + slArray.length] = this.asl[i + index]; // store the rest
        } // for
        this.asl = new String[temp.length];
        for (int i = 0; i < this.asl.length; i++) {
            this.asl[i] = temp[i]; // put back into asl
        } // for
        return true;
    } // add

    /**
     * {@inheritDoc}
     */
    public StringList distinct() {
        StringList distinctASL = new ArrayStringList();
        String [] tempArr = this.toArray();
        for (int i = 0; i < tempArr.length - 1; i++) {
            for (int j = i + 1; j < tempArr.length; j++) {
                if (tempArr[i] != null && tempArr[i].equals(tempArr[j])) {
                    tempArr[j] = null; // check for matching elements
                } // if
            } // for
        } // for
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] != null) {
                distinctASL.add(tempArr[i]); // add the nonmatching elements to the new array
            } // if
        } // for
        return distinctASL;
    } // distinct

    /**
     * {@inheritDoc}
     */
    public String remove(int index) {
        checkIndexBounds(index);
        String removedString = this.asl[index]; // store the removed string
        for (int i = 0; i < this.asl.length - 2; i++) {
            if (i >= index) {
                this.asl[i] = this.asl[i + 1]; // shift everything left
            } // if
        } // for
        this.size--; // decrease size
        return removedString;
    } // remove

    /**
     * {@inheritDoc}
     */
    public StringList reverse() {
        StringList reversedASL = new ArrayStringList();
        for (int i = this.size - 1; i >= 0; i--) {
            reversedASL.add(this.asl[i]); // add the elements in reverse order
        } // for
        return reversedASL;
    } // reverse

    /**
     * {@inheritDoc}
     */
    public String set(int index, String s) {
        checkStringNull(s); // checks if the string is null
        checkStringEmpty(s); // checks if the string is empty
        checkIndexBounds(index); // checks if the index is in bounds
        String prevString = this.asl[index]; // get the old string
        this.asl[index] = s; // store the new string
        return prevString;
    } // set

    /**
     * {@inheritDoc}
     */
    public int size() {
        return this.size; // return size
    }

    /**
     * {@inheritDoc}
     */
    public StringList splice(int fromIndex, int toIndex) {
        checkIndexesBounds(fromIndex, toIndex); // check if the indexes are in bounds
        StringList splicedASL = new ArrayStringList();
        for (int i = fromIndex; i < toIndex; i++) {
            splicedASL.add(this.asl[i]); // add the elements to the array
        } // for
        return splicedASL;
    } // splice

    /**
     * {@inheritDoc}
     */
    public String[] toArray() {
        String [] newArray = new String[this.size];
        for (int i = 0; i < newArray.length; i++) {
            if (this.asl[i] != null) {
                newArray[i] = this.asl[i]; // gets each element
            } // if
        } // for
        return newArray;
    } // toArray

    /**
     * A method to check if the array needs to be increased in size.
     */
    void checkLength() {
        if (getEmptyIndex() == -1) {
            arraySizeIncrease();
        } // if
    } // checkLength

    /**
     * Increases the array size by {@code size} * 1.5 by using a temp array.
     */
    void arraySizeIncrease() {
        String [] temp = new String[this.asl.length + ((int)(this.size * 1.5))]; // size * 1.5
        for (int i = 0; i < this.asl.length; i++) {
            temp[i] = this.asl[i]; // store in a temp array
        } // for
        this.asl = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            this.asl[i] = temp[i]; // put it back into asl
        } // for
    } // arraySizeIncrease

    /**
     * Checks the array for any null elements and returns first one if found.
     * @return emptyIndex The index of the first null space to be filled. If none are found, -1
     * is returned.
     */
    int getEmptyIndex() {
        int emptyIndex = -1;
        for (int i = this.asl.length - 1; i >= 0; i--) {
            if (this.asl[i] == null) {
                emptyIndex = i; // gets the index of the first null element
            } // if
        } // for
        return emptyIndex;
    } // getEmptyIndex

} // ArrayStringList
