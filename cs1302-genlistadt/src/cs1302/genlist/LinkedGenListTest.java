package cs1302.genlist;

import cs1302.genlistadt.GenList;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.Comparator;

/**
 * This class demos some methods in the {@code LinkedGenList} class. It tests each method with
 * lambda expressions and two different parameterizations of {@code GenList<T>}.
 */
public class LinkedGenListTest {

    public static void main(String [] args) {
        demoMap();
        demoReduce();
        demoFilter();
        demoMin();
        demoAllMatch();
    } // main

    /**
     * Test the map method in the {@code LinkedGenList} class.
     */
    public static void demoMap() {
        System.out.println("");
        System.out.println("DEMO MAP:");

        System.out.println("- Map Integers to Hexadecimals:");
        GenList<Integer> gli = new LinkedGenList<>();
        gli.add(7);
        gli.add(11);
        gli.add(14);
        gli.add(18);
        gli.add(38);
        Function<Integer, String> hexaMapper = t -> Integer.toString(t, 16);
        GenList<String> mappedHex = gli.map(hexaMapper);
        System.out.print("   - Unmapped Integers: ");
        System.out.println(gli.makeString(", "));
        System.out.print("   - Mapped Integers (mapped to Hexadecimal): ");
        System.out.println(mappedHex.makeString(", "));

        System.out.println("- Map Characters to Integers (ASCII Value):");
        GenList<Character> glc = new LinkedGenList<>();
        glc.add('a');
        glc.add('b');
        glc.add('c');
        glc.add('d');
        glc.add('e');
        Function<Character, Integer> intMapper = c -> (int)c;
        GenList<Integer> mappedInt = glc.map(intMapper);
        System.out.print("   - Unmapped Characters: ");
        System.out.println(glc.makeString(", "));
        System.out.print("   - Mapped Characters (mapped to Integers): ");
        System.out.println(mappedInt.makeString(", "));
        System.out.println("");
    } // demoMap

    /**
     * Test the reduce method in the {@code LinkedGenList} class.
     */
    public static void demoReduce() {
        System.out.println("DEMO REDUCE:");

        System.out.println("- Concat Strings:");
        GenList<String> gls = new LinkedGenList<>();
        gls.add("Alpha");
        gls.add("Beta");
        gls.add("Charlie");
        BinaryOperator<String> concatString = (a, b) -> a + b;
        String str1 = gls.reduce("Test", concatString);
        String str2 = gls.reduce("Hello", concatString);
        System.out.print("   - Original String GenList: ");
        System.out.println(gls.makeString(", "));
        System.out.print("   - Output with 'Test' in front: ");
        System.out.println(str1);
        System.out.print("   - Output with 'Hello' in front: ");
        System.out.println(str2);

        System.out.println("- Multiply Integers:");
        GenList<Integer> gli = new LinkedGenList<>();
        gli.add(1);
        gli.add(2);
        gli.add(3);
        BinaryOperator<Integer> multiplyInt = (a, b) -> a * b;
        int int1 = gli.reduce(0, multiplyInt);
        int int2 = gli.reduce(1, multiplyInt);
        int int3 = gli.reduce(10, multiplyInt);
        System.out.print("   - Original Integer GenList: ");
        System.out.println(gli.makeString(", "));
        System.out.print("   - Output with '0': ");
        System.out.println(int1);
        System.out.print("   - Output with '1': ");
        System.out.println(int2);
        System.out.print("   - Output with '10': ");
        System.out.println(int3);
        System.out.println("");
    } // demoReduce

    /**
     * Test the filter method in the {@code LinkedGenList} class.
     */
    public static void demoFilter() {
        System.out.println("DEMO FILTER:");

        System.out.println("- Strings Start with 'A' and have a Length of '5':");
        GenList<String> gls = new LinkedGenList<>();
        gls.add("Alpha");
        gls.add("Apple");
        gls.add("Abcd");
        gls.add("Beta");
        gls.add("Delta");
        gls.add("abcde");
        gls.add("Abcde");
        Predicate<String> A5String = a -> a.charAt(0) == 'A' && a.length() == 5;
        GenList<String> OnlyA5Strings = gls.filter(A5String);
        System.out.print("   - Original String GenList: ");
        System.out.println(gls.makeString(", "));
        System.out.print("   - Filtered String GenList: ");
        System.out.println(OnlyA5Strings.makeString(", "));

        System.out.println("- Integers End with '7' and have a Length of '2':");
        GenList<Integer> gli = new LinkedGenList<>();
        gli.add(7);
        gli.add(17);
        gli.add(77);
        gli.add(13);
        gli.add(937);
        Predicate<Integer> predInt = n -> n % 10  == 7 && String.valueOf(n).length() == 2;
        GenList<Integer> filteredInt = gli.filter(predInt);
        System.out.print("   - Original Integer GenList: ");
        System.out.println(gli.makeString(", "));
        System.out.print("   - Filtered Integer GenList: ");
        System.out.println(filteredInt.makeString(", "));
        System.out.println("");
    } // demoFilter

    /**
     * Test the min method in the {@code LinkedGenList} class.
     */
    public static void demoMin() {
        System.out.println("DEMO MIN:");

        System.out.println("- Minimum String Based on the 3rd Letter:");
        GenList<String> gls = new LinkedGenList<>();
        gls.add("Alpha");
        gls.add("Beta");
        gls.add("Charlie");
        gls.add("Delta");
        Comparator<String> compString = (s1, s2) ->
            (s1.substring(2, 3)).compareTo(s2.substring(2, 3));
        String str1 = gls.min(compString);
        System.out.print("   - Original String GenList: ");
        System.out.println(gls.makeString(", "));
        System.out.print("   - Minimum String (3rd Letter): ");
        System.out.println(str1);

        System.out.println("- Minimum Integer Based on the Last 2 Digits:");
        GenList<Integer> gli = new LinkedGenList<>();
        gli.add(1912);
        gli.add(1492);
        gli.add(2001);
        gli.add(2020);
        gli.add(2015);
        gli.add(1904);
        Comparator<Integer> compInt = (n1, n2) -> ((n1 % 100) - (n2 % 100));
        int int1 = gli.min(compInt);
        System.out.print("   - Original Integer GenList: ");
        System.out.println(gli.makeString(", "));
        System.out.print("   - Minimum Integer (Last 2 Digits): ");
        System.out.println(int1);
        System.out.println("");
    } // demoMin

     /**
     * Test the allMatch method in the {@code LinkedGenList} class.
     */
    public static void demoAllMatch() {
        System.out.println("DEMO ALL MATCH:");

        System.out.println("- Strings End with 's' and have an Even Length:");
        GenList<String> gls = new LinkedGenList<>();
        gls.add("Apples");
        gls.add("Boys");
        gls.add("Wordsmiths");
        Predicate<String> predString = s1 ->
            ((s1.charAt(s1.length() - 1) == 's') && (s1.length() % 2 == 0));
        Boolean result1 = gls.allMatch(predString);
        System.out.print("   - Original String GenList: ");
        System.out.println(gls.makeString(", "));
        System.out.print("   - All Match Result: ");
        System.out.println(result1);

        System.out.println("- Integers 2nd Digit is 1 and have an Odd Length:");
        GenList<Integer> gli = new LinkedGenList<>();
        gli.add(111);
        gli.add(219);
        gli.add(2103);
        Predicate<Integer> predInt = n1 ->
            ((String.valueOf(n1).charAt(1) == '1') &&
             ((String.valueOf(n1).length() % 2 == 0) == false));
        Boolean result2 = gli.allMatch(predInt);
        System.out.print("   - Original Integer GenList: ");
        System.out.println(gli.makeString(", "));
        System.out.print("   - All Match Result: ");
        System.out.println(result2);
        System.out.println("");
    } // demoAllMatch
} // LinkedGenListTest
