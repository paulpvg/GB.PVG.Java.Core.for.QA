package com.company.hw01;

public abstract class Specialist {

    public enum Position{
        TEAMLEAD,
        WEBDESIGNER,
        IMPOSER,
        FRONTDEV,
        BACKDEV,
        SYSADM,
        ENGINEER,
        QA;
    }

    private String name;
    private Position position;
    private int age;
    private int salary;

    public Specialist (String name, Position position, int age, int salary) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
