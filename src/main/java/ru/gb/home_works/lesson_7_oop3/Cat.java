package ru.gb.home_works.lesson_7_oop3;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public boolean eat(Plate p) {
        if (satiety) {
            System.out.printf("Котик \"%s\" сытый и кушать не хочет\n", name);
            return true;
        }
        if (p.getFood(appetite)) {
            System.out.printf("Котик \"%s\" съел %d еды\n", name, appetite);
            satiety = true;
            return true;
        } else {
            System.out.printf("Котику \"%s\" не хватило еды в этой тарелке\n", name);
            return false;
        }
    }

    public boolean isSatiety() {
        return satiety;
    }
}
