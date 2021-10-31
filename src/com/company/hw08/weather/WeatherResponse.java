package com.company.hw08.weather;

import com.company.hw08.DBHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;

public class WeatherResponse {

    private static final String PROTOKOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String CURRENTCONDITION = "currentconditions";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String FIVE_DAY = "5day";
    private static final String API_KEY = "KSTmeH66CA8a7dfZXS7jEGx6hB4FDRVi";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String RU = "ru-RU";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void getCurrentWeather(String selectedCity) throws IOException, SQLException {

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(CURRENTCONDITION)
                .addPathSegment(VERSION)
                .addPathSegment(detectCityKey(selectedCity))
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", RU)
                .addQueryParameter("details", "true")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response currentDayForecastResponse = okHttpClient.newCall(request).execute();

        String responseStr = currentDayForecastResponse.body().string();

        String weatherText = objectMapper.readTree(responseStr).get(0).at("/WeatherText").asText();
        Double temperature = objectMapper.readTree(responseStr).get(0).at("/Temperature/Metric/Value").asDouble();
        String windLocalized = objectMapper.readTree(responseStr).get(0).at("/Wind/Direction/Localized").asText();
        Double windSpeed = objectMapper.readTree(responseStr).get(0).at("/Wind/Speed/Metric/Value").asDouble();
        Double pressure = objectMapper.readTree(responseStr).get(0).at("/Pressure/Metric/Value").asDouble();
        Double humidity = objectMapper.readTree(responseStr).get(0).at("/RelativeHumidity").asDouble();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DecimalFormat dF = new DecimalFormat( "#.##" );

        CurrentWeather currentWeather = new CurrentWeather(timestamp, selectedCity, weatherText, temperature, windLocalized, windSpeed, pressure, humidity);

        System.out.println("Выведем текущую погоду в " + currentWeather.getCity() + "\n");
        System.out.println("Общие условия:           " + currentWeather.getWeathertext());
        System.out.println("Температура:             " + currentWeather.getTemperature() + " \u2103");
        System.out.println("Направление ветра:       " + currentWeather.getWindlocalized());
        System.out.println("Скорость ветра:          " + dF.format(currentWeather.getWindspeed() * 1000 / 3600) + " м/с");
        System.out.println("Давление:                " + dF.format(currentWeather.getPressure() * 0.750064) + " мм рт. ст.");
        System.out.println("Относительная влажность: " + currentWeather.getHumidity() + " % ");

        DBHandler dbHandler = new DBHandler();
        if (dbHandler.addCurrentWeather(currentWeather)) {
            System.out.println("\nЗапрос обработан и сохранен в БД в:  " + currentWeather.getTimestamp());
        }
    }

    public static void getForecastWeather(String selectedCity) throws IOException, SQLException {

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(FORECASTS)
                .addPathSegment(VERSION)
                .addPathSegment(DAILY)
                .addPathSegment(FIVE_DAY)
                .addPathSegment(detectCityKey(selectedCity))
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", RU)
                .addQueryParameter("metric", "true")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response fiveDayForecastResponse = okHttpClient.newCall(request).execute();
        String responseStr = fiveDayForecastResponse.body().string();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        System.out.println("Выведем прогноз погоды на 5 дней для города " + selectedCity + "\n");

        DayWeather[] dayWeather = new DayWeather[5];

        for (int i = 0; i < objectMapper.readTree(responseStr).at("/DailyForecasts").size(); i++) {

            String date = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Date").asText().split("T")[0];
            String weatherTextDay = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Day/IconPhrase").asText();
            String weatherTextNight = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Night/IconPhrase").asText();
            Double temperatureMin = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Temperature/Minimum/Value").asDouble();
            Double temperatureMax = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Temperature/Maximum/Value").asDouble();

            dayWeather[i] = new DayWeather(date, weatherTextDay, weatherTextNight, temperatureMin, temperatureMax);

            System.out.println("   " + dayWeather[i].getDate() + ": днем ожидается - " + dayWeather[i].getDay() + "; ночью ожидается - " + dayWeather[i].getNight() + "; температура будет в пределах от " + dayWeather[i].getTmin() + " \u2103 до " + dayWeather[i].getTmax() + " \u2103");

        }

        Forecast5dayWeather forecast5dayWeather = new Forecast5dayWeather(timestamp, selectedCity, dayWeather);

        DBHandler dbHandler = new DBHandler();
        if (dbHandler.addForecastWeather(forecast5dayWeather)) {
            System.out.println("\nЗапрос обработан и сохранен в БД в:  " + forecast5dayWeather.getTimestamp());
        }
    }
    public static String detectCityKey(String selectCity) throws IOException {

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectCity)
                .addQueryParameter("language", RU)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        try {
            return(objectMapper.readTree(responseString).get(0).at("/Key").asText());
        }
        catch (NullPointerException e){

        }
        return "";
    }
}
