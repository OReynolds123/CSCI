package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.Comparator;

/**
 * An abstract class that reduces redundancy for a {@link GenList}.
 */
public abstract class BaseGenList<T> implements GenList<T> {

    /**
     * Appends the objects contained in the specified list to the end of this list, in the order in
     * which they appear in the specified list.
     * <p>
     * {@inheritDoc}
     */
    public <U extends T> boolean add(GenList<U> list) {
        checkListNull(list); // checks if the List is null
        if (list.isEmpty() == true) { // checks if the List is empty
            return false;
        } // if
        this.add(this.size(), list); // uses add(index, list) to reduce redundancy
        return true;
    } // add

    /**
     * Inserts the objects contained in the specified list at the specified position in this list,
     * in the order in which they appear in the specified list.
     * <p>
     * {@inheritDoc}
     */
    public abstract <U extends T> boolean add(int index, GenList<U> list);

    /**
     * Inserts the specified object at the specified position in this list.
     * <p>
     * {@inheritDoc}
     */
    public abstract boolean add(int index, T obj);

    /**
     * Appends the specified object to the end of this list.
     * <p>
     * {@inheritDoc}
     */
    public boolean add(T obj) {
        checkNull(obj); // checks if the object is null
        this.add(this.size(), obj); // uses add(index, obj) to reduce redundancy
        return true;
    } // add

    /**
     * Returns whether all the elements of this list that pass the test specified by the given
     * predicate.
     * <p>
     * {@inheritDoc}
     */
    public boolean allMatch(Predicate<T> p) {
        checkPredicateNull(p); // checks if the predicate is null
        boolean checkMatch = true;
        for (int i = 0; i < this.size(); i++) {
            if (p.test(this.get(i)) != true) { // check if the test is not true
                checkMatch = false; // set false
            } // if
        } // for
        return checkMatch;
    } // allMatch

    /**
     * Returns whether at least one element of this list that passes the test specified by the
     * given predicate.
     * <p>
     * {@inheritDoc}
     */
    public boolean anyMatch(Predicate<T> p) {
        checkPredicateNull(p); // checks if the predicate is null
        boolean checkMatch = false;
        for (int i = 0; i < this.size(); i++) {
            if (p.test(this.get(i)) == true) { // check if the test is true
                checkMatch = true; // set true
            } // if
        } // for
        return checkMatch;
    } // anyMatch

    /**
     * Removes all of the objects from this list.
     * <p>
     * {@inheritDoc}
     */
    public void clear() {
        for (int i = this.size() - 1; i >= 0; i--) {
            this.remove(i); // uses remove(index) method to empty the list
        } // for
    } // clear

    /**
     * Returns true if this list contains the specified object.
     * <p>
     * {@inheritDoc}
     */
    public boolean contains(T o) {
        checkNull(o); // checks if the object is null
        boolean checkContains = false;
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i)).equals(o)) { // element.equals(o)
                checkContains = true;
            } // if
        } // for
        return checkContains;
    } // contains

    /**
     * Builds and returns a new {@code GenList<T>} from this list without any duplicate objects.
     * <p>
     * {@inheritDoc}
     */
    public abstract GenList<T> distinct();

    /**
     * Returns a new {@code GenList<T>} consisting of the elements of this list that pass the test
     * specified by the given predicate.
     * <p>
     * {@inheritDoc}
     */
    public abstract GenList<T> filter(Predicate<T> p);

    /**
     * Returns a reference to the object at the specified position in this list.
     * <p>
     * {@inheritDoc}
     */
    public abstract T get(int index);

    /**
     * Returns the index of the first occurrence of the specified object in this list, or -1 if
     * this list does not contain the object.
     * <p>
     * {@inheritDoc}
     */
    public int indexOf(T obj) {
        checkNull(obj); // checks if the object is null
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (index == -1 && obj.equals(get(i))) { // obj.equals(get(i))
                index = i;
            } // if
        } // for
        return index;
    } // indexOf

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
     * Returns an string representation of this list with every object in the sequence separated by
     * the specified separator string.
     * <p>
     * {@inheritDoc}
     */
    public String makeString(String sep) {
        String printString = "";
        for (int i = 0; i < this.size(); i++) {
            if (i <= (this.size() - 2)) {
                printString = printString + get(i) + sep; // add the seperator
            } else if (i == (this.size() - 1)) {
                printString = printString + get(i);
            } // if
        } // for
        return printString;
    } // makeString

    /**
     * Returns a new {@code GenList<R>} consisting of elements of this list transformed by the
     * given function.
     * <p>
     * {@inheritDoc}
     */
    public abstract <R> GenList<R> map(Function<T,R> f);

    /**
     * Returns a maximal element of this list according to the provided {@link Comparator}.
     * <p>
     * {@inheritDoc}
     */
    public T max(Comparator<T> c) {
        checkComparatorNull(c); // checks if the comparator is null
        T max = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T val = this.get(i);
            if (c.compare(val, max) >= 0) { // compare the elements
                max = val;
            } // if
        } // for
        return max;
    } // max

    /**
     * Returns a minimal element of this list according to the provided {@link Comparator}.
     * <p>
     * {@inheritDoc}
     */
    public T min(Comparator<T> c) {
        checkComparatorNull(c); // checks if the comparator is null
        T min = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T val = this.get(i);
            if (c.compare(val, min) <= 0) { // compare the elements
                min = val;
            } // if
        } // for
        return min;
    } // min

    /**
     * Returns the result of repeatedly applying some combining operation to the elements of this
     * list.
     * <p>
     * {@inheritDoc}
     */
    public T reduce(T start, BinaryOperator<T> f) {
        if (f == null) { // if function is null
            throw new NullPointerException("Specified binary operator is null.");
        } // if
        T result = start;
        for (int i = 0; i < this.size(); i++) {
            if (result == null) {
                throw new NullPointerException("Function resulted in null.");
            } // if
            result = f.apply(result, this.get(i)); // combining operation
        } // for
        return result;
    } // reduce

    /**
     * Removes the element at the specified position in this list.
     * <p>
     * {@inheritDoc}
     */
    public abstract T remove(int index);

    /**
     * Builds and returns a new {@code GenList<T>} that contains the objects from this list in
     * reverse order.
     * <p>
     * {@inheritDoc}
     */
    public abstract GenList<T> reverse();

    /**
     * Replaces the objects at the specified position in this list with the specified element.
     * <p>
     * {@inheritDoc}
     */
    public abstract T set(int index, T obj);

    /**
     * Returns the number of elements in this list.
     * <p>
     * {@inheritDoc}
     */
    public abstract int size();

    /**
     * Builds and returns a new {@code GenList<T>} that contains the strings from this list between
     * the specified {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
     * <p>
     * {@inheritDoc}
     */
    public abstract GenList<T> splice(int fromIndex, int toIndex);

    /**
     * Returns an array containing all of the objects in this list in proper sequence (from
     * first to last element).
     * <p>
     * {@inheritDoc}
     */
    public abstract T[] toArray(IntFunction<T[]> gen);

    /**
     * Checks if the the inputed object is null and throws a {@link NullPointerException} if true.
     * @param s The object to be checked.
     */
    public void checkNull(T s) {
        if (s == null) {
            throw new NullPointerException("Specified object is null.");
        } // if
    } // checkNull

    /**
     * Checks if the the inputed {@code GenList<U>} is null and throws a
     * {@link NullPointerException} if true.
     * @param list The {@code GenList<U>} to be checked.
     * @param <U> A reference type bounded by T.
     */
    public <U extends T> void checkListNull(GenList<U> list) {
        if (list == null) {
            throw new NullPointerException("Specified List is null.");
        } // if
    } // checkListNull

    /**
     * Checks if the the inputed {@code Predicate<T>} is null and throws a
     * {@link NullPointerException} if true.
     * @param p The {@code Predicate<T>} to be checked.
     */
    public void checkPredicateNull(Predicate<T> p) {
        if (p == null) {
            throw new NullPointerException("Specified predicate is null.");
        } // if
    } // checkPredicateNull

    /**
     * Checks if the the inputed {@code Comparator<T>} is null and throws a
     * {@link NullPointerException} if true.
     * @param c The {@code Comparator<T>} to be checked.
     */
    public void checkComparatorNull(Comparator<T> c) {
        if (c == null) {
            throw new NullPointerException("Specified comparator is null.");
        } // if
    } // checkComparatorNull

    /**
     * Checks if the the inputed index is out of bounds and throws an
     * {@link IndexOutOfBoundsException} if true.
     * Index is out of bounds if index is less than 0 or greater than this.size().
     * @param index The index to be checked.
     */
    public void checkIndexBounds(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Specified index is out of bounds.");
        } // if
    } // checkIndexBounds

    /**
     * Checks if the the inputed index is out of bounds and throws an
     * {@link IndexOutOfBoundsException} if true.
     * Index is out of bounds if index is less than 0 or greater than or equal to this.size().
     * @param index The index to be checked.
     */
    public void checkIndexEqualBounds(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Specified index is out of bounds.");
        } // if
    } // checkIndexEqualBounds

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

} // BaseGenList
