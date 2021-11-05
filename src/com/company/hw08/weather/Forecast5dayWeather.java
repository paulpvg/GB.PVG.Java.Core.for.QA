package com.company.hw08.weather;

import java.sql.Timestamp;

public class Forecast5dayWeather {

    private Timestamp timestamp;
    private String city;
    private DayWeather[] dayweather;

    public Forecast5dayWeather(Timestamp timestamp, String city, DayWeather[] dayweather) {
        this.timestamp = timestamp;
        this.city = city;
        this.dayweather = dayweather;
    }

    @Override
    public String toString () {

        String str = "";

        for (int i = 0; i < 5; i++){
            str += dayweather[i];
        }

        return "\nПрогноз погоды на 5 дней в г. " + city + ", запрошенный " + timestamp + ": \n" + str;

    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getCity() {
        return city;
    }

    public DayWeather[] getDayweather() {
        return dayweather;
    }
}
