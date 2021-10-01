package com.company.hw01.team;

public class Man implements Athlete{

    String name;
    private final int maxDistanceRun = 10000;
    private final int maxDistanceSwim = 200;
    private final int maxHeightJump = 5;
    boolean onDistance;

    public Man(String name){
        this.name = name;
        this.onDistance = true;
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void run(int distance) {
        if ((distance <= maxDistanceRun) && (distance >0)) System.out.println(name + " пробежал " + distance + "м");
        else if (distance > maxDistanceRun) {
            System.out.println(name + " пробежал " + maxDistanceRun + "м и, утомившись, пал замертво");
            onDistance = false;
        }
        else if (distance == 0) {
            System.out.println(name + " поблагодарил, что его никуда не посылают, спрятался под ёлкой и уснул");
            onDistance = false;
        }
        else {
            System.out.println(name + " не рак и пятиться не желает"); // отрицательная дистанция
            onDistance = false;
        }
    }

    @Override
    public void swim(int distance) {
        if ((distance <= maxDistanceSwim) && (distance >0)) System.out.println(name + " проплыл " + distance + "м");
        else if (distance > maxDistanceSwim) {
            System.out.println(name + " проплыл " + maxDistanceSwim + "м и, утомившись, утонул");
            onDistance = false;
        }
        else if (distance == 0) {
            System.out.println(name + " увидел, что река пересохла, и побежал дальше");
        }
        else {
            System.out.println(name + " не рак и пятиться не желает"); // отрицательная дистанция
            onDistance = false;
        }
    }

    @Override
    public void jump(int distance) {
        if ((distance <= maxHeightJump) && (distance >0)) System.out.println(name + " подпрыгнув на " + distance + "м, взял барьер и побежал  к финишу");
        else if (distance > maxHeightJump) {
            System.out.println(name + " подпрыгнул на " + maxHeightJump + "м, не осилил и решил барьер обойти - был дисквалифицирован");
            onDistance =false;
        }
        else if (distance == 0) {
            System.out.println(name + " подумал, что где-то здесь должен был быть барьер, и пошел его искать");
            onDistance = false;
        }
        else {
            System.out.println(name + " взял лопату и начал делать подкоп"); // отрицательная высота првратилась в глубину
            onDistance = false;
        }
    }

    @Override
    public void showMembers() {
        System.out.println(name + " - может пробежать " + maxDistanceRun + "м, проплыть " + maxDistanceSwim + "м и подпрыгнуть на " + maxHeightJump + "м.");
    }

    @Override
    public void showResult() {
        if(onDistance) System.out.println(name + " - дистанцию прошел");
        else System.out.println(name + " - полосу препятствий не осилил");
    }
}
