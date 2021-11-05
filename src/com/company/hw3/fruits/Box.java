package com.company.hw3.fruits;

import java.util.ArrayList;

public class Box <T extends Fruit>{

    private ArrayList<T> list;

    public Box() {
        this.list = new ArrayList<>();
    }

    public float getWeight() {
        float weight = 0.0f;

        for (T o : list) {
            weight += o.getWeight();
        }
        return weight;
    }

    public void transferFruit(Box<T> anotherBox) {
        if(list == anotherBox.list){
            System.out.println("\nПопытка переложить фрукты в коробке из самой в себя...");
        }
        else {
            anotherBox.list.addAll(list);
            list.clear();
        }
    }

    public void addFruit(T fruit, int amount) {
        for(int i = 0; i < amount; i++) {
            list.add(fruit);
        }
    }

    public boolean compare(Box<?> o) {
        return Math.abs(this.getWeight() - o.getWeight()) < 0.001;
        //Допустим, что точность наших весов 0.001
    }
}
