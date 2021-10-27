package ru.gb.home_works.lesson_3;

public class HomeWorkLesson_3 {
    public static void main(String[] args) {

//        Написать метод, в который передается не пустой одномерный целочисленный массив,
//        метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
//        int[] array=new int[]{1,2,3,4,2,0};
        int[] array = new int[]{1, 2, 4, 3, 2, 1,1};
        System.out.println(leftAndRightSum(array));



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
