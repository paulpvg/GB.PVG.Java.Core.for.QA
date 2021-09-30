package com.company.hw01.team;

public class Colonel implements Athlete {
    String name;
    private final int maxDistanceRun = 20;
    private final int maxDistanceSwim = 20;
    private final int maxHeightJump = 0;
    boolean onDistance;

    public Colonel(String name) {
        this.name = name;
        this.onDistance = true;
    }

    public boolean isOnDistance() {
        return onDistance;
    }

    public void run(int distance) {
        if ((distance <= maxDistanceRun) && (distance > 0)) System.out.println(name + " пробежал " + distance + "м");
        else if (distance > maxDistanceRun) {
            System.out.println(name + " пробежал " + maxDistanceRun + "м и, утомившись, пал замертво");
            onDistance = false;
        }
        else if (distance == 0) {
            System.out.println(name + " поблагодарил, что его никуда не посылают, спрятался под ёлкой и уснул");
            onDistance = false;
        }
        else {
            System.out.println(name + " не рак и пятиться не желает");
            onDistance = false;
        }
    }

    public void swim(int distance) {
        if ((distance <= maxDistanceSwim) && (distance > 0)) System.out.println(name + " проплыл " + distance + "м");
        else if (distance > maxDistanceSwim) {
            System.out.println(name + " проплыл " + maxDistanceSwim + "м и, утомившись, утонул");
            onDistance = false;
        }
        else if (distance == 0) {
            System.out.println(name + " побархтался наместе 30 мин, устал и утонул");
            onDistance = false;
        }
        else {
            System.out.println(name + " не рак и пятиться не желает");
            onDistance = false;
        }
    }

    public void jump(int height) {
        if (height < maxHeightJump) {
            System.out.println(name + " взял лопату и начал делать подоп");
            onDistance = false;
        }
        else System.out.println(name + " сказал, что прыгать ему не по чину, и прошел сквозь препятствие");
    }

    public void showResult() {
        if(onDistance) System.out.println(name + " дистанцию прошел");
        else System.out.println(name + " полосу препятствий не осилил");
    }
}
