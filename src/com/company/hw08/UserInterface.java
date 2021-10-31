package com.company.hw08;

import com.company.hw08.weather.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class UserInterface {
    public void processing() throws IOException {

        Scanner scanner = new Scanner(System.in);

        String  city = "";
        boolean exec = true;
        boolean respCity = false;
        boolean respForecast = false;

        while (exec) {

            while ((!respCity) && exec) {

                respForecast = false;

                System.out.println("Введите имя города или набирите 'выход' для выхода из программы: ");
                city = scanner.nextLine();

                if (city.equals("выход")) {
                    System.out.println("Спасибо за внимание, до скорых встреч!!!\n");
                    exec = false;
                }
                else if (WeatherResponse.detectCityKey(city) != "") {
                    respCity = true;

                }
                else {
                    System.out.println("Моя не знает такого города, попробуйте еще раз, или наберите 'выход' для выхода из программы\n");
                }
            }

            while ((!respForecast) && exec) {

                System.out.println("\nВведите: 1 - для получения текущей погоды в г. " + city + ";\n" +
                        "         5 - для получения прогноза погоды на 5 дней в г. " + city + " ;\n" +
                        "         0 - для смены города;\n" +
                        "         2 - для вывода всех запросов текущей погоды, сохраненных в БД, по всем городам;\n" +
                        "         3 - для вывода всех запросов текущей погоды, сохраненных в БД, по текущему г. " + city + ";\n" +
                        "         7 - для вывода всех запросов 5-дневных прогнозов погоды, сохраненных в БД, по всем городам;\n" +
                        "         8 - для вывода всех запросов 5-дневных прогнозов погоды, сохраненых в БД, по текущему г. " + city + ";\n" +
                        "         выход - для выхода из программы");

                String command = scanner.nextLine();

                if (command.equals("выход")) {
                    System.out.println("Спасибо за внимание, до скорых встреч!!!\n");
                    exec = false;
                }
                else if (command.equals("0")) {
                    respForecast = true;
                    respCity = false;
                }
                else if (command.equals("1")){
                    try {
                        WeatherResponse.getCurrentWeather(city);
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("5")){
                    try {
                        WeatherResponse.getForecastWeather(city);
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("2")){
                    try {
                        DBHandler dbHandler = new DBHandler();
                        List<CurrentWeather> list = dbHandler.getCurrentWeatherAll();

                        if (!list.isEmpty()) {
                            System.out.println(list);
                        }
                        else {
                            System.out.println("В БД информации с текущей погодой не нашлось :(");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("3")){
                    try {
                        DBHandler dbHandler = new DBHandler();
                        List<CurrentWeather> list = dbHandler.getCurrentWeatherCity(city);

                        if (!list.isEmpty()) {
                            System.out.println(list);
                        }
                        else {
                            System.out.println("В БД информации по г. " + city + " не нашлось :(");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("7")){
                    try {
                        DBHandler dbHandler = new DBHandler();
                        List<Forecast5dayWeather> list = dbHandler.getForecastWeatherAll();

                        if (!list.isEmpty()) {
                            System.out.println(list);
                        }
                        else {
                            System.out.println("В БД информации с 5-ти дневными прогнозами не нашлось :(");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("8")){
                    try {
                        DBHandler dbHandler = new DBHandler();
                        List<Forecast5dayWeather> list = dbHandler.getForecastWeatherCity(city);

                        if (!list.isEmpty()) {
                            System.out.println(list);
                        }
                        else {
                            System.out.println("В БД информации с 5-ти дневными прогнозами по г. " + city + " не нашлось :(");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Моя твоя не понимать...\n");
                }
            }
        }
    }
}
