package ru.gb.home_works.lesson_4;

public class HomeWorkLesson_4_Xor0 {
    static Field field;

    public static void main(String[] args) {
        field = new Field(5,4);
        System.out.println(field);
        while (field.doStep()) {
            System.out.println(field);
            if (field.isWin())
                break;
        }
        switch (field.getCurrentState()) {
            case Field.STANDOFF:
                System.out.println("Клетки закончились. Ничья!");
                break;
            case Field.botWin:
                System.out.println("Ха! Я победил!");
                break;
            case Field.playerWin:
                System.out.println("Мои поздравления! Ты выиграл!");
        }
    }
}






