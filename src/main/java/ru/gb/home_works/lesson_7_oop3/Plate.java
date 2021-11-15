package ru.gb.home_works.lesson_7_oop3;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void addFood(int food) {
        this.food += food;
        System.out.printf("В тарелку добавили %d еды\n", food);
    }

    public boolean getFood(int foodToEat) {
        if (foodToEat <= food) {
            this.food -= foodToEat;
            return true;
        } else
            return false;
    }
}
