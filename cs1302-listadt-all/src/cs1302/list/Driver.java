package cs1302.list;

import cs1302.listadt.StringList;

public class Driver {

    public static void main(String [] args) {

        System.out.println("\nLINKED STRING LIST TESTS");
        StringList lsl = new LinkedStringList();

        System.out.println("\n - ADD:");
        testAddNormal(lsl);
        testAddByIndex(lsl);
        testAddByIndexOOB(lsl);
        testAddNegative(lsl);
        testAddStringNull(lsl);
        testAddStringEmpty(lsl);
        testAddStringList(lsl);
        testAddStringListByIndex(lsl);

        System.out.println("\n - CLEAR:");
        testClear(lsl);

        System.out.println("\n - CONTAINS:");
        testContains(lsl);
        testContainsStringNull(lsl);
        testContainsStringEmpty(lsl);
        testContainsIgnoreCase(lsl);
        testContainsIgnoreCaseStringNull(lsl);
        testContainsIgnoreCaseStringEmpty(lsl);
        testContainsSubstring(lsl);
        testContainsSubstringStringNull(lsl);
        testContainsSubstringStringEmpty(lsl);

        System.out.println("\n - DISTINCT:");
        testDistinct(lsl);

        System.out.println("\n - GET:");
        testGet(lsl);
        testGetIndexOOB(lsl);

        System.out.println("\n - INDEX OF:");
        testIndexOf(lsl);
        testIndexOfStringNull(lsl);
        testIndexOfStringEmpty(lsl);
        testIndexOfIgnoreCase(lsl);
        testIndexOfIgnoreCaseStringNull(lsl);
        testIndexOfIgnoreCaseStringEmpty(lsl);

        System.out.println("\n - IS EMPTY:");
        testIsEmpty(lsl);

        System.out.println("\n - MAKE STRING:");
        testMakeString(lsl);
        testMakeStringSep(lsl);
        testMakeStringSep2(lsl);

        System.out.println("\n - REMOVE:");
        testRemove(lsl);

        System.out.println("\n - REVERSE:");
        testReverse(lsl);

        System.out.println("\n - SET:");
        testSet(lsl);
        testSetStringNull(lsl);
        testSetStringEmpty(lsl);
        testSetIndexOOB(lsl);

        System.out.println("\n - SIZE:");
        testSize(lsl);

        System.out.println("\n - SPLICE:");
        testSplice(lsl);

        System.out.println("\nARRAY STRING LIST TESTS");
        StringList asl = new ArrayStringList();

        System.out.println("\n - ADD:");
        testAddNormal(asl);
        testAddByIndex(asl);
        testAddByIndexOOB(asl);
        testAddNegative(asl);
        testAddStringNull(asl);
        testAddStringEmpty(asl);
        testAddStringList(asl);
        testAddStringListByIndex(asl);

        System.out.println("\n - CLEAR:");
        testClear(asl);

        System.out.println("\n - CONTAINS:");
        testContains(asl);
        testContainsStringNull(asl);
        testContainsStringEmpty(asl);
        testContainsIgnoreCase(asl);
        testContainsIgnoreCaseStringNull(asl);
        testContainsIgnoreCaseStringEmpty(asl);
        testContainsSubstring(asl);
        testContainsSubstringStringNull(asl);
        testContainsSubstringStringEmpty(asl);

        System.out.println("\n - DISTINCT:");
        testDistinct(asl);

        System.out.println("\n - GET:");
        testGet(asl);
        testGetIndexOOB(asl);

        System.out.println("\n - INDEX OF:");
        testIndexOf(asl);
        testIndexOfStringNull(asl);
        testIndexOfStringEmpty(asl);
        testIndexOfIgnoreCase(asl);
        testIndexOfIgnoreCaseStringNull(asl);
        testIndexOfIgnoreCaseStringEmpty(asl);

        System.out.println("\n - IS EMPTY:");
        testIsEmpty(asl);

        System.out.println("\n - MAKE STRING:");
        testMakeString(asl);
        testMakeStringSep(asl);
        testMakeStringSep2(asl);

        System.out.println("\n - REMOVE:");
        testRemove(asl);

        System.out.println("\n - REVERSE:");
        testReverse(asl);

        System.out.println("\n - SET:");
        testSet(asl);
        testSetStringNull(asl);
        testSetStringEmpty(asl);
        testSetIndexOOB(asl);

        System.out.println("\n - SIZE:");
        testSize(asl);

        System.out.println("\n - SPLICE:");
        testSplice(asl);
        System.out.println("");
    } // main

    public static void testSplice(StringList list) {
        System.out.print("testSplice: ");
        try {
            list.clear();
            list.add("One");
            list.add("Two");
            list.add("Three");
            list.add("Four");
            list.add("Five");
            StringList tempList = list.splice(2,4);
            String [] tempArr = tempList.toArray();
            if (tempArr[0].equals("Three") && tempArr[1].equals("Four")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected IOOB, but got " + e);
        } // try
    } // testSplice

    public static void testSize(StringList list) {
        System.out.print("testSize: ");
        try {
            list.clear();
            list.add("A");
            list.add("B");
            list.add("C");
            list.add("D");
            int tempInt1 = list.size();
            list.clear();
            int tempInt2 = list.size();
            if (tempInt1 == 4 && tempInt2 == 0) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            }
        } catch (Throwable e) {
            System.out.println("FAIL: expected IOOB, but got " + e);
        } // try
    } // testSize


    public static void testSetIndexOOB(StringList list) {
        System.out.print("testSetIndexOOB: ");
        try {
            list.clear();
            list.set(50, "Apple");
            System.out.println("FAIL: expected IOOB; however, no exception was encountered");
        } catch (IndexOutOfBoundsException ioob) {
            System.out.println("PASS: expected IOOB; IOOB was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IOOB, but got " + e);
        } // try
    } // testSetIndexOOB

    public static void testSetStringEmpty(StringList list) {
        System.out.print("testSetStringEmpty: ");
        try {
            list.clear();
            list.add("A");
            list.set(0, "");
            System.out.println("FAIL: expected IAE; however, no exception was encountered");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS: expected IAE; IAE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IAE, but got " + e);
        } // try
    } // testSetStringEmpty

    public static void testSetStringNull(StringList list) {
        System.out.print("testSetStringNull: ");
        try {
            String testStr = null;
            list.clear();
            list.add("A");
            list.set(0, testStr);
            System.out.println("FAIL: expected NPE; however, no exception was encountered");
        } catch (NullPointerException npe) {
            System.out.println("PASS: expected NPE; NPE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected NPE, but got " + e);
        } // try
    } // testSetStringNull

    public static void testSet(StringList list) {
        System.out.print("testSet: ");
        try {
            list.clear();
            list.add("A");
            list.add("B");
            list.add("C");
            String tempStr1 = list.set(0, "Apple");
            String tempStr2 = list.set(2, "Charlie");
            String [] tempArr = list.toArray();
            if (tempArr[0].equals("Apple") && tempArr[1].equals("B") && tempArr[2].equals("Charlie") && tempStr1.equals("A") && tempStr2.equals("C")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // catch
    } // testSet

    public static void testReverse(StringList list) {
        System.out.print("testReverse: ");
        try {
            list.clear();
            list.add("A");
            list.add("B");
            list.add("C");
            StringList tempList = list.reverse();
            String [] tempArr = tempList.toArray();
            if (tempArr[0].equals("C") && tempArr[1].equals("B") && tempArr[2].equals("A")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // catch
    } // testReverse

    public static void testRemove(StringList list) {
        System.out.print("testRemove: ");
        try {
            list.clear();
            list.add("A");
            list.add("B");
            list.add("C");
            list.add("D");
            list.add("E");
            list.remove(2);
            list.remove(0);
            String [] tempArr = list.toArray();
            if (tempArr[0].equals("B") && tempArr[1].equals("D") && tempArr[2].equals("E")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // catch
    } // testRemove

    public static void testMakeStringSep2(StringList list) {
        System.out.print("testMakeStringSep2: ");
        try {
            list.clear();
            list.add("A");
            list.add("B");
            list.add("C");
            String tempStr = list.makeString("~", "!", "#");
            if (tempStr.equals("~A!B!C#")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // catch
    } // testMakeStringSep2

    public static void testMakeStringSep(StringList list) {
        System.out.print("testMakeStringSep: ");
        try {
            list.clear();
            list.add("A");
            list.add("B");
            list.add("C");
            String tempStr = list.makeString(", ");
            if (tempStr.equals("A, B, C")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // catch
    } // testMakeStringSep

    public static void testMakeString(StringList list) {
        System.out.print("testMakeString: ");
        try {
            list.clear();
            list.add("Apple");
            list.add("Car");
            String tempStr = list.makeString();
            if (tempStr.equals("AppleCar")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // catch
    } // testMakeString

    public static void testIsEmpty(StringList list) {
        System.out.print("testIsEmpty: ");
        Boolean checkOne = false;
        Boolean checkTwo = false;
        try {
            list.clear();
            list.add("Apple");
            list.add("Car");
            if (list.isEmpty() == false) {
                checkOne = true;
            } // if
            list.clear();
            if (list.isEmpty() == true) {
                checkTwo = true;
            } // if
            if (checkOne == true && checkTwo == true) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // catch
    } // testIsEmpty

    public static void testIndexOfIgnoreCase(StringList list) {
        System.out.print("testIndexOfIgnoreCase: ");
        try {
            list.clear();
            list.add("Apple");
            list.add("Cat");
            if ((list.indexOfIgnoreCase("Apple") == 0) && (list.indexOfIgnoreCase("Cat") == 1) && (list.indexOfIgnoreCase("App") == -1) && (list.indexOfIgnoreCase("apple") == 0)) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testIndexOfIgnoreCase

    public static void testIndexOfIgnoreCaseStringNull(StringList list) {
        String testStr = null;
        System.out.print("testIndexOfIgnoreCaseStringNull: ");
        try {
            list.clear();
            list.indexOfIgnoreCase(testStr);
            System.out.println("FAIL: expected NPE; however, no exception was encountered");
        } catch (NullPointerException npe) {
            System.out.println("PASS: expected NPE; NPE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected NPE, but got " + e);
        } // try
    } // testIndexOfIgnoreCaseStringNull

    public static void testIndexOfIgnoreCaseStringEmpty(StringList list) {
        System.out.print("testIndexOfIgnoreCaseStringEmpty: ");
        try {
            list.clear();
            list.indexOfIgnoreCase("");
            System.out.println("FAIL: expected IAE; however, no exception was encountered");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS: expected IAE; IAE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IAE, but got " + e);
        } // try
    } // testIndexOfIgnoreCaseStringEmpty

    public static void testIndexOf(StringList list) {
        System.out.print("testIndexOf: ");
        try {
            list.clear();
            list.add("Apple");
            list.add("Cat");
            if ((list.indexOf("Apple") == 0) && (list.indexOf("Cat") == 1) && (list.indexOf("App") == -1) && (list.indexOf("apple") == -1)) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testIndexOf

    public static void testIndexOfStringNull(StringList list) {
        String testStr = null;
        System.out.print("testIndexOfStringNull: ");
        try {
            list.clear();
            list.indexOf(testStr);
            System.out.println("FAIL: expected NPE; however, no exception was encountered");
        } catch (NullPointerException npe) {
            System.out.println("PASS: expected NPE; NPE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected NPE, but got " + e);
        } // try
    } // testIndexOfStringNull

    public static void testIndexOfStringEmpty(StringList list) {
        System.out.print("testIndexOfStringEmpty: ");
        try {
            list.clear();
            list.indexOf("");
            System.out.println("FAIL: expected IAE; however, no exception was encountered");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS: expected IAE; IAE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IAE, but got " + e);
        } // try
    } // testIndexOfStringEmpty

    public static void testGetIndexOOB(StringList list) {
        System.out.print("testGetIndexOOB: ");
        try {
            list.clear();
            list.get(50);
            System.out.println("FAIL: expected IOOB; however, no exception was encountered");
        } catch (IndexOutOfBoundsException ioob) {
            System.out.println("PASS: expected IOOB; IOOB was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IOOB, but got " + e);
        } // try
    } // testGetIndexOOB

    public static void testGet(StringList list) {
        System.out.print("testGet: ");
        try {
            list.clear();
            list.add("Dog");
            list.add("Cat");
            list.add("Panda");
            if (list.get(0).equals("Dog") && list.get(1).equals("Cat") && list.get(2).equals("Panda")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testGet

    public static void testDistinct(StringList list) {
        System.out.print("testDistinct: ");
        try {
            list.clear();
            list.add("Dog");
            list.add("Cat");
            list.add("Panda");
            list.add("Panda");
            list.add("panda");
            StringList distList = list.distinct();
            list.clear();
            String distArr [] = distList.toArray();
            if (distArr[0].equals("Dog") && distArr[1].equals("Cat") && distArr[2].equals("Panda") && distArr[3].equals("panda")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testDistinct

    public static void testAddNormal(StringList list) {
        System.out.print("testAddNormal: ");
        try {
            list.clear();
            list.add("Person 1");
            list.add("Person 2");
            list.add("Person 3");
            list.add("Person 4");
            list.add("Person 5");
            list.add("Person 6");
            list.add("Person 7");
            System.out.println("PASS");
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testAddNormal

    public static void testAddStringList(StringList list) {
        System.out.print("testAddStringList: ");
        try {
            StringList testASL = new ArrayStringList();
            testASL.add("One");
            testASL.add("Two");
            list.clear();
            list.add(testASL);
            String [] testList = list.toArray();
            if (testList[0].equals("One") && testList[1].equals("Two")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testAddStringList

    public static void testAddStringListByIndex(StringList list) {
        System.out.print("testAddStringListByIndex: ");
        try {
            StringList testASL1 = new ArrayStringList();
            testASL1.add("Two");
            testASL1.add("Three");
            list.clear();
            list.add("One");
            list.add(1, testASL1);
            String [] testList = list.toArray();
            if (testList[0].equals("One") && testList[1].equals("Two") && testList[2].equals("Three")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testAddStringListByIndex

    public static void testAddByIndex(StringList list) {
        System.out.print("testAddByIndex: ");
        try {
            list.clear();
            list.add(0, "Person 2");
            list.add(0, "Person 0");
            list.add(1, "Person 1");
            String [] testList = list.toArray();
            if (testList[0].equals("Person 0") && testList[1].equals("Person 1") && testList[2].equals("Person 2")) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testAddByIndex

    public static void testAddByIndexOOB(StringList list) {
        System.out.print("testAddByIndexOOB: ");
        try {
            list.clear();
            list.add(50, "Nope");
            System.out.println("FAIL: expected IOOB; however, no exception was encountered");
        } catch (IndexOutOfBoundsException ioob) {
            System.out.println("PASS: expected IOOB; IOOB was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IOOB, but got " + e);
        } // try
    } // testAddByIndexOOB

    public static void testAddNegative(StringList list) {
        System.out.print("testAddNegative: ");
        try {
            list.clear();
            list.add(-5, "hello");
            System.out.println("FAIL: expected IOOB; however, no exception was encountered");
        } catch (IndexOutOfBoundsException ioob) {
            System.out.println("PASS: expected IOOB; IOOB was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IOOB, but got " + e);
        } // try
    } // testAddNegative

    public static void testAddStringNull(StringList list) {
        String testStr = null;
        System.out.print("testAddStringNull: ");
        try {
            list.clear();
            list.add(testStr);
            System.out.println("FAIL: expected NPE; however, no exception was encountered");
        } catch (NullPointerException npe) {
            System.out.println("PASS: expected NPE; NPE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected NPE, but got " + e);
        } // try
    } // testAddStringNull

    public static void testAddStringEmpty(StringList list) {
        System.out.print("testAddStringEmpty: ");
        try {
            list.clear();
            list.add("");
            System.out.println("FAIL: expected IAE; however, no exception was encountered");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS: expected IAE; IAE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IAE, but got " + e);
        } // try
    } // testAddStringEmpty

    public static void testClear(StringList list) {
        System.out.print("testClear: ");
        try {
            list.clear();
            String tempArr [] = list.toArray();
            boolean checkEmpty = true;
            for (int i = 0; i < tempArr.length; i++) {
                if (tempArr[i].equals("") == false) {
                    checkEmpty = false;
                } // if
            } // for
            if (list.size() != 0) {
                System.out.println("FAIL: list size is not 0");
            } else if (checkEmpty = false) {
                System.out.println("FAIL: list had non-empty elements");
            } else {
                System.out.println("PASS");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testClear

    public static void testContains(StringList list) {
        System.out.print("testContains: ");
        try {
            list.clear();
            list.add("Braves");
            if (list.contains("Braves") == true && list.contains("B") == false && list.contains("Bra") == false && list.contains("braves") == false) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testContains

    public static void testContainsStringNull(StringList list) {
        String testStr = null;
        System.out.print("testContainsStringNull: ");
        try {
            list.clear();
            list.contains(testStr);
            System.out.println("FAIL: expected NPE; however, no exception was encountered");
        } catch (NullPointerException npe) {
            System.out.println("PASS");
        } catch (Throwable e) {
            System.out.println("FAIL: expected NPE, but got " + e);
        } // try
    } // testContainsStringNull

    public static void testContainsStringEmpty(StringList list) {
        System.out.print("testContainsStringEmpty: ");
        try {
            list.clear();
            list.contains("");
            System.out.println("FAIL: expected IAE; however, no exception was encountered");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IAE, but got " + e);
        } // try
    } // testContainsStringEmpty

    public static void testContainsIgnoreCase(StringList list) {
        System.out.print("testContainsIgnoreCase: ");
        try {
            list.clear();
            list.add("Braves");
            if (list.containsIgnoreCase("Braves") == true && list.containsIgnoreCase("B") == false && list.containsIgnoreCase("Bra") == false && list.containsIgnoreCase("braves") == true) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testContainsIgnoreCase

    public static void testContainsIgnoreCaseStringNull(StringList list) {
        String testStr = null;
        System.out.print("testContainsIgnoreCaseStringNull: ");
        try {
            list.clear();
            list.containsIgnoreCase(testStr);
            System.out.println("FAIL: expected NPE; however, no exception was encountered");
        } catch (NullPointerException npe) {
            System.out.println("PASS");
        } catch (Throwable e) {
            System.out.println("FAIL: expected NPE, but got " + e);
        } // try
    } // testContainsIgnoreCaseStringNull

    public static void testContainsIgnoreCaseStringEmpty(StringList list) {
        System.out.print("testContainsIgnoreCaseStringEmpty: ");
        try {
            list.clear();
            list.containsIgnoreCase("");
            System.out.println("FAIL: expected IAE; however, no exception was encountered");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IAE, but got " + e);
        } // try
    } // testContainsIgnoreCaseStringEmpty

    public static void testContainsSubstring(StringList list) {
        System.out.print("testContainsSubstring: ");
        try {
            list.clear();
            list.add("Braves");
            if (list.containsSubstring("Braves") == true && list.containsSubstring("B") == true && list.containsSubstring("Bra") == true && list.containsSubstring("braves") == false) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            } // if
        } catch (Throwable e) {
            System.out.println("FAIL: expected it to work, but got " + e);
        } // try
    } // testContainsSubstring

    public static void testContainsSubstringStringNull(StringList list) {
        String testStr = null;
        System.out.print("testContainsSubstringStringNull: ");
        try {
            list.clear();
            list.containsSubstring(testStr);
            System.out.println("FAIL: expected NPE; however, no exception was encountered");
        } catch (NullPointerException npe) {
            System.out.println("PASS: expected NPE; NPE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected NPE, but got " + e);
        } // try
    } // testContainsSubstringStringNull

    public static void testContainsSubstringStringEmpty(StringList list) {
        System.out.print("testContainsSubstringStringEmpty: ");
        try {
            list.clear();
            list.containsSubstring("");
            System.out.println("FAIL: expected IAE; however, no exception was encountered");
        } catch (IllegalArgumentException iae) {
            System.out.println("PASS: expected IAE; IAE was encountered");
        } catch (Throwable e) {
            System.out.println("FAIL: expected IAE, but got " + e);
        } // try
    } // testContainsSubstringStringEmpty

    public static void printArray(StringList list) {
        String tempArr [] = list.toArray();
        System.out.print("Print: ");
        String wholeStr = "";
        for (int i = 0; i < tempArr.length; i++) {
            wholeStr += tempArr[i] + ", ";
        } // for
        if (wholeStr.equals("") == false) {
            wholeStr = wholeStr.substring(0, wholeStr.length() - 2);
        } // if
        System.out.println(wholeStr);
    } // printArray
} // Driver
