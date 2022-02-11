package cs1302.list;

import cs1302.listadt.StringList;
import java.util.Iterator;

/**
 * A driver class to test implementations of {@link StringList}.
 * This class should not be submitted.
 *
 * @author Michael Rehman
 */
public class Driver1 {

    /** Reset the color of the prompt. */
    public static final String RESET = "\u001B[0m";
    /** Set color of the prompt to red. */
    public static final String FAIL = "\u001B[31m";
    /** Set color of the propmt to green. */
    public static final String PASS = "\u001B[32m";
    /** Set color of the prompt to yellow. */
    public static final String NEW_TEST = "\u001B[33m";
    /** Set color of the prompt to blue. */
    public static final String NEW_OBJ = "\u001B[34m";
    /** Set color of the prompt to purple. */
    public static final String NEW_SUB_TEST = "\u001B[35m";

    public static void main(String[] args) {
        printKey();

        // There will be a separate method for testing Constructor(StringList)
        // so do not put any methods constructed like that in this array.
        StringList[] slArr = {
            new ArrayStringList(),
            new LinkedStringList()
        }; // slArr

        for (StringList sl : slArr) {
            printObjectType(sl);
            // Comment out the methods you don't want to test.
            testAdd(sl);                        // needs makeString()
            testClear("clear()", sl);           // needs add(String) and makeString()
            testContains(sl);                   // needs add(String)
            testDistinct("distinct()", sl);     // needs add(String) and makeString()
            testGet("get(int)", sl);            // needs add(String)
            testIndexOf(sl);                    // needs add(String)
            testIsEmpty("isEmpty()", sl);       // needs add(String)
            testMakeString(sl);                 // needs add(String)
            testRemove("remove(int)", sl);      // needs add(String) and makeString()
            testReverse("reverse()", sl);       // needs add(String) and makeString()
            testSet("set(int, String)", sl);    // needs add(String) and makeString()
            testSize("size()", sl);             // needs add(String), remove(int), set(int, String)
            testSplice("splice(int, int)", sl); // needs add(String) and makeString()
            testToArray("toArray()", sl);       // needs add(String and makeString()
            testConstructor("Constructor(StringList)", sl); // needs add(String) and makeString()

            // Uncomment to test extra credit.
//            testIterator("iterator()", sl);

            System.out.println();
        } // for
    } // main

    /**
     * Tests each {@code add} method under various conditions.
     * @param sl the {@link StringList} implementation to test
     */
    public static void testAdd(StringList sl) {
        testAddIntString("add(int, String)", sl);
        testAddIntList("add(int, StringList)", sl);
        testAddString("add(String)", sl);
        testAddList("add(StringList)", sl);
    } // testAdd

    /**
     * Test each {@code contains} method under various conditions.
     * @param sl the {@link StringList} implementation to test
     */
    public static void testContains(StringList sl) {
        testContains("contains(String)", sl);
        testContainsIgnoreCase("containsIgnoreCase(String)", sl);
        testContainsSubstring("containsSubstring(String)", sl);
    } // testContains

    /**
     * Test each {@code indexOf} method under various conditions.
     * @param sl the {@link StringList} implementation to test
     */
    public static void testIndexOf(StringList sl) {
        testIndexOf("indexOf(String)", sl);
        testIndexOfIgnoreCase("indexOfIgnoreCase(String)", sl);
    } // testIndexOf

    /**
     * Test each {@code makeString} method under various conditions.
     * @param sl the {@link StringList} implementation to test
     */
    public static void testMakeString(StringList sl) {
        testMakeString("makeString()", sl);
        testMakeStringSep("makeString(String)", sl);
        testMakeStringThree("makeString(String, String, String)", sl);
    } // makeString

    /**
     * Test {@link StringList#add(int, String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testAddIntString(String testName, StringList sl) {
        printTestName(testName);
        String f = "add(%d, %s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, -2, "\"a\"") + ": IOOBE");
            sl.add(-2, "a");
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 1, "\"a\"") + " on an empty list: IOOBE");
            sl.add(1, "a");
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, null) + ": NPE");
            String n = null;
            sl.add(0, n);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, "\"\"") + ": IAE");
            sl.add(0, "");
            printFail(formatException("IAE"));
        } catch (IllegalArgumentException e) {
            printPass(formatException("IAE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, "\"a\"") + ", "
                             + String.format(f, 1, "\"b\"") + ", "
                             + String.format(f, 0, "\"c\"") + ", "
                             + String.format(f, 1, "\"d\"") + ": \"cdab\"");
            if (sl.add(0, "a") && sl.add(1, "b") && sl.add(0, "c") && sl.add(1, "d")
                && sl.makeString().equals("cdab")) {
                printPass("All adds returned true and elements added in correct order");
            } else {
                printFail("An add returned false, makeString(String) is faulty"
                          + ", or the elements were added in the wrong order");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testAddIntString

    /**
     * Test {@link StringList#add(int, StringList)} for proper
     * behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testAddIntList(String testName, StringList sl) {
        printTestName(testName);
        String f = "add(%d, %s)";
        StringList otherSL = reset(sl); // get matching type
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, -2, "otherSL") + ": IOOBE");
            otherSL.add("dummy string");
            sl.add(-2, otherSL);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        otherSL = reset(otherSL);
        try {
            printSubTestName(String.format(f, 1, "otherSL") + " on an empty list: IOOBE");
            otherSL.add("dummy text");
            sl.add(1, otherSL);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, null) + ": NPE");
            StringList n = null;
            sl.add(0, n);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        testAddIntList2(f, sl, otherSL);
    } // testAddIntList

    /**
     * Continues testing {@link StringList#add(int, StringList)}.
     * @param f the format string used to print sub test titles
     * @param sl the {@link StringList} being tested
     * @param otherSL the {@link StringList} used to test {@code sl}
     */
    private static void testAddIntList2(String f, StringList sl, StringList otherSL) {
        sl = reset(sl);
        otherSL = reset(otherSL);
        try {
            printSubTestName(String.format(f, 0, "otherSL") + " where otherSL is empty: false");
            if (!sl.add(0, otherSL)) {
                printPass("Successfully returned false");
            } else {
                printFail("Returned true instead of false");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        sl = reset(sl);
        otherSL = reset(otherSL);
        printSubTestName(String.format(f, 0, "otherSL") + ", "
                         + String.format(f, 1, "anotherSL") + ", "
                         + String.format(f, 0, "yetAnotherSL")
                         + ": \"ydsca\"");
        try {
            StringList anotherSL = reset(sl); // get matching type
            StringList yetAnotherSL = reset(sl); // get matching type
            otherSL.add(0, "a");
            otherSL.add(0, "d");
            anotherSL.add(0, "s");
            anotherSL.add(1, "c");
            yetAnotherSL.add(0, "y");
            if (sl.add(0, otherSL) && sl.add(1, anotherSL) && sl.add(0, yetAnotherSL)
                && sl.makeString().equals("ydsca")) {
                printPass("All adds returned true and elements added in correct order");
            } else {
                printFail("An add returned false, makeString(String) is faulty"
                          + ", or the elements were added in the wrong order");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testAddIntList2

    /**
     * Test {@link StringList#add(String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testAddString(String testName, StringList sl) {
        printTestName(testName);
        String f = "add(%s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "null") + ": NPE");
            String n = null;
            sl.add(n);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"\"") + ": IAE");
            sl.add("");
            printFail(formatException("IAE"));
        } catch (IllegalArgumentException e) {
            printPass(formatException("IAE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"a\"") + ", "
                             + String.format(f, "\"b\"") + ", "
                             + String.format(f, "\"c\"")
                             + ": \"abc\"");
            if (sl.add("a") && sl.add("b") && sl.add("c")
                && sl.makeString().equals("abc")) {
                printPass("All adds returned true and elements added in correct order");
            } else {
                printFail("An add returned false, makeString(String) is faulty"
                          + ", or the elements were added in the wrong order");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testAddString

    /**
     * Test {@link StringList#add(StringList)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testAddList(String testName, StringList sl) {
        printTestName(testName);
        String f = "add(%s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "null") + ": NPE");
            StringList n = null;
            sl.add(n);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "otherSL") + " where otherSL is empty: false");
            StringList otherSL = reset(sl); // get matching type
            if (!sl.add(otherSL)) {
                printPass("Successfully returned false");
            } else {
                printFail("Returned true instead of false");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "otherSL") + ", "
                             + String.format(f, "anotherSL")
                             + ": \"scap\"");
            StringList otherSL = reset(sl); // get matching type
            otherSL.add("s");
            otherSL.add("c");
            StringList anotherSL = reset(sl); // get matching type
            anotherSL.add("a");
            anotherSL.add("p");
            if (sl.add(otherSL) && sl.add(anotherSL)
                && sl.makeString().equals("scap")) {
                printPass("All adds returned true and elements added in correct order");
            } else {
                printFail("An add returned false, makeString(String) is faulty"
                          + ", or the elements were added in the wrong order");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testAddList

    /**
     * Test {@link StringList#clear()} for
     * proper behavior.
     * @param testName the name of the test
     * @param sl the {@link StringList} to test
     */
    public static void testClear(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            sl.add("a");
            sl.add("b");
            sl.add("c");
            sl.clear();
            if (sl.makeString().equals("")) {
                printPass("Successfully cleared the list");
            } else {
                printFail("List was not cleared or makeString() is faulty");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testClear

    /**
     * Test {@link StringList#contains(String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testContains(String testName, StringList sl) {
        printTestName(testName);
        String f = "contains(%s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "null") + ": NPE");
            sl.contains(null);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"\"") + ": IAE");
            sl.contains("");
            printFail(formatException("IAE"));
        } catch (IllegalArgumentException e) {
            printPass(formatException("IAE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"a\"") + ", "
                             + String.format(f, "\"b\"")
                             + " where \"a\" and \"b\" are elements: true");
            sl.add("a");
            sl.add("b");
            if (sl.contains("a") && sl.contains("b")) {
                printPass("Successfully returns true");
            } else {
                printFail("Returned false instead of true");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"A\"")
                             + " where \"A\" is not an element"
                             + " but \"a\" is: false");
            sl.add("a");
            if (!sl.contains("A")) {
                printPass("Successfully returns false");
            } else {
                printFail("Returned true instead of false; "
                          + "ensure that case is not ignored");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // true
    } // testContains

    /**
     * Test {@link StringList#containsIgnoreCase(String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testContainsIgnoreCase(String testName, StringList sl) {
        printTestName(testName);
        String f = "containsIgnoreCase(%s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "null") + ": NPE");
            sl.containsIgnoreCase(null);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"\"") + ": IAE");
            sl.containsIgnoreCase("");
            printFail(formatException("IAE"));
        } catch (IllegalArgumentException e) {
            printPass(formatException("IAE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"a\"") + ", "
                             + String.format(f, "\"A\"")
                             + " where \"a\" is an element: true");
            sl.add("a");
            if (sl.containsIgnoreCase("a") && sl.containsIgnoreCase("A")) {
                printPass("Successfully returns true");
            } else {
                printFail("Returned false instead of true; "
                          + "ensure that case is ignored");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"not added\"")
                             + " where \"not added\" is not an element: false");
            if (!sl.containsIgnoreCase("not added")) {
                printPass("Successfully returns false");
            } else {
                printFail("Returned true instead of false");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testContainsIgnoreCase

    /**
     * Test {@link StringList#containsSubstring(String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testContainsSubstring(String testName, StringList sl) {
        printTestName(testName);
        String f = "containsSubstring(%s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "null") + ": NPE");
            sl.containsSubstring(null);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"\"") + ": IAE");
            sl.containsSubstring("");
            printFail(formatException("IAE"));
        } catch (IllegalArgumentException e) {
            printPass(formatException("IAE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"John\"")
                             + " where \"John Doe\" is an element: true");
            sl.add("John Doe ");
            if (sl.containsSubstring("John")) {
                printPass("Successfully returns true");
            } else {
                printFail("Returned false instead of true; "
                          + "ensure that case is ignored");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"not added\"")
                             + " where \"not added\" is not an element: false");
            if (!sl.containsSubstring("not added")) {
                printPass("Successfully returns false");
            } else {
                printFail("Returned true instead of false");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testContainsSubstring

    /**
     * Test {@link StringList#distinct()} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testDistinct(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            sl.add("a");
            sl.add("a");
            sl.add("A");
            sl.add("b");
            sl.add("B");
            sl.add("B");
            StringList dist = sl.distinct();
            // Uniqueness
            if (dist.makeString().equals("aAbB")) {
                // Same implementation
                if (dist.getClass().getName().equals(sl.getClass().getName())) {
                    // Unique memory
                    dist.add("c");
                    sl.add("s");
                    if (dist.makeString().equals("aAbBc")
                        && sl.makeString().equals("aaAbBBs")) {
                        printPass("Resulting list has no duplicate elements, "
                                  + "matches the object type, "
                                  + "and is unique in memory");
                    } else {
                        printFail("Resulting list has no duplicate elements and "
                                  + "matches the object type, but "
                                  + "is sharing the same data in memory");
                    } // if
                } else {
                    printFail("Resulting list has no dupicate elements but "
                              + "it is not of the same type");
                } // if
            } else {
                printFail("Resulting list contains duplicate elements");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testDistinct

    /**
     * Test {@link StringList#get(int)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testGet(String testName, StringList sl)  {
        printTestName(testName);
        String f = "get(%d)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, -2) + ": IOOBE");
            sl.get(-2);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 1) + " on an emptpy list: IOOBE");
            sl.get(1);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0) + ", " + String.format(f, 1)
                             + " where \"a\" and \"b\" are elements");
            sl.add("a");
            sl.add("b");
            if (sl.get(0).equals("a") && sl.get(1).equals("b")) {
                printPass("Successfully retrieve the correct elements");
            } else {
                printFail("Elements are not at their anticpated index");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testGet

    /**
     * Test {@link StringList#indexOf(String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testIndexOf(String testName, StringList sl) {
        printTestName(testName);
        String f = "indexOf(%s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "null") + ": NPE");
            sl.indexOf(null);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"\"") + ": IAE");
            sl.indexOf("");
            printFail(formatException("IAE"));
        } catch (IllegalArgumentException e) {
            printPass(formatException("IAE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"a\"") + " where \"a is an element\": 3");
            sl.add("c");
            sl.add("b");
            sl.add("A");
            sl.add("a");
            sl.add("a");
            if (sl.indexOf("a") == 3) {
                printPass("Successfully returned the correct index");
            } else {
                printFail("Returned the wrong index");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"n\"") + " where \"n\" is not an element: -1");
            if (sl.indexOf("n") == -1 && sl.add("a") && sl.indexOf("n") == -1) {
                printPass("Successfully returned -1");
            } else {
                printFail("Returned an index other than -1 or add(String) is faulty");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testIndexOf

    /**
     * Test {@link StringList#indexOfIgnoreCase(String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testIndexOfIgnoreCase(String testName, StringList sl) {
        printTestName(testName);
        String f = "indexOfIgnoreCase(%s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "null") + ": NPE");
            sl.indexOfIgnoreCase(null);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"\"") + ": IAE");
            sl.indexOfIgnoreCase("");
            printFail(formatException("IAE"));
        } catch (IllegalArgumentException e) {
            printPass(formatException("IAE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"A\"") + " where \"A\" is an element: 2");
            sl.add("c");
            sl.add("b");
            sl.add("a");
            sl.add("A");
            sl.add("a");
            if (sl.indexOfIgnoreCase("A") == 2) {
                printPass("Successfully returned the correct index");
            } else {
                printFail("Returned the wrong index");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "\"n\"") + " where \"n\" is not an element: -1");
            if (sl.indexOfIgnoreCase("n") == -1
                && sl.add("a") && sl.indexOfIgnoreCase("n") == -1) {
                printPass("Successfully returned -1");
            } else {
                printFail("Returned an index other than -1 or add(String) is faulty");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testIndexOfIgnoreCase

    /**
     * Test {@link StringList#isEmpty()} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testIsEmpty(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            if (sl.isEmpty() && sl.add("a") && !sl.isEmpty()) {
                printPass("Accurately determines when list is empty");
            } else {
                printFail("Does not accurately determine when list is empty");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testIsEmpty

    /**
     * Test {@link StringList#makeString()} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testMakeString(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            sl.add("a");
            sl.add("b");
            sl.add("c");
            if (sl.makeString().equals("abc")) {
                printPass("Successfully made String");
            } else {
                printFail("Failed to make String");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testMakeString

    /**
     * Test {@link StringList#makeString(String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testMakeStringSep(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            sl.add("a");
            sl.add("b");
            sl.add("c");
            if (sl.makeString(" - ").equals("a - b - c")) {
                printPass("Successfully made String");
            } else {
                printFail("Failed to make String");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testMakeStringSep

    /**
     * Test {@link StringList#makeString(String, String, String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testMakeStringThree(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            sl.add("a");
            sl.add("b");
            sl.add("c");
            if (sl.makeString("<", ",", ">").equals("<a,b,c>")) {
                printPass("Successfully made String");
            } else {
                printFail("Failed to make String");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testMakeStringThree

    /**
     * Test {@link StringList#remove(int)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testRemove(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        String f = "remove(%d)";
        try {
            printSubTestName(String.format(f, -2) + ": IOOBE");
            sl.remove(-2);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 1) + " on an empty list: IOOBE");
            sl.remove(1);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 1) + " where \"b is an element\": \"b\"");
            sl.add("a");
            sl.add("b");
            sl.add("c");
            if (sl.remove(1).equals("b") && sl.makeString().equals("ac")) {
                printPass("Successfully removed the element and returned the removed value");
            } else {
                printFail("Did not remove element or failed to return removed value");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        }
    } // testRemove

    /**
     * Test {@link StringList#reverse()} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testReverse(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            sl.add("a");
            sl.add("b");
            sl.add("c");
            sl.add("d");
            StringList rev = sl.reverse();
            // Reversed
            if (rev.makeString().equals("dcba")) {
                // Same implementation
                if (rev.getClass().getName().equals(sl.getClass().getName())) {
                    // Unique memory
                    rev.add("e");
                    sl.add("s");
                    if (rev.makeString().equals("dcbae")
                        && sl.makeString().equals("abcds")) {
                        printPass("Resulting list is reversed, "
                                  + "matches the object type, "
                                  + "and is unique in memory");
                    } else {
                        printFail("Resulting list list is reversed and "
                                  + "matches the object type, but "
                                  + "is sharing the same data in memory");
                    } // if
                } else {
                    printFail("Resulting list is reversed but "
                              + "it is not of the same type");
                } // if
            } else {
                printFail("Resulting list is not reversed");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testReverse

    /**
     * Test {@link StringList#set(int, String)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testSet(String testName, StringList sl) {
        printTestName(testName);
        String f = "set(%d, %s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, -2, "\"a\"") + ": IOOBE");
            sl.set(-2, "a");
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 1, "\"a\"") + " on an empty list: IOOBE");
            sl.set(1, "a");
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, null) + ": NPE");
            String n = null;
            sl.add("a");
            sl.set(0, n);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, "\"\"") + ": IAE");
            sl.add("a");
            sl.set(0, "");
            printFail(formatException("IAE"));
        } catch (IllegalArgumentException e) {
            printPass(formatException("IAE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 1, "\"d\"") + ": \"adc\"");
            sl.add("a");
            sl.add("b");
            sl.add("c");
            if (sl.set(1, "d").equals("b") && sl.makeString().equals("adc")) {
                printPass("Successfully set and return replaced element");
            } else {
                printFail("Did not set or failed to return replaced element");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testSet

    /**
     * Test {@link StringList#size()} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testSize(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            boolean size1 = false;
            boolean size2 = false;
            boolean size3 = false;
            sl.add("a");
            sl.add("b");
            size1 = sl.size() == 2;
            sl.set(1, "box");
            size2 = sl.size() == 2;
            sl.remove(0);
            size3 = sl.size() == 1;
            if (size1 && size2 && size3) {
                printPass("Size is accurately adjusted and returned");
            } else {
                printFail("Size is not accurately adjusted or returned");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testSize

    /**
     * Test {@link StringList#splice(int, int)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testSplice(String testName, StringList sl) {
        printTestName(testName);
        String f = "splice(%d, %d)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, -1, 0) + ": IOOBE");
            sl.splice(-1, 0);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, -1) + ": IOOBE");
            sl.splice(0, -1);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 1, 0) + " on an empty list: IOOBE");
            sl.splice(1, 0);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, 1) + " on an empty list: IOOBE");
            sl.splice(0, 1);
            printFail(formatException("IOOBE"));
        } catch (IndexOutOfBoundsException e) {
            printPass(formatException("IOOBE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 1, 1) + ": \"\"");
            sl.add("a");
            sl.add("b");
            sl.add("c");
            if (sl.splice(1, 1).makeString().equals("")) {
                printPass("Resulting list is empty");
            } else {
                printFail("Resulting list is not empty, may not be unique or faulty method");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        testSplice2(f, sl);
    } // testSplice

    /**
     * Continues testing {@link StringList#splice(int, int)}.
     * @param f the format string used to print sub test titles
     * @param sl the {@link StringList} being tested
     */
    public static void testSplice2(String f, StringList sl) {
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, 0, 3) + ": and an extra \"c\" is added \"abcc\"");
            sl.add("a");
            sl.add("b");
            sl.add("c");
            sl.add("d");
            sl.add("e");
            sl.add("f");
            StringList spliced = sl.splice(0, 3);
            // Subset
            if (spliced.makeString().equals("abc")) {
                // Same implementation
                if (spliced.getClass().getName().equals(sl.getClass().getName())) {
                    // Unique memory
                    spliced.add("c");
                    sl.add("s");
                    if (spliced.makeString().equals("abcc")
                        && sl.makeString().equals("abcdefs")) {
                        printPass("Resulting list is a subset, "
                                  + "matches the object type, "
                                  + "and is unique in memory");
                    } else {
                        printFail("Resulting list is a subset and "
                                  + "matches the object type, but "
                                  + "is sharing the same data in memory");
                    } // if
                } else {
                    printFail("Resulting list is a subset but "
                              + "it is not of the same type");
                } // if
            } else {
                printFail("Resulting list is not a subset");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testSplice2

    /**
     * Test {@link StringList#toArray()} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testToArray(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            sl.add("a");
            sl.add("b");
            sl.add("c");
            String listAsStr = sl.makeString();
            String arrAsStr = String.join("", sl.toArray());
            if (listAsStr.equals(arrAsStr)) {
                printPass("Resulting array was properly created");
            } else {
                printFail("Resulting array's elements do not match those of the list");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testToArray

    /**
     * Test {@code Constructor(StringList)} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testConstructor(String testName, StringList sl) {
        printTestName(testName);
        String f = "Constructor(%s)";
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "null") + ": NPE");
            StringList otherSL = sl.getClass().getName().contains("Array")
                ? new ArrayStringList(null) : new LinkedStringList(null);
            printFail(formatException("NPE"));
        } catch (NullPointerException e) {
            printPass(formatException("NPE", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName(String.format(f, "sl") + ": \"abnew\" where"
                             + " \"a\" and \"b\" come from sl and"
                             + " \"new\" comes from add(String)");
            sl.add("a");
            sl.add("b");
            StringList otherSL = sl.getClass().getName().contains("Array")
                ? new ArrayStringList(sl) : new LinkedStringList(sl);
            // Same values
            if (otherSL.makeString().equals(sl.makeString())) {
                // Unique memory
                sl.add("org");
                otherSL.add("new");
                if (sl.makeString().equals("aborg") && otherSL.makeString().equals("abnew")) {
                    printPass("The new list had the same inital values"
                              + " and is unique in memory");
                } else {
                    printFail("The new list had the same inital values"
                              + ", but shares the same memory");
                } // if
            } else {
                printFail("The new list does not have the same values");
            } // if
        } catch (Exception e) {
            e.printStackTrace();
            printFail(formatException("Exception", e));
        } // try
    } // testConstructor

    /**
     * Test {@link StringList#iterator()} for
     * proper behavior and expected exceptions.
     * @param testName the name of the test
     * @param sl the {@link StringList} implementation to test
     */
    public static void testIterator(String testName, StringList sl) {
        printTestName(testName);
        sl = reset(sl);
        try {
            printSubTestName("while-loop");
            sl.add("a");
            sl.add("b");
            sl.add("c");
            Iterator<String> it = sl.iterator();
            String itOutput = "";
            while (it.hasNext()) {
                itOutput += it.next();
            } // while
            if (itOutput.equals(sl.makeString())) {
                printPass("The while-loop works as anticipated");
            } else {
                printFail("The while-loop does not work as anticipated");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
        sl = reset(sl);
        try {
            printSubTestName("for-each-loop");
            sl.add("a");
            sl.add("b");
            sl.add("c");
            String output = "";
            for (String s : sl) {
                output += s;
            } // for
            if (output.equals(sl.makeString())) {
                printPass("The for-each-loop works as anticipated");
            } else {
                printFail("The for-each-loop does not work as anticipated");
            } // if
        } catch (Exception e) {
            printFail(formatException("Exception", e));
        } // try
    } // testIterator

    /**
     * Prints the labeling key used.
     */
    private static void printKey() {
        System.out.println(NEW_OBJ + "=== Implementation Being Tested ===" + RESET);
        System.out.println(NEW_TEST + "  +++ Name of a general test "
                           + "or grouping of tests +++" + RESET);
        System.out.println(NEW_SUB_TEST + "    <Name of a specific test>" + RESET);
        System.out.println(PASS + "      Passed a test " + RESET + "or "
                           + FAIL + "Failed a test" + RESET + "\n");
    } // printKey

    /**
     * Prints {@code sl}'s object type with
     * a designated label and color.
     * @param sl the {@link StringList} to get the object type of
     */
    private static void printObjectType(StringList sl) {
        System.out.println(NEW_OBJ + "=== " + sl.getClass().getName() + " ===" + RESET);
    } // printObjectType

    /**
     * Prints {@code testName} with a
     * a designated label and color.
     * @param testName the name of the test
     */
    private static void printTestName(String testName) {
        System.out.println(NEW_TEST + "  +++ " + testName + " +++" + RESET);
    } // printTestName

    /**
     * Prints {@code testName} with a
     * a designated label and color.
     * @param testName the name of the test
     */
    private static void printSubTestName(String testName) {
        System.out.println(NEW_SUB_TEST + "    <" + testName + ">" + RESET);
    } // printSubTestName

    /**
     * Prints {@code msg} with a designated color.
     * @param msg the message of the test
     */
    private static void printPass(String msg) {
        System.out.println(PASS + "      PASS: " + msg + RESET);
    } // printPass

    /**
     * Prints {@code msg} with a designated color.
     * @param msg the message of the test
     */
    private static void printFail(String msg) {
        System.out.println(FAIL + "      FAIL: " + msg + RESET);
    } // printFail

    /**
     * Returns a new {@link StringList} object
     * depending on the object reference to by {@code sl}.
     * @param sl the {@link StringList} implementation to use
     * @return the new reference to a {@link StringList} implementation
     */
    private static StringList reset(StringList sl) {
        if (sl.getClass().getName().contains("Array")) {
            return new ArrayStringList();
        } else {
            return new LinkedStringList();
        } // if
    } // reset

    /**
     * Takes in an exception abbreviation and
     * returns a {@link String} in a particular format.
     *
     * @param abbr the exception abbreviation
     * @return a {@link String} in a particular format
     */
    private static String formatException(String abbr) {
        return String.format("did not throw a(n) %s", abbr);
    } // formatException

    /**
     * Takes in an exception abbreviation and {@link Exception}
     * and returns a {@link String} in a particular format.
     *
     * @param abbr the exception abbreviation
     * @param e the {@code Exception} object
     * @return a {@link String} in a particular format
     */
    private static String formatException(String abbr, Exception e) {
        return String.format("threw a(n) %s ||| Message: %s", abbr, e.getMessage());
    } // formatException

} // Driver
