package com.company.hw05;

import java.io.*;
import java.util.ArrayList;

public class AppData {

    private String[] header;
    private Integer[][] data;

    public AppData(String[] header, Integer[][] data) {
        this.header = header;
        this.data = data;
    }

    public String[] getHeader() {
        return header;
    }

    public Integer[][] getData() {
        return data;
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(rowToString(header));
            for (Integer[] row : data) {
                writer.write(rowToString(row));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile (String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            header = bufferedReader.readLine().split(";");

            ArrayList<Integer[]> dataList = new ArrayList<>();

            String tmpStr = "";
            while ((tmpStr = bufferedReader.readLine()) != null) {
                dataList.add(stringToRow(tmpStr));
            }

            data = dataList.toArray(new Integer[][]{{}});

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printFile (String fileName){

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) System.out.println(line);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> String rowToString(T[] row) {
        String out = "";
        for (int i = 0; i < row.length; i++) {
            out += row[i].toString();
            if (i != row.length - 1) {
                out += ";";
            }
        }
        out += "\n";
        return out;
    }

    private Integer[] stringToRow(String str) {
        String[] strings = str.split(";");
        Integer[] out = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            out[i] = Integer.parseInt(strings[i]);
        }
        return out;
    }

}