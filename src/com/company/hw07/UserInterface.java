package com.company.hw07;

import java.io.IOException;
import java.util.Scanner;

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

                System.out.println("\nВведите: 1 - для получения текущей погоды в " + city + ";\n         5 - для получения прогноза погоды на 5 дней в " + city + " ;\n         0 - для смены города;\n         выход - для выхода из программы");

                String command = scanner.nextLine();

                if (command.equals("выход")) {
                    System.out.println("Спасибо за внимание, до скорых встреч!!!\n");
                    exec = false;
                }
                else if (command.equals("0")) {
                    respForecast = true;
                    respCity = false;
                }
                else if (!(command.equals("1")) && !(command.equals("5"))){
                    System.out.println("Моя твоя не понимать...\n");
                }
                else if (command.equals("1")){
                    try {
                        WeatherResponse.getCurrentWeather(city);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("5")){
                    try {
                        WeatherResponse.getForecastWeather(city);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
