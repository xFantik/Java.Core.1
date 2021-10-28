package ru.gb.home_works.lesson_3;

public class HomeWorkLesson_3 {
    public static void main(String[] args) {
//1         Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;



//2         Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;




//3        Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;



//4        Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно). Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];


//5        Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;

//6        Задать одномерный массив и найти в нем минимальный и максимальный элементы ;


//7        Написать метод, в который передается не пустой одномерный целочисленный массив,
//        метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
        int[] array=new int[]{1,2,3,4,2,0};


        System.out.println(leftAndRightSum(new int[]{-10,10,0,0,0,0,0}));


//8        Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
//          при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично.
//          Для усложнения задачи нельзя пользоваться вспомогательными массивами.
//          Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
//          При каком n в какую сторону сдвиг можете выбирать сами.


    }

    private static boolean leftAndRightSum(int[] array) {
        int startSumm = 0;
        int endSumm = 0;
        int i = 0, j = array.length - 1;
        while (i <= j) {
            if (startSumm <= endSumm) {
                startSumm += array[i];
                i++;
            } else {
                endSumm += array[j];
                j--;
            }
        }
        if (startSumm == endSumm) {
            leftAndRightSumPrinter(array, i);
            return true;
        } else return false;
    }

    public static void leftAndRightSumPrinter(int[] array, int endIndex) {
        if(array.length==endIndex){
            System.out.println("Сумма всех элементов массива равна 0");
            return;
        }
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (int i = 0; i < array.length; i++) {
            if (i < endIndex-1) {
                result.append(array[i]);
                result.append(" + ");
            } else if (i == endIndex-1) {
                result.append(array[i]);
                result.append(" = ");
            } else if (i < array.length - 1) {
                result.append(array[i]);
                result.append(" + ");
            } else {
                result.append(array[i]);
                result.append("}");
            }
        }
        System.out.println(result);
    }
}
