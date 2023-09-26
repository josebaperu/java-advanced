package com.pkg.algorithms;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String args[]) {

        int[] data = { -2, 45, 0, 11, -9 };
        BubbleSort.doSort(data);

        System.out.println("Sorted Array in Ascending Order:");
        System.out.println(Arrays.toString(data));
    }
    static void doSort(int array[]) {
        int size = array.length;

        // loop to access each array element
        for (int i = 0; i < (size - 1); i++) {

            // check if swapping occurs
            boolean swapped = false;

            // loop to compare adjacent elements
            for (int j = 0; j < (size - i - 1); j++) {

                int currentIndex = j;
                int nextIndex = j + 1;
                if (array[currentIndex] > array[nextIndex]) {
                    int current = array[currentIndex];
                    int next = array[nextIndex];
                    array[currentIndex] = next;
                    array[nextIndex] = current;
                    swapped = true;
                }
            }
            // no swapping means the array is already sorted
            // so no need for further comparison
            if (!swapped)
                break;

        }
    }
}
