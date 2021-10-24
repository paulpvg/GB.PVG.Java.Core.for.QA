package com.company.hw3.exceptions;

public class MyArrayIndexException extends RuntimeException{
    public MyArrayIndexException() {
        super(" __Передаваемый индекс находится за пределами массива!__");
    }
}