package com.company.hw08;

import com.company.hw08.weather.*;
import org.sqlite.JDBC;
import java.sql.*;
import java.util.*;

public class DBHandler {

    private final String PATH_TO_DB = "jdbc:sqlite:src/com/company/hw08/dbRespAccuweather.db";

    private Connection connection;

    public DBHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(PATH_TO_DB);
    }

    public boolean addCurrentWeather(CurrentWeather currentWeather) throws SQLException{
        try(PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO  CurrentWeather('timecode', 'city', 'weathertext', 'temperature', 'windlocalized', 'windspeed', 'pressure', 'humidity')" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        )){
            statement.setObject(1, currentWeather.getTimestamp());
            statement.setObject(2, currentWeather.getCity());
            statement.setObject(3, currentWeather.getWeathertext());
            statement.setObject(4, currentWeather.getTemperature());
            statement.setObject(5, currentWeather.getWindlocalized());
            statement.setObject(6, currentWeather.getWindspeed());
            statement.setObject(7, currentWeather.getPressure());
            statement.setObject(8, currentWeather.getHumidity());
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addForecastWeather(Forecast5dayWeather forecast5dayWeather) throws SQLException{
        try(PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Forecast5DayWeather('timecode', 'city', 'date1', 'day1', 'night1', 'tmin1', 'tmax1', " +
                                                                         "'date2', 'day2', 'night2', 'tmin2', 'tmax2', " +
                                                                         "'date3', 'day3', 'night3', 'tmin3', 'tmax3', " +
                                                                         "'date4', 'day4', 'night4', 'tmin4', 'tmax4', " +
                                                                         "'date5', 'day5', 'night5', 'tmin5', 'tmax5')" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        )){
            statement.setObject(1, forecast5dayWeather.getTimestamp());
            statement.setObject(2, forecast5dayWeather.getCity());
            for(int i = 0; i < 5; i++){
                statement.setObject(i*5+3, forecast5dayWeather.getDayweather()[i].getDate());
                statement.setObject(i*5+4, forecast5dayWeather.getDayweather()[i].getDay());
                statement.setObject(i*5+5, forecast5dayWeather.getDayweather()[i].getNight());
                statement.setObject(i*5+6, forecast5dayWeather.getDayweather()[i].getTmin());
                statement.setObject(i*5+7, forecast5dayWeather.getDayweather()[i].getTmax());
            }
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<CurrentWeather> getCurrentWeatherCity(String city) throws SQLException{
        List<CurrentWeather> currentWeathers = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT timecode, city, weathertext, temperature, windlocalized, windspeed, pressure, humidity " +
                            "FROM CurrentWeather WHERE city = '" + city + "'"
            );

            while (resultSet.next()) {

                currentWeathers.add(new CurrentWeather(
                        resultSet.getTimestamp("timecode"),
                        resultSet.getString("city"),
                        resultSet.getString("weathertext"),
                        resultSet.getDouble("temperature"),
                        resultSet.getString("windlocalized"),
                        resultSet.getDouble("windspeed"),
                        resultSet.getDouble("pressure"),
                        resultSet.getDouble("humidity")
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return currentWeathers;
    }

    public List<CurrentWeather> getCurrentWeatherAll() throws SQLException{
        List<CurrentWeather> currentWeathers = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT timecode, city, weathertext, temperature, windlocalized, windspeed, pressure, humidity " +
                            "FROM CurrentWeather"
            );

            while (resultSet.next()) {

                currentWeathers.add(new CurrentWeather(
                        resultSet.getTimestamp("timecode"),
                        resultSet.getString("city"),
                        resultSet.getString("weathertext"),
                        resultSet.getDouble("temperature"),
                        resultSet.getString("windlocalized"),
                        resultSet.getDouble("windspeed"),
                        resultSet.getDouble("pressure"),
                        resultSet.getDouble("humidity")
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return currentWeathers;
    }

    public List<Forecast5dayWeather> getForecastWeatherCity(String city) throws SQLException {
       List<Forecast5dayWeather> forecast5dayWeathers = new ArrayList<>();
       try (Statement statement = this.connection.createStatement()) {
           ResultSet resultSet = statement.executeQuery(
                   "SELECT timecode, city, date1, day1, night1, tmin1, tmax1, " +
                           "date2, day2, night2, tmin2, tmax2, " +
                           "date3, day3, night3, tmin3, tmax3, " +
                           "date4, day4, night4, tmin4, tmax4, " +
                           "date5, day5, night5, tmin5, tmax5 " +
                           "FROM Forecast5dayWeather WHERE city = '" + city + "'"
           );

           while (resultSet.next()) {

               DayWeather[] dayWeathers = new DayWeather[5];
               for(int i=0; i<5; i++) {
                   dayWeathers[i] = new DayWeather(
                           resultSet.getString("date" + (i+1)),
                           resultSet.getString("day" + (i+1)),
                           resultSet.getString("night" + (i+1)),
                           resultSet.getDouble("tmin" + (i+1)),
                           resultSet.getDouble("tmax" + (i+1))
                   );
               }
               forecast5dayWeathers.add(new Forecast5dayWeather(
                       resultSet.getTimestamp("timecode"),
                       resultSet.getString("city"),
                       dayWeathers
               ));
           }
       }catch (SQLException e) {
           e.printStackTrace();
       }
       return forecast5dayWeathers;
    }

    public List<Forecast5dayWeather>  getForecastWeatherAll() throws SQLException{
        List<Forecast5dayWeather> forecast5dayWeathers = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT timecode, city, date1, day1, night1, tmin1, tmax1, " +
                            "date2, day2, night2, tmin2, tmax2, " +
                            "date3, day3, night3, tmin3, tmax3, " +
                            "date4, day4, night4, tmin4, tmax4, " +
                            "date5, day5, night5, tmin5, tmax5 " +
                            "FROM Forecast5dayWeather"
            );

            while (resultSet.next()) {

                DayWeather[] dayWeathers = new DayWeather[5];
                for(int i=0; i<5; i++) {
                    dayWeathers[i] = new DayWeather(
                            resultSet.getString("date" + (i+1)),
                            resultSet.getString("day" + (i+1)),
                            resultSet.getString("night" + (i+1)),
                            resultSet.getDouble("tmin" + (i+1)),
                            resultSet.getDouble("tmax" + (i+1))
                    );
                }

                forecast5dayWeathers.add(new Forecast5dayWeather(
                        resultSet.getTimestamp("timecode"),
                        resultSet.getString("city"),
                        dayWeathers
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return forecast5dayWeathers;
    }
}
