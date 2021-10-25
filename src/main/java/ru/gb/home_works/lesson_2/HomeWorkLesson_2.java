package ru.gb.home_works.lesson_2;

public class HomeWorkLesson_2 {
    public static void main(String[] args) {
//                      Написать метод, принимающий на вход два целых числа и проверяющий,
//                      что их сумма лежит в пределах от 10 до 20 (включительно),
//                       если да – вернуть true, в противном случае – false.
        System.out.println(isNeededSum(13,8));

//                       Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
//                      положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
        printIsPositive(8);

//                      Написать метод, которому в качестве параметра передается целое число.
//                      Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
        System.out.println(isNegative(8));

//                      Написать метод, которому в качестве аргументов передается строка и число,
//                      метод должен отпечатать в консоль указанную строку, указанное количество раз;
        printString("напечатай меня \"х\" раз" ,6);

//                       * Написать метод, который определяет, является ли год високосным, и возвращает boolean
//                       (високосный - true, не високосный - false). Каждый 4-й год является високосным,
//                       кроме каждого 100-го, при этом каждый 400-й – високосный.
        System.out.println(isLeapYear(5));




    }
    //1
    public static boolean isNeededSum (int a, int b){
        return (a+b >=10 && a+b<=20);
    }

    //2
    public static void printIsPositive(int a){
        System.out.println(a>=0?"Число положительное":"Число отрицательное");
    }

    //3
    public static boolean isNegative(int a){
        return (a<0);
    }

    //4
    public static void printString(String text, int copy){
        for (int i = 0; i < copy; i++) {
            System.out.println(text);
        }
    }

    //5
    public static boolean isLeapYear(int year){
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }
}
