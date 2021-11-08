package ru.gb.home_works.lesson_6_oop2;

public class Animal {
    private static int quantity = 0;
    String name;

    public Animal() {
        quantity++;
    }

    public Animal(String name) {
        this();
        this.name = name;
    }

    public void animalInfo() {
        System.out.println("Животное: " + name);
    }

    public void swim(int distance){
        System.out.println("Животное проплыло расстояние: " + distance);
    }
    public void run(int distance){
        System.out.println("Животное пробежало расстояние: " + distance);
    }

    public void jump() {
        System.out.println("Животное подпрыгнуло");
    }

    public static int getQuantity() {
        return quantity;
    }
}
