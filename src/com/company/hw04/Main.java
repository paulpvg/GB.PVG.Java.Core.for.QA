package com.company.hw04;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 1.");
        System.out.println("Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список ");
        System.out.println("уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается ");
        System.out.println("каждое слово.");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        String[] words = {"IPA", "NEIPA", "Lager", "APA", "NEIPA", "Porter", "NEIPA", "Weissbier", "IPA", "Stout", "Stout", "Pilsner", "Lambic", "NEIPA", "Stout", "Lambic", "Stout", "DIPA", "NEIPA", "Stout"};

        System.out.println("Изначальный массив слов:");
        System.out.println(Arrays.toString(words));

        System.out.println("\nВыведем список уникальных слов из нашего массива:");
        Set<String> uniqWords = new HashSet<>(Arrays.asList(words));
        System.out.println(uniqWords);

        System.out.println("\nВыведем сколько раз каждое слово встречалось в нашем массиве:");
        Map<String, Integer> hm = new HashMap<>();
        for (String word : words) {
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }
        System.out.println(hm + "\n");

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 2.");
        System.out.println("Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий и телефонных номеров.");
        System.out.println("В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать");
        System.out.println("номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае");
        System.out.println("однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        Phonebook phonebook = new Phonebook();

        phonebook.add("Зигфрид", "+7(495)123-4567");
        phonebook.add("Брумгильда", "+7(495)222-3322");
        phonebook.add("Петров", "+7(495)333-2233");
        phonebook.add("Шниперсон", "+7(499)322-2237");
        phonebook.add("Петров", "+7(3519)22-3322");
        phonebook.add("Петров", "+7(812)223-3329");
        phonebook.add("Горыныч", "+7(48236)3-0001");
        phonebook.add("Горыныч", "+7(48236)3-0002");
        phonebook.add("Горыныч", "+7(48236)3-0003");

        System.out.println("\nВыведем наш телефонный справочник:");

        System.out.println("Телефон Зигфрида:   " + phonebook.get("Зигфрид"));
        System.out.println("Телефон Петрова:    " + phonebook.get("Петров"));
        System.out.println("Телефон Шниперсона: " + phonebook.get("Шниперсон"));
        System.out.println("Телефон Горыныча:   " + phonebook.get("Горыныч"));
        System.out.println("Телефон Брумгильды: " + phonebook.get("Брумгильда") + "\n");

    }
}
