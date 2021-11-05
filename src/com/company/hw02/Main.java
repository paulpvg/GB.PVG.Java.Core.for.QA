package com.company.hw02;

public class Main {

    public static void main(String[] args) {

        String[][] validArray = {{"11", "12", "13", "14"},{"21","22","23","24"},{"31","32","33","34"},{"41","42","43","44"}};
        String[][] errorSizeArray = {{"11", "12", "13", "14"},{"21","22","23","24"},{"31","32","33","34"}};
        String[][] errorDataArray = {{"11", "twelve", "13", "14"},{"21","22","23","24"},{"31","32","33","34"},{"41","42","43","44"}};

        System.out.println("Обработаем три матрицы размером 4 х 4 следующим образом: ");
        System.out.println("преобразуем по возможности строковые элементы в целые числа и, в случае успешности, выведем их сумму");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("1. Обрабортка валидной матрицы:");
        try {processing(validArray);}
        catch(MyArraySizeException e){System.out.println(e.getMessage());}
        catch(MyArrayDataException e){System.out.println(e.getMessage());}
        System.out.println("");

        System.out.println("2. Обработка матрицы с нурушенной размерностью");
        try {processing(errorSizeArray);}
        catch(MyArraySizeException e){System.out.println(e.getMessage());}
        catch(MyArrayDataException e){System.out.println(e.getMessage());}
        System.out.println("");

        System.out.println("3. Обработка матрицы с невалидными элементами");
        try {processing(errorDataArray);}
        catch(MyArraySizeException e){System.out.println(e.getMessage());}
        catch(MyArrayDataException e){System.out.println(e.getMessage());}
        System.out.println("");
    }


    public static void processing(String[][] array) throws MyArrayDataException, MyArraySizeException{

        int row = 0;
        int column = 0;
        int sum = 0;
        int number = 0;

        if (array.length != 4) {throw new MyArraySizeException();} // проверка на количество строк
        else{
            for (int i = 0; i < 4; i++){
                if (array[i].length != 4){throw new MyArraySizeException();} // проверка на количество столбцов в каждой строке
                else{
                    row = i;
                    for (int j =0; j < 4; j++) {
                        column = j;
                        try {
                            number = Integer.parseInt(array[i][j]);
                            sum += number;
                        } catch (IllegalArgumentException e) {
                            String message = "строка - " + (row + 1) + ", столбец - " + (column + 1); //приведем человеческие координаты в матрице
                            throw new MyArrayDataException(message);
                        }
                    }
                }
            }
            System.out.println("Сумма всех преобразованных элементов - " + sum);
        }
    }
}
