package com.company.hw09;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student("Сигизмунд", Arrays.asList(
                new Course("JAVA_Level1"),
                new Course("JAVA_Level2"),
                new Course("JAVA_Level3"),
                new Course("Web_Design"))));

        students.add(new Student("Зигфрид", Arrays.asList(
                new Course("BigData_for_dummies"),
                new Course("DataScience_for_dummies"))));

        students.add(new Student("Брумгильда", Arrays.asList(
                new Course("JAVA_Level1"),
                new Course("JAVA_Level2"),
                new Course("JAVA_Level3"),
                new Course("Python_basics"))));

        students.add(new Student("Шниперсон", Arrays.asList(
                new Course("JAVA_Level1"),
                new Course("JAVA_Level2"),
                new Course("JAVA_Level3"),
                new Course("Web_Design"),
                new Course("Python_basics"),
                new Course("SQL_basics"))));

        students.add(new Student("Моня", Arrays.asList(
                new Course("Web_Design"))));

        students.add(new Student("Изя", Arrays.asList(
                new Course("Linux_getting_start"),
                new Course("SQL_basics"))));

        students.add(new Student("Шариков", Arrays.asList(
                new Course("Python_basics"),
                new Course("Linux_getting_start"),
                new Course("SQL_basics"))));

        System.out.println("---------------------------------------------------------------------------------------------------------\n" +
                            "Задача 1.\n" +
                            "Написать функцию, принимающую список Student и возвращающую список уникальных курсов,\n" +
                            "на которые подписаны студенты.\n" +
                            "---------------------------------------------------------------------------------------------------------\n" +
                            "Задача 2.\n" +
                            "Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных\n" +
                            "(любознательность определяется количеством курсов)\n" +
                            "---------------------------------------------------------------------------------------------------------\n" +
                            "Задача 3.\n" +
                            "Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,\n" +
                            "которые посещают этот курс\n" +
                            "---------------------------------------------------------------------------------------------------------\n");

        System.out.println("Выведем всех наших студентов: \n" + students);

        System.out.println("\nВыведем список уникальных курсов, которые посещают студенты:");
        System.out.println(getUniqCourses(students));

        System.out.println("\nВыведем трех самых любознательных студентов:");
        System.out.println(getActiveStudents(students));

        Course course = new Course("Python_basics");
        System.out.println("\nВыведем студентов, посещающих курс " + course.getCourseTitle() + ":");
        System.out.println(getStudentsThisCourse(students, course));
        System.out.println("");
    }

    public static Set<Course> getUniqCourses(List <Student> students) {
        return students.stream()
                .map(s -> s.getListCourse())
                .flatMap(c -> c.stream())
                .collect(Collectors.toSet());
    }

    public static List<Student> getActiveStudents(List<Student> studentList) {
        return studentList.stream()
                .sorted((s1, s2) -> s2.getListCourse().size() - s1.getListCourse().size())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Student> getStudentsThisCourse(List<Student> studentList, Course course) {
        return studentList.stream()
                .filter(student -> student.getListCourse().contains(course))
                .collect(Collectors.toList());
    }
}
