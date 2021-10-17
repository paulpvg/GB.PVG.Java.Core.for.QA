package com.company.hw02;

public class MyArraySizeException extends RuntimeException{
    public MyArraySizeException() {
        super("Размер массива не соответствует ожидаемому!");
    }
}
