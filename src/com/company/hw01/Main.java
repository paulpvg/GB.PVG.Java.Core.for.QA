package com.company.hw01;

import com.company.hw01.team.*;
import com.company.hw01.course.*;

public class Main {

    public static void main(String[] args) {

        Athlete[] athletes = {new Man("Зигфрид"), new Woman("Брумгильда"), new Child("Чук"), new Child("Гек")};
        Obstacle[] obstacles = {new Trail(500), new River(0), new Barrier(3)};
        Team team = new Team("Трудовые резервы", athletes);

        System.out.println("Создана команда: " + team.getTeamName());
        System.out.println("Команда состоит из: ");
        team.showMembers();

        Course course = new Course(obstacles);

        System.out.println("Сформирована полоса препятствий:");
        course.showCourse();

        System.out.println("Результаты прохождения полосы препятствий:");
        course.passIt(team);

        System.out.println("Полностью полосу препятствий прошли:");
        team.showMembersFinishedCourse();
    }
}
