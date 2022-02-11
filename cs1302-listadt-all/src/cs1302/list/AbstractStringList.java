package cs1302.list;

import cs1302.listadt.StringList;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;

/**
 * An abstract class that reduces redundancy between {@link LinkedStringList} and
 * {@link ArrayStringList} classes.
 */
public abstract class AbstractStringList implements StringList {

    /**
     * Inserts the specified string at the specified position in this list.
     * <p>
     * {@inheritDoc}
     */
    public abstract boolean add(int index, String s);

    /**
     * Inserts the strings contained in the specified list at the specified position in this list,
     * in the order in which they appear in the specified list.
     * <p>
     * {@inheritDoc}
     */
    public abstract boolean add(int index, StringList sl);

    /**
     * Appends the specified string to the end of this list.
     * <p>
     * {@inheritDoc}
     */
    public boolean add(String s) {
        checkStringNull(s); // checks if the string is null
        this.add(this.size(), s); // uses add(index, string) to reduce redundancy
        return true;
    } // add

    /**
     * Appends the strings contained in the specified list to the end of this list, in the order in
     * which they appear in the specified list.
     * <p>
     * {@inheritDoc}
     */
    public boolean add(StringList sl) {
        checkStringListNull(sl); // checks if the StringList is null
        if (sl.isEmpty() == true) { // checks if the StringList is empty
            return false;
        } // if
        this.add(this.size(), sl); // uses add(index, stringlist) to reduce redundancy
        return true;
    } // add

    /**
     * Removes all of the strings from this list.
     * <p>
     * {@inheritDoc}
     */
    public void clear() {
        for (int i = this.size() - 1; i >= 0; i--) {
            this.remove(i); // uses remove(index) method to empty the list
        } // for
    } // clear

    /**
     * Returns true if this list contains the specified string.
     * <p>
     * {@inheritDoc}
     */
    public boolean contains(String o) {
        checkStringNull(o); // checks if the string is null
        checkStringEmpty(o); // checks if the string is empty
        boolean checkContains = false;
        String [] checkStr = this.toArray();
        for (int i = 0; i < this.size(); i++) {
            if (o.equals(checkStr[i])) { // o.equals(listElement)
                checkContains = true;
            } // if
        } // for
        return checkContains;
    } // contains

    /**
     * Returns true if this list contains the specified string, ignoring case.
     * <p>
     * {@inheritDoc}
     */
    public boolean containsIgnoreCase(String o) {
        checkStringNull(o); // checks if the string is null
        checkStringEmpty(o); // checks if the string is empty
        boolean checkContains = false;
        String [] tempArr = this.toArray();
        for (int i = 0; i < tempArr.length; i++) {
            if (o.equalsIgnoreCase(tempArr[i])) { // o.equalsIgnoreCase(listElement)
                checkContains = true;
            } // if
        } // for
        return checkContains;
    } // containsIgnoreCase

    /**
     * Returns true if any string in this list contains the specified substring.
     * <p>
     * {@inheritDoc}
     */
    public boolean containsSubstring(String o) {
        checkStringNull(o); // checks if the string is null
        checkStringEmpty(o); // checks if the string is empty
        boolean checkContains = false;
        String [] tempArr = this.toArray();
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i].contains(o)) { // listElement.contains(o)
                checkContains = true;
            } // if
        } // for
        return checkContains;
    } // containsSubstring

    /**
     * Builds and returns a new {@link StringList} from this list without any duplicate strings.
     * <p>
     * {@inheritDoc}
     */
    public abstract StringList distinct();

    /**
     * Returns the string at the specified position in this list.
     * <p>
     * {@inheritDoc}
     */
    public String get(int index) {
        checkIndexBounds(index); // check index bounds
        String [] tempArr = this.toArray(); // convert to array
        return tempArr[index]; // get element at index from the array
    } // get

    /**
     * Returns the index of the first occurrence of the specified string in this list, or -1 if
     * this list does not contain the string.
     * <p>
     * {@inheritDoc}
     */
    public int indexOf(String s) {
        checkStringNull(s); // checks if the string is null
        checkStringEmpty(s); // checks if the string is empty
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (index == -1 && s.equals(get(i))) { // s.equals(get(i))
                index = i;
            } // if
        } // for
        return index;
    } // indexOf

    /**
     * Returns the index of the first occurrence of the specified string, ignoring case, in this
     * list, or -1 if this list does not contain the string.
     * <p>
     * {@inheritDoc}
     */
    public int indexOfIgnoreCase(String s) {
        checkStringNull(s); // checks if the string is null
        checkStringEmpty(s); // checks if the string is empty
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (index == -1 && s.equalsIgnoreCase(get(i))) { // s.equalsIgnoreCase(get(i))
                index = i;
            } // if
        } // for
        return index;
    } // indexOfIgnoreCase

    /**
     * Returns true if this list contains no elements.
     * <p>
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        boolean checkEmpty = false;
        if (this.size() == 0) { // if size is empty
            checkEmpty = true;
        } // if
        return checkEmpty;
    } // isEmpty

    /**
     * Returns a string representation of this list with every string in the sequence separated by
     * the specified separator string.
     * <p>
     * {@inheritDoc}
     */
    public String makeString(String sep) {
        String printString = "";
        for (int i = 0; i < this.size(); i++) {
            if (i <= (this.size() - 2)) {
                printString = printString + get(i) + sep;
            } else if (i == (this.size() - 1)) {
                printString = printString + get(i);
            } // if
        } // for
        return printString;
    } // makeString

    /**
     * Removes the string at the specified position in this list.
     * <p>
     * {@inheritDoc}
     */
    public abstract String remove(int index);

    /**
     * Builds and returns a new {@link StringList} that contains the strings from this list in
     * reverse order.
     * <p>
     * {@inheritDoc}
     */
    public abstract StringList reverse();

    /**
     * Replaces the string at the specified position in this list with the specified element.
     * <p>
     * {@inheritDoc}
     */
    public abstract String set(int index, String s);

    /**
     * Returns the number of elements in this list.
     * <p>
     * {@inheritDoc}
     */
    public abstract int size();

    /**
     * Builds and returns a new {@link StringList} that contains the strings from this list between
     * the specified {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
     * <p>
     * {@inheritDoc}
     */
    public abstract StringList splice(int fromIndex, int toIndex);

    /**
     * Returns a new array containing all of the strings in this list in proper sequence (from
     * first to last element).
     * <p>
     * {@inheritDoc}
     */
    public abstract String[] toArray();

    /**
     * Checks if the the inputed string is null and throws a {@link NullPointerException} if true.
     * @param s The string to be checked.
     */
    public void checkStringNull(String s) {
        if (s == null) {
            throw new NullPointerException("Specified string is null.");
        } // if
    } // checkStringNull

    /**
     * Checks if the the inputed {@link StringList} is null and throws a
     * {@link NullPointerException} if true.
     * @param sl The {@link StringList} to be checked.
     */
    public void checkStringListNull(StringList sl) {
        if (sl == null) {
            throw new NullPointerException("Specified StringList is null.");
        } // if
    } // checkStringNull

    /**
     * Checks if the the inputed string is empty and throws an {@link IllegalArgumentException} if
     * true.
     * @param s The string to be checked.
     */
    public void checkStringEmpty(String s) {
        if (s.equals("")) {
            throw new IllegalArgumentException("Specified string is empty.");
        } // if
    } // checkStringEmpty

    /**
     * Checks if the the inputed index is out of bounds and throws an
     * {@link IndexOutOfBoundsException} if true.
     * @param index The index to be checked.
     */
    public void checkIndexBounds(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Specified index is out of bounds.");
        } // if
    } // checkIndexBounds

    /**
     * Checks if the the inputed indexes are out of bounds and throws an
     * {@link IndexOutOfBoundsException} if true.
     * @param fromIndex The first index to be checked.
     * @param toIndex The second index to be checked.
     */
    public void checkIndexesBounds(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > this.size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("One or both of the indexes are invalid.");
        } // if
    } // checkIndexesBounds
} // AbstractStringList
