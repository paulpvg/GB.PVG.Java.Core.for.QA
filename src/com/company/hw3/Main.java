package com.company.hw3;

import com.company.hw3.exceptions.*;
import com.company.hw3.fruits.*;
import com.company.hw3.task01.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 1.");
        System.out.println("Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        Cube[] cubes = {new Cube("green"), new Cube("red"), new Cube("blue"), new Cube("blue"), new Cube("red"), new Cube("green")};

        System.out.println("Изначально кубики на столе лежат в следующем порядке:\n");
        String cubesOut = "";
        for (Cube c:cubes) {
            cubesOut += " " + c.getColor();
        }
        System.out.println(cubesOut + "\n");

        System.out.println("Предадимся перфекционизму и поменяем местами 1-ый и 5-ый");

        try {
            Task01.swap(cubes, 0, 4);
        }
        catch (MyArrayIndexException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Получившийся порядок кубиков на столе:\n");
        cubesOut = "";
        for (Cube с:cubes) {
            cubesOut += " " + с.getColor();
        }
        System.out.println(cubesOut + "\n");

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 2.");
        System.out.println("a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;");
        System.out.println("b. Класс Box, в который  можно  складывать  фрукты. Коробки условно сортируются  по  типу фрукта, поэтому ");
        System.out.println("   в одну коробку нельзя сложить и яблоки, и апельсины;");
        System.out.println("c. Для хранения фруктов внутри коробки можно использовать ArrayList;");
        System.out.println("d. Сделать  метод  getWeight(), который  высчитывает вес коробки, зная вес одного фрукта и их количество: ");
        System.out.println("   вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);");
        System.out.println("e. Внутри  класса Box сделать метод compare(), который  позволяет сравнить текущую коробку с той, которую");
        System.out.println("   подадут в compare() в  качестве параметра. true – если их массы равны, false в противоположном случае.");
        System.out.println("   Можно сравнивать коробки с яблоками и апельсинами;");
        System.out.println("f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку");
        System.out.println("   фруктов: нельзя  яблоки  высыпать в коробку  с апельсинами. Соответственно, в текущей  коробке фруктов");
        System.out.println("   не остается, а в другую перекидываются объекты, которые были в первой;");
        System.out.println("g. Не забываем про метод добавления фрукта в коробку.");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        Box<Orange> orange1 = new Box<>();
        Box<Orange> orange2 = new Box<>();
        Box<Apple> apple1 = new Box<>();
        Box<Apple> apple2 = new Box<>();

        System.out.println("Заполним две коробки апельсинами и две коробки яблоками");

        orange1.addFruit(new Orange(), 6);
        orange2.addFruit(new Orange(), 7);
        apple1.addFruit(new Apple(), 9);
        apple2.addFruit(new Apple(), 11);

        System.out.println("Выведем массу каждой коробки:");

        System.out.println(" Коробка 1 весит: " + orange1.getWeight());
        System.out.println(" Коробка 2 весит: " + orange2.getWeight());
        System.out.println(" Коробка 3 весит: " + apple1.getWeight());
        System.out.println(" Коробка 4 весит: " + apple2.getWeight());

        System.out.println("\nСравним коробки по весу ");
        System.out.println("Коробку 1 сравним с коробой 3: \n " + orange1.compare(apple1));
        System.out.println("Коробку 2 сравним с коробой 4: \n " + orange2.compare(apple2) + "\n");

        System.out.println("Пересыпем фрукты из 1-ой во 2-ую коробку");
        orange1.transferFruit(orange2);
        System.out.println("Пересыпем фрукты из 3-ей в 4-ую коробку");
        apple1.transferFruit(apple2);
        System.out.println("\nВыведем массу каждой коробки: ");
        System.out.println(" Коробка 1 весит: " + orange1.getWeight());
        System.out.println(" Коробка 2 весит: " + orange2.getWeight());
        System.out.println(" Коробка 3 весит: " + apple1.getWeight());
        System.out.println(" Коробка 4 весит: " + apple2.getWeight());
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }
}
