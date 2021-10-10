package com.company.hw01.course;

import com.company.hw01.team.Athlete;

public class Barrier implements Obstacle{

    private int distance;

    public Barrier(int distance){
        this.distance = distance;
    }

    public int getDistance(){ return  distance; }

    @Override
    public void passIt(Athlete athlete) {
        athlete.jump(distance);
    }

    @Override
    public void showCourse() {
        System.out.println("высота барьера - " + distance + "м.");
    }
}
