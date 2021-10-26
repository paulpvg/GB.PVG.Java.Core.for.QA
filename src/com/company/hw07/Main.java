package com.company.hw07;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 1.");
        System.out.println("Реализовать корректный вывод информации о текущей погоде. Создать класс WeatherResponse и десериализовать");
        System.out.println("ответ сервера. Выводить пользователю только текстовое описание погоды и температуру в градусах Цельсия.");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 2.");
        System.out.println("Реализовать корректный выход из программы");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 3.");
        System.out.println("Реализовать вывод погоды на следующие 5 дней в формате: ");
        System.out.println("В городе CITY на дату DATE ожидается WEATHER_TEXT, температура - TEMPERATURE");
        System.out.println("где CITY, DATE, WEATHER_TEXT и TEMPERATURE - уникальные значения для каждого дня.");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        UserInterface userInterface = new UserInterface();
        userInterface.processing();
    }
}
