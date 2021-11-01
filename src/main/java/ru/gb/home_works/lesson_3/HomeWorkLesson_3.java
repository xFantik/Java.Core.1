package ru.gb.home_works.lesson_3;

public class HomeWorkLesson_3 {
    public static void main(String[] args) {


//1         Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int[] array = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i]^1;
        }
        System.out.print("Задание 1: ");
        arrayPrinter(array);

//2         Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
        array=new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i]=i+1;
        }
        System.out.print("\nЗадание 2: ");
        arrayPrinter(array);


//3        Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        array = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < array.length; i++) {
            if (array[i]<6)
                array[i]*=2;
        }
        System.out.print("\nЗадание 3: ");
        arrayPrinter(array);


//4        Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
//          и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно).
//          Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
        int size=10;
        int[][] array2d=new int[size][size];
        for (int i = 0; i < array2d.length; i++) {
            for (int j = 0; j < array2d[i].length; j++) {
                if (i==j || j+i==array2d.length-1) array2d[i][j]=1;
            }
        }
        System.out.print("\nЗадание 4: ");
        arrayPrinter(array2d);


//5        Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
        System.out.print("\nЗадание 5: ");
        arrayPrinter(createArray(10, -5));

        
//6        Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
        System.out.print("\nЗадание 6: ");
        array = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i]>max) max=array[i];
            if (array[i]<min) min=array[i];
        }
        System.out.printf("\nМинимальное значение массива: %d\nМаксимальное значение массива: %d\n", min, max);


//7        Написать метод, в который передается не пустой одномерный целочисленный массив,
//        метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
        System.out.print("\nЗадание 7: ");
        System.out.println(leftAndRightSum(new int[]{-1,-1,-1,-1, 5, 1}));


//8        Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
//          при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются циклично.
//          Для усложнения задачи нельзя пользоваться вспомогательными массивами.
//          Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
//          При каком n в какую сторону сдвиг можете выбирать сами.
        System.out.print("\nЗадание 8: ");
        shiftArrayWithoutSecondArray(new int[]{1, 2, 3,4,5 }, -201);


        int[] testArray=new int[]{1,2,3,4,5,6};
        shiftArrayWithSystemArrayCopy(testArray, -1);


        shiftOptimal(new int[]{1,2,3,4}, 2);







    }


    //задание 5
    private static int[] createArray(int len, int initialValue) {
        if (len<=0) return null;
        int[] arr=new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i]= initialValue;
        }
        return arr;
    }

    //задание 7
    private static boolean leftAndRightSum(int[] array) {
        int leftSum = 0;
        int rightSum = 0;
        int i = 0, j = array.length - 1;
        while (i <= j) {
            if (leftSum <= rightSum) {
                leftSum += array[i];
                i++;
            } else {
                rightSum += array[j];
                j--;
            }
        }
        if (leftSum == rightSum) {
            leftAndRightSumPrinter(array, i);
            return true;
        } else return false;
    }

    //задание 7 вывод
    public static void leftAndRightSumPrinter(int[] array, int endIndex) {
        if (array.length == endIndex) {
            System.out.println("Сумма всех элементов массива равна 0");
            return;
        }
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (int i = 0; i < array.length; i++) {
            if (i < endIndex - 1) {
                result.append(array[i]);
                result.append(" + ");
            } else if (i == endIndex - 1) {
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

    public static void shiftOptimal(int[] arr, int n) {
        if (arr == null || arr.length < 2) return;
        int shift = (arr.length + n % arr.length) % arr.length;
        int count = 0;
        for (int i = 0; count < arr.length; i++) {
            int currentIndex = i;
            int prevElement = arr[i];
            do {
                int nextElement = (currentIndex + shift) % arr.length;
                int temp = arr[nextElement];
                arr[nextElement] = prevElement;
                prevElement = temp;
                currentIndex = nextElement;
                count++;
            } while (i != currentIndex);
        }
        arrayPrinter(arr);
    }
    //задание 8
    private static void shiftArray(int[] array, int shift) {
        int arrayNew[]=new int[array.length];
        while (shift <0) shift += array.length;
        for (int i = 0; i < array.length; i++) {
            arrayNew[Math.abs(i+ shift)% array.length]= array[i];
        }
        arrayPrinter(arrayNew);
    }

    //задание 8*
    private static void shiftArrayWithoutSecondArray(int[] array, int shift) {
        while (shift <0) shift += array.length;
        shift %= array.length;
        for (int i = 0; i < shift; i++) {
            int tmp= array[array.length-1];
            for (int j = array.length-1; j >0; j--) {
                array[j]= array[j-1];
            }
            array[0]=tmp;
        }
        arrayPrinter(array);
    }
////задание 8**
    private static void shiftArrayWithSystemArrayCopy(int[] testArray, int shift) {
        while (shift <0) shift += testArray.length;
        shift %= testArray.length;
        int[] testArray2=new int[testArray.length];
        System.arraycopy(testArray,0, testArray2, shift, testArray.length- shift);
        arrayPrinter(testArray2);
        System.arraycopy(testArray, testArray.length- shift, testArray2, 0, shift);
        arrayPrinter(testArray2);
    }
    public static void arrayPrinter(int[] array) {
        if (array==null) {
            System.out.println("Массив не проинициализирован");
            return;
        }
        StringBuilder result = new StringBuilder();
        result.append("{");
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1)
                result.append(", ");
            else
                result.append("}");
        }
        System.out.println(result);
    }
    public static void arrayPrinter(int[][] array) {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                result.append(array[i][j]+ " ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }

}
