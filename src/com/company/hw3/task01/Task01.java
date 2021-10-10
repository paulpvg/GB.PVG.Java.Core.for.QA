package com.company.hw3.task01;

import com.company.hw3.exceptions.*;

public class Task01 {

    public static <T> void swap(T[] array, int firstIndex, int secondIndex) {
        if (((firstIndex >= 0) && (firstIndex < array.length)) && ((secondIndex >= 0) && (secondIndex < array.length))) {
            T tmpVal = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = tmpVal;
        }
        else {
            throw new MyArrayIndexException();
        }
    }
}
