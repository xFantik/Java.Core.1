package ru.gb.home_works.lesson_6_oop2;

public class HomeWorkLesson_6_OOP {
//    Домашнее задание
//    Создать классы Собака и Кот с наследованием от класса Животное.
//    Все животные могут бежать и плыть.
//    В качестве параметра каждому методу передается длина препятствия.
//    Результатом выполнения действия будет печать в консоль.
//    (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
//    У каждого животного есть ограничения на действия
//    (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
//            * Добавить подсчет созданных котов, собак и животных.


    public static void main(String[] args) {
        new Animal("Змея").run(1000);
        new Cat("Барсик").run(100);
        new Cat("Мурзик").run(1000);
        new Cat("Мурзик").swim(1000);
        new Dog("Бобик").swim(7);
        new Dog("Бобик").jump();
        new Animal("ВторойБобик").run(1000);
        System.out.println("Создано животных: " + Animal.getQuantity());
        System.out.println("Из них кошек: " + Cat.getQuantity());
        System.out.println("Из них собак: " + Dog.getQuantity());



    }
}
