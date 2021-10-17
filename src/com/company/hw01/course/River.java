package com.company.hw01.course;

import com.company.hw01.team.Athlete;

public class River implements Obstacle{

    private int distance;

    public River(int distance){
        this.distance = distance;
    }

    @Override
    public void passIt(Athlete athlete) {
        athlete.swim(distance);
    }

    @Override
    public void showCourse() {
        System.out.println("ширина реки - " + distance + "м, ");
    }
}
