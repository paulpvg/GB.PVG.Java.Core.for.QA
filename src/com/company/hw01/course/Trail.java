package com.company.hw01.course;

import com.company.hw01.team.Athlete;

public class Trail implements Obstacle {

    private int distance;

    public Trail (int distance){ this.distance = distance; }

    public int getDistance(){return  distance; }

    @Override
    public void passIt(Athlete athlete) {
        athlete.run(distance);
    }

    @Override
    public void showCourse() {
        System.out.println("длина тропы - " + distance + "м, ");
    }
}


