package com.company.hw08.weather;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class CurrentWeather {

    private Timestamp timestamp;
    private String city;
    private String weathertext;
    private Double temperature;
    private String windlocalized;
    private Double windspeed;
    private Double pressure;
    private Double humidity;

    public CurrentWeather(Timestamp timestamp, String city, String weathertext, Double temperature, String windlocalized, Double windspeed, Double pressure, Double humidity) {
        this.timestamp = timestamp;
        this.city = city;
        this.weathertext = weathertext;
        this.temperature = temperature;
        this.windlocalized = windlocalized;
        this.windspeed = windspeed;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @Override
    public String toString() {

        DecimalFormat dF = new DecimalFormat( "#.##" );

        return "\nТекущая погода в г. " + city + ", запрошенная " + timestamp + ": " +
                "\nОбщие условия:           " + weathertext +
                "\nТемпература:             " + temperature + " \u2103" +
                "\nНаправление ветра:       " + windlocalized +
                "\nСкорость ветра:          " + dF.format((windspeed) * 1000 / 3600) + " м/с" +
                "\nДавление:                " + dF.format((pressure) * 0.750064) + " мм рт. ст." +
                "\nОтносительная влажность: " + humidity + " %\n";
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getCity() {
        return city;
    }

    public String getWeathertext() {
        return weathertext;
    }

    public Double getTemperature() {
        return temperature;
    }

    public String getWindlocalized() {
        return windlocalized;
    }

    public Double getWindspeed() {
        return windspeed;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }
}
