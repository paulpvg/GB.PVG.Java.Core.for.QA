package com.company.hw06;

import com.fasterxml.jackson.databind.*;
import okhttp3.*;
import java.io.IOException;

public class AccuweatherForecast {

    private static final String PROTOKOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
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

    public static void getWeather(String selectedCity) throws IOException {

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

        System.out.println("Выведем сформированный HTML-запрос: \n   " + httpUrl + "\n");

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response fiveDayForecastResponse = okHttpClient.newCall(request).execute();
        System.out.println("Выведем полученную погоду в " + selectedCity + " на ближайшие 5 дней в виде json-строки: \n   " + fiveDayForecastResponse.body().string() + "\n");

    }

    private static String detectCityKey(String selectCity) throws IOException {

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

        return (objectMapper.readTree(responseString).get(0).at("/Key").asText());
    }
}
