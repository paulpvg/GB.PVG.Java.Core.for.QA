package com.company.hw09;

import java.util.List;

public class Student {

    private String name;
    private List<Course> listCourse;

    public Student(String name, List<Course> listCourse) {
        this.name = name;
        this.listCourse = listCourse;
    }

    @Override
    public String toString() {
        return "\nСтудент " + name +                 ", \n   посещает следующие курсы: " + listCourse;
    }

    public List<Course> getListCourse() {
        return listCourse;
    }
}



