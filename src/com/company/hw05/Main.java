package com.company.hw05;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 1.");
        System.out.println("Реализовать сохранение данных в csv файл;");
        System.out.println("Структура csv файла:");
        System.out.println("| Строка заголовок с набором столбцов |");
        System.out.println("| Набор строк с целочисленными значениями |");
        System.out.println("| * Разделитель между столбцами - символ точка с запятой (;) ");
        System.out.println("Пример:");
        System.out.println("  Value 1;Value 2;Value 3");
        System.out.println("  100;200;123");
        System.out.println("  300,400,500");
        System.out.println("Для хранения данных использовать класс вида:");
        System.out.println("  public class AppData {");
        System.out.println("      private String[] header;");
        System.out.println("      private int[][] data;");
        System.out.println("      // ...");
        System.out.println("  }");
        System.out.println("Если выполняется save(AppData data), то старые данные в файле полностью перезаписываются.");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        String[] headerArr = {"Value_1","Value_2","Value_3"};
        Integer[][] dataArr = {{111, 122, 133}, {211, 222, 233}, {311, 322, 333}};

        AppData appData = new AppData(headerArr, dataArr);
        appData.saveToFile("src/com/company/hw05/files/file.csv");

        System.out.println("Выведем на консоль сформированный файл:\n");

        appData.printFile("src/com/company/hw05/files/file.csv");

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("Задача 2.");
        System.out.println("Реализовать загрузку данных из csv файла. Файл читается целиком.");
        System.out.println("---------------------------------------------------------------------------------------------------------");


        appData.loadFromFile("src/com/company/hw05/files/file.csv");

        System.out.println("Выведем данные, загруженные из файла:\n");
        System.out.println(Arrays.toString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData()));
        System.out.println("---------------------------------------------------------------------------------------------------------\n");

    }
}
