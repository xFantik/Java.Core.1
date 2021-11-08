package ru.gb.home_works.lesson_6_oop2;

public class Cat extends Animal {
    private static int quantity = 0;
    public Cat(String name){
        this.name=name;
        quantity++;
    }
    @Override
    public void swim(int distance) {
        System.out.println("К сожалению, кошки не умеют плавать");
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.printf("Cat %s пробежал(а) расстояние: %d\n", name, distance);
        } else
            System.out.printf("Cat %s сказал(а): Так далеко я не побегу\n", name);
    }


    public static int getQuantity() {
        return quantity;
    }

}
