package ru.gb.home_works.lesson_4;

public class HomeWorkLesson_4_Xor0 {
    static Field field;

    public static void main(String[] args) {
        field = new Field(5,4);
        System.out.println(field);
        while (field.doStep()) {                            //сделать ход
            System.out.println(field);
            if (field.isGameOver())
                break;
        }
        switch (field.getCurrentState()) {
            case Field.STANDOFF -> System.out.println("Больше линий здесь не нарисовать! Ничья!");
            case Field.botWin -> System.out.println("Ха! Я победил!");
            case Field.playerWin -> System.out.println("Мои поздравления! Ты выиграл!");
        }
        System.out.println("Спасибо за игру!");
    }
}






