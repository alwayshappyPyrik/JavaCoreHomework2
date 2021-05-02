package HomeWork21;

import java.util.*;
import java.util.stream.Stream;

public class StreamMain {

    static List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
    static Integer[] arrayPositiveNumber = new Integer[10];
    static Integer[] evenNumber = new Integer[5];
    static int element;
    static int count;

    static void withoutCollectionApiAndStreamApi (List<Integer> intList) {
        Integer[] intArray = intList.toArray(new Integer[0]);
        for (int i : intArray) {
            if (i > 0) {
                arrayPositiveNumber[element] = i;
                element++;
                count++;
            }
        }
        element = 0;
        count = 0;
        for (int i : arrayPositiveNumber) {
            if (i % 2 == 0) {
                evenNumber[element] = i;
                element++;
                count++;
            }
        }
        Arrays.sort(evenNumber);
        for (int i = 0; i < count; i++) {
            System.out.print(evenNumber[i] + " ");
        }
        System.out.println();
    }

    static void withStreamApi(List<Integer> intList) {
        Stream<Integer> resaltList = intList.stream()
                .filter(x -> x > 0 && x % 2 == 0)
                .sorted(Comparator.naturalOrder());
        resaltList.forEach(i -> System.out.print(i + " "));
    }

}