package com.company.hw08;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 1.");
        System.out.println("Добавить поддержку SQLite в проект.");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 2.");
        System.out.println("Создать класс-репозиторий, отвечающий за взаимодействие с базой данных.");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 3.");
        System.out.println("Организовать запись данных в базу при каждом успешном API запросе. ");
        System.out.println("Формат - String city, String localDate, String weatherText, Double temperature.");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 4.");
        System.out.println("Организовать чтение из базы всех данных по пункту меню (требует переработки меню).");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 5.");
        System.out.println("Учесть, что соединение всегда нужно закрывать.");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        UserInterface userInterface = new UserInterface();
        userInterface.processing();

    }
}
