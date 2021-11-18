package ru.gb.home_works.lesson_7_oop3;

public class Plate {
    private int food;
    private int plateSize;

    public Plate(int plateSize,int food) {
        this.food = food;
        this.plateSize = plateSize;
    }

    public void addFood(int food) {
        if (food+this.food<=plateSize){
            this.food += food;
            System.out.printf("В тарелку добавили %d еды\n", food);
        } else
            System.out.println("Столько еды в миску не влезет!");
    }

    public boolean getFood(int foodToEat) {
        if (foodToEat <= food) {
            this.food -= foodToEat;
            return true;
        } else
            return false;
    }
}
