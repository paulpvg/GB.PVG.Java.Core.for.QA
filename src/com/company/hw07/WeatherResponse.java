package com.company.hw07;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;

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

    public static void getCurrentWeather(String selectedCity) throws IOException {

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
        String temperature = objectMapper.readTree(responseStr).get(0).at("/Temperature/Metric/Value").asText();
        String windLocalized = objectMapper.readTree(responseStr).get(0).at("/Wind/Direction/Localized").asText();
        String windSpeed = objectMapper.readTree(responseStr).get(0).at("/Wind/Speed/Metric/Value").asText();
        String pressure = objectMapper.readTree(responseStr).get(0).at("/Pressure/Metric/Value").asText();
        String humidity = objectMapper.readTree(responseStr).get(0).at("/RelativeHumidity").asText();

        System.out.println("Выведем текущую погоду в " + selectedCity + "\n");
        System.out.println("Общие условия:           " + weatherText);
        System.out.println("Температура:             " + temperature + " C");
        System.out.println("Направление ветра:       " + windLocalized);
        System.out.println("Скорость ветра:          " + windSpeed + " км/ч");
        System.out.println("Давление:                " + pressure + " мбар");
        System.out.println("Относительная влажность: " + humidity + " % ");

    }

    public static void getForecastWeather(String selectedCity) throws IOException {

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

        System.out.println("Выведем прогноз погоды на 5 дней для города " + selectedCity + "\n");

        for (int i = 0; i < objectMapper.readTree(responseStr).at("/DailyForecasts").size(); i++) {
            String date = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Date").asText().split("T")[0];
            String weatherTextDay = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Day/IconPhrase").asText();
            String weatherTextNight = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Night/IconPhrase").asText();
            String temperatureMin = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Temperature/Minimum/Value").asText();
            String temperatureMax = objectMapper.readTree(responseStr).at("/DailyForecasts").get(i).at("/Temperature/Maximum/Value").asText();
            System.out.println("   " + date + ": днем ожидается - " + weatherTextDay + "; ночью ожидается - " + weatherTextNight + "; температура будет в пределах от " + temperatureMin + " C до " + temperatureMax + " C");
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

        };

        return "";
    }
}
