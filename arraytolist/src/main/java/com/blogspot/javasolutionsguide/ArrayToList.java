package com.blogspot.javasolutionsguide;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayToList {

    public static void main(String[] args) {

        //Before Java 8
        System.out.println("Before JAVA 8........................................");

        // 1.
        String[] names = new String[] {"Gaurav", "Sachin", "Yuvraj"};
        List<String> nameList =  Arrays.asList(names);
        System.out.println("using Arrays.asList: " + nameList);

        //2
        List<Integer> list = Arrays.asList(1, 2, 3);
        System.out.println("Inline primitive array to Integer List using Arrays.asList(): "
                + list);

        //Java 8
        System.out.println("JAVA 8 ................................................");
        // 3. primitive array
        int intArray[] = {1, 2, 3};
        List<Integer> integerList1 = Arrays.stream(intArray).boxed()
                                                         .collect(Collectors.toList());
        System.out.println("Primitive int array to Integer List using Array.stream(): "
                + integerList1);

        // 4. primitive array with IntStream
        List<Integer> integerList2 = IntStream.of(intArray).boxed()
                                                          .collect(Collectors.toList());
        System.out.println("Primitive int array to Integer List using IntStream.of(): "
                + integerList2);

        // 5. Object type array to Integer List
        Integer integerArray[] = {1, 2, 3};
        List<Integer> integerList3 = Arrays.stream(integerArray)
                                                           .collect(Collectors.toList());
        System.out.println("Object type array to Integer List using Arrays.stream:  "
                + integerList3);

        // 6. Integer Array to ArrayList
        ArrayList<Integer> arrayList = Arrays.stream(integerArray)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Integer type array to Integer ArrayList using Arrays.stream:  "
                + arrayList);

        // 7. Integer Array to LinkedList
        LinkedList<Integer> linkedList = Stream.of(integerArray)
                                           .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("Integer type array to Integer LinkedList using Arrays.stream:  "
                + linkedList);

        // 8. Integer Array to Immutable List
        List<Integer> immutableList = Collections.unmodifiableList(Arrays.asList(integerArray));
        System.out.println("Integer type array to Immutable List using Arrays.stream:  "
                + immutableList);

        //9. JAVA 9
        System.out.println("JAVA 9.........................................................");
        String[] playerNames = {"Sachin", "Dhoni", "Yuvraj"};
        List<String> players = List.of(playerNames);
        System.out.println("Array to List using Java 9 List.of() :" + players);

        //10. JAVA 10
        System.out.println("JAVA 10.........................................................");
        List<Integer> integerList = List.copyOf(Arrays.asList(integerArray));
        System.out.println("Array to List using Java 10 List.copyOf() :" + integerList);

        //11. Apache Commons
        System.out.println("Apache Commons...................................................");
        List<Integer> targetList = new ArrayList<>(6);
        CollectionUtils.addAll(targetList, integerArray);
        System.out.println("Array to List using Apache Common CollectionUtils.addAll() :"
                + integerList);

        //12 Google Guava
        System.out.println("Google Guava......................................................");
        Integer[] sourceArray = {1, 2, 3};
        List<Integer> targetList1 = Lists.newArrayList(sourceArray);
        System.out.println("Array to List using Google Guava Lists.newArrayList:"
                + integerList);
    }
}
