package ru.gb.home_works.lesson_2;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HomeWorkLesson_2 {
    public static void main(String[] args) {
//                      Написать метод, принимающий на вход два целых числа и проверяющий,
//                      что их сумма лежит в пределах от 10 до 20 (включительно),
//                       если да – вернуть true, в противном случае – false.
        System.out.println(isNeededSum(13, 8));

//                       Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
//                      положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
        printIsPositive(8);

//                      Написать метод, которому в качестве параметра передается целое число.
//                      Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
        System.out.println(isNegative(8));

//                      Написать метод, которому в качестве аргументов передается строка и число,
//                      метод должен отпечатать в консоль указанную строку, указанное количество раз;
        printString("напечатай меня \"х\" раз", 6);

//                       * Написать метод, который определяет, является ли год високосным, и возвращает boolean
//                       (високосный - true, не високосный - false). Каждый 4-й год является високосным,
//                       кроме каждого 100-го, при этом каждый 400-й – високосный.
        System.out.println(isLeapYear(5));

//                          дополнительное задание
//                           Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
//                           При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
//                           сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь.
//                           Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
//                           apple – загаданное apricot - ответ игрока ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
//                           Для сравнения двух слов посимвольно можно пользоваться: String str = "apple"; char a = str.charAt(0); - метод, вернет char,
//                           который стоит в слове str на первой позиции Играем до тех пор, пока игрок не отгадает слово. Используем только маленькие буквы.
        game();


    }


    //1
    public static boolean isNeededSum(int a, int b) {
        return (a + b >= 10 && a + b <= 20);
    }

    //2
    public static void printIsPositive(int a) {
        System.out.println(a >= 0 ? "Число положительное" : "Число отрицательное");
    }

    //3
    public static boolean isNegative(int a) {
        return (a < 0);
    }

    //4
    public static void printString(String text, int copy) {
        for (int i = 0; i < copy; i++) {
            System.out.println(text);
        }
    }

    //5
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }


    //6
    public static void game() {
        String[] words = new String[]{"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
                "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String answer = words[(int) (Math.random() * (words.length))];
//        String maskAnswer = getMaskAnswer(answer);
//        System.out.println(maskAnswer);
        System.out.println("Угадайте слово: " + answer);
        Scanner scanner = new Scanner(System.in);
        int attemptCounter = 0;
        while (true) {
            System.out.printf("\nПопытка №%d: ", attemptCounter + 1);
            String input = scanner.next();
            attemptCounter++;

            if (input.equals(answer)) {
                System.out.printf("Ура! Слово угадано за %d %s", attemptCounter, wordHelper(attemptCounter));
                break;
            } else {
                StringBuilder promt = new StringBuilder();
                for (int i = 0; i < 15; i++) {
                    if (i<input.length() && i<answer.length()){
                        if (input.charAt(i) == answer.charAt(i)) {
                            promt.append(input.charAt(i));
                        } else promt.append('#');
                    }
                    else promt.append('#');
                }
                 System.out.println("Нет. Угаданные буквы: " + promt.toString());
            }
        }
    }

    public static String wordHelper(int count) {
        if ((count >= 10 && count <= 20) || count % 10 == 0) {
            return "попыток";
        } else if (count % 10 == 1) {
            return "попытку";
        } else if (count % 10 <= 4) {
            return "попытки";
        } else return "попыток";
    }

}
