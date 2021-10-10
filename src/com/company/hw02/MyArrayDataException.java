package com.company.hw02;

public class MyArrayDataException extends RuntimeException {
    public MyArrayDataException(String message) {
        super("В ячейке матрицы: " + message + " находятся некорректные данные");
    }
}
