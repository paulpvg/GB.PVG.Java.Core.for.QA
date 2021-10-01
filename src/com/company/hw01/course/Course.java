package com.company.hw01.course;

import com.company.hw01.team.*;

public class Course {

    Obstacle [] obstacles;

    public Course(Obstacle[] obstacles){
        this.obstacles = obstacles;
    }

    public void showCourse(){
        for(Obstacle o : obstacles){
            o.showCourse();
        }
    }

    public void passIt(Team team){
        Athlete[] teamMembers = team.getTeamMember();
        if(teamMembers.length > 0){
            for (Athlete a: teamMembers){
                for (Obstacle o: obstacles){
                    o.passIt(a);
                    if (!a.isOnDistance()) break; // если уж какое-то препятствие не прошел, то не за чем дальше мучиться
                }
            }
        } else {
            System.out.println("В команду забыли кого-нибудь принять");
        }
    }
}
