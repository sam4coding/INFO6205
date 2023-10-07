package edu.neu.coe.info6205;
import java.util.Random;
import edu.neu.coe.info6205.util.Timer;

import java.util.function.Function;
import java.util.function.Supplier;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;


public class Assignment3Test {
    public static void main(String[] args) {

        // Assignment 3 testing for insertionSort + Benchmark Timer;
        int nArrays = 10; //number of arrays for testing, each of length 2 to the power from 1 to n.
        int n = 100; // repeat m times for each length

        for (int pow=0; pow<nArrays; pow++) { // the size of testing array are powers of the 2
            Timer timer = new Timer(); // this starts the timer by calling the resume() method in Timer
            Random randomInt = new Random(); // this is to prepare for a random integer supplier
            int arraySize = 100*(int) Math.pow(2,pow); // the arraySize doubles each time
            /*******************Part 1. testing on random arrays******************************/
            Supplier<Integer[]> randIntGen = () -> { // the supplier of a ()->Integer[] lambda
                Integer[] intArray = new Integer[arraySize];
                for (int i = 0; i<arraySize; i++) {
                    intArray[i] = randomInt.nextInt();
                }
                return intArray;
            };
            Function<Integer[],Integer[]> insertionSorter = (intArray) -> {
                InsertionSort<Integer> insertionSort = new InsertionSort<>();
                insertionSort.sort(intArray, 0, intArray.length);
                return intArray;
            };

            double meanTime = timer.repeat(n,randIntGen,insertionSorter);
            System.out.println("Mean Time of " + n + " sorting of random array of size " +arraySize + " is " + meanTime + " ms");
            /*******************Part 2. testing on ordered arrays******************************/
            Supplier<Integer[]> orderedIntGen = () -> { // the supplier of a ()->Integer[] lambda
                Integer[] intArray = new Integer[arraySize];
                for (int i = 0; i<arraySize; i++) {
                    intArray[i] = randomInt.nextInt();
                }
                InsertionSort<Integer> insertionSort = new InsertionSort<>();
                insertionSort.sort(intArray, 0, intArray.length);
                return intArray;
            };
            meanTime = timer.repeat(n,orderedIntGen,insertionSorter);
            System.out.println("Mean Time of " + n + " sorting of ordered array of size " +arraySize + " is " + meanTime + " ms");
            /*******************Part 3. testing on partially-ordered arrays******************************/
            Supplier<Integer[]> partiallyOrderedIntGen = () -> { // the supplier of a ()->Integer[] lambda
                Integer[] intArray = new Integer[arraySize];
                for (int i = 0; i<arraySize; i++) {
                    intArray[i] = randomInt.nextInt();
                }
                InsertionSort<Integer> insertionSort = new InsertionSort<>();
                insertionSort.sort(intArray, 0, intArray.length/2);
                return intArray;
            };
            meanTime = timer.repeat(n,partiallyOrderedIntGen,insertionSorter);
            System.out.println("Mean Time of " + n + " sorting of partially-ordered array of size " +arraySize + " is " + meanTime + " ms");
            /*******************Part 4. testing on reverse-ordered arrays******************************/
            Supplier<Integer[]> reverseOrderedIntGen = () -> { // the supplier of a ()->Integer[] lambda
                Integer[] intArray = new Integer[arraySize];
                for (int i = 0; i<arraySize; i++) {
                    intArray[i] = randomInt.nextInt();
                }
                InsertionSort<Integer> insertionSort = new InsertionSort<>();
                insertionSort.sort(intArray, 0, intArray.length);
                Integer[] intArray_reversed = new Integer[arraySize];
                for (int i = 0; i<arraySize; i++) {
                    intArray_reversed[i] = intArray[arraySize-1-i];
                }
                return intArray_reversed;
            };
            meanTime = timer.repeat(n,reverseOrderedIntGen,insertionSorter);
            System.out.println("Mean Time of " + n + " sorting of reverse-ordered array of size " +arraySize + ", is " + meanTime + " ms");

        }


    }
}
