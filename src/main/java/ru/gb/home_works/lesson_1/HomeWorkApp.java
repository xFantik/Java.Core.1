package ru.gb.home_works.lesson_1;
//Домашнее задание первого урока
public class HomeWorkApp {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();


        quadratic(-2,6,9);                      //Доп задание из телеги (Квадратное уравнение)

        {                                                   //Доп задание из телеги (шифр Цезаря)
            System.out.println("\n\n\n\n");
            int key = -21;
            String s_crypt = (crypt("Зашифруйте мне эту строку =))", key));
            System.out.println("Зашифрованная строка: " + s_crypt);
            String s_decrypt = (decrypt(s_crypt, key));
            System.out.println("Расшифрованная строка: " + s_decrypt);
        }

    }

    public static String crypt(String s, int key){
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char symbol=s.charAt(i);
            symbol+=key;
            result+= symbol;
        }
        return result;
    }
    public static String decrypt(String s, int key){
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char symbol=s.charAt(i);
            symbol-=key;
            result+= symbol;
        }
        return result;
    }


    public static void quadratic(int a, int b, int c) {
        System.out.println("\n\n\n");
        if (a==0 && b!=0){
            System.out.println("Это линейное уравнение: "+b+"x+"+c+"=0");
            double  x = (-(double)c) /b ;
            System.out.println("Ответ: x="+x);
        }
        else if (a==0 && b==0){             //Вот тут пришлось возврат метода делать void (или exception, которые еще надо изучать)
            if (c==0){
                System.out.println("Упрощаем уравнение: 0=0. Эй, куда делись иксы?");
            }
            else{
                System.out.println("Если а = 0 и b = 0, Вы утверждаете, что "+ c + " = 0. Мне нравится Ваш подход =))");
            }
        }

        else {
            System.out.println("Пытаемся решить уравнение: " + a + "x²+" + b + "x+" + c + "=0");
            System.out.println("Считаем дискрименант по формуле D = b² - 4ac");
            System.out.println("Подставляем значения: D=" + b + "²-4*" + a + "*" + c);
            double d = 1.0 * b * b - (4.0 * a * c);
            System.out.println("Дискрименант D = " + d);
            if (d<0){
                System.out.println("D < 0, значит уравнение не имеет решения");
            }
            else if (d==0){
                System.out.println("Ищем корень уравнения по формуле: x1 = -b / 2a");
                System.out.println("Уравнение имеет один корень:\nx = "+ (-b / 2.0 * a));
            }
            else{
                System.out.println("Ищем корни уравнения по формуле: x1 = (-b + √D) / 2a");
                System.out.println("Ищем корни уравнения по формуле: x2 = (-b + √D) / 2a");

                double x1 = (-b + Math.sqrt(d)) / 2.0 * a;
                double x2 = (-b - Math.sqrt(d)) / 2.0 * a;

                System.out.println("Ответ:\nx1 = "+x1+"\nx2 = " + x2);
            }

        }




    }

    public static void compareNumbers(){
        int a = 10;
        int b = 20;
        if (a >= b){
            System.out.println("a >= b");
        }
        else {
            System.out.println("a < b");
        }
    }

    public static void printColor() {
        int value = 0;
        if (value<=0){
            System.out.println("Красный");
        }
        else if (value>0&& value<=100){
            System.out.println("Желтый");
        }
        else {
            System.out.println("Зелёный");
        }
    }

    public static void checkSumSign() {
        int a = 10;
        int b = 20;
        if (a+b>=0){
            System.out.println("Сумма положительная");
        }
        else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }


}
