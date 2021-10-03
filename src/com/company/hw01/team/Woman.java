package com.company.hw01.team;

public class Woman implements Athlete{

    String name;
    private final int maxDistanceRun = 5000;
    private final int maxDistanceSwim = 50;
    private final int maxHeightJump = 3;
    boolean onDistance;

    public Woman(String name){
        this.name = name;
        this.onDistance = true;
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void run(int distance) {
        if ((distance <= maxDistanceRun) && (distance >0)) System.out.println(name + " пробежала " + distance + "м");
        else if (distance > maxDistanceRun) {
            System.out.println(name + " пробежала " + maxDistanceRun + "м и, утомившись, пала замертво");
            onDistance = false;
        }
        else if (distance == 0) {
            System.out.println(name + " поблагодарила, что её никуда не посылают, спряталась под ёлкой и уснула");
            onDistance = false;
        }
        else {
            System.out.println(name + " не рак и пятиться не желает"); // отрицательная дистанция
            onDistance = false;
        }
    }

    @Override
    public void swim(int distance) {
        if ((distance <= maxDistanceSwim) && (distance >0)) System.out.println(name + " проплыла " + distance + "м");
        else if (distance > maxDistanceSwim) {
            System.out.println(name + " проплыла " + maxDistanceSwim + "м и, утомившись, утонула");
            onDistance = false;
        }
        else if (distance == 0) {
            System.out.println(name + " увидела, что река пересохла, и побежала дальше");
        }
        else {
            System.out.println(name + " не рак и пятиться не желает"); // отрицательная дистанция
            onDistance = false;
        }
    }

    @Override
    public void jump(int distance) {
        if ((distance <= maxHeightJump) && (distance >0)) System.out.println(name + " подпрыгнув на " + distance + "м, взяла барьер и побежала к финишу");
        else if (distance > maxHeightJump) {
            System.out.println(name + " подпрыгнула на " + maxHeightJump + "м, не осилила и решила барьер обойти - была дисквалифицирована");
            onDistance =false;
        }
        else if (distance == 0) {
            System.out.println(name + " подумала, что где-то здесь должен был быть барьер, и пошла его искать");
            onDistance = false;
        }
        else {
            System.out.println(name + " взяла лопату и начала делать подкоп"); // отрицательная высота првратилась в глубину
            onDistance = false;
        }
    }

    @Override
    public void showMembers() {
        System.out.println(name + " - может пробежать " + maxDistanceRun + "м, проплыть " + maxDistanceSwim + "м и подпрыгнуть на " + maxHeightJump + "м.");
    }

    @Override
    public void showResult() {
        if(onDistance) System.out.println(name + " - дистанцию прошла");
        else System.out.println(name + " - полосу препятствий не осилила");
    }
}

