package ru.gb.home_works.lesson_6_oop2;

public class Dog extends Animal {
    private static int quantity = 0;

    public Dog(String name) {
        this.name = name;
        quantity++;
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.printf("Dog %s проплыл(а) расстояние: %d\n", name, distance);
        } else
            System.out.printf("Dog %s сказал(а): Так далеко я не поплыву\n", name);
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.printf("Dog %s пробежал(а) расстояние: %d\n", name, distance);
        } else
            System.out.printf("Dog %s сказал(а): Так далеко я не побегу\n", name);
    }


    public static int getQuantity() {
        return quantity;
    }

}
