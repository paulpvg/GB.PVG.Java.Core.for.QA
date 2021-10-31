package com.company.hw08.weather;

public class DayWeather {
    private String date;
    private String day;
    private String night;
    private Double tmin;
    private Double tmax;

    public DayWeather(String date, String day, String night, Double tmin, Double tmax) {
        this.date = date;
        this.day = day;
        this.night = night;
        this.tmin = tmin;
        this.tmax = tmax;
    }

    @Override
    public String toString() {
        return "   " + date + ": днем ожидается - " + day + "; ночью - " + night + "; температура будет в пределах от " + tmin + " \u2103 до " + tmax + " \u2103 \n";
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getNight() {
        return night;
    }

    public Double getTmin() {
        return tmin;
    }

    public Double getTmax() {
        return tmax;
    }
}
