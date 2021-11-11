package ru.gb.home_works.lesson_5;

public class HomeWork {
    public static void main(String[] args) {
        CoWorker[] coWorkers= new CoWorker[5];

        coWorkers[0]= new CoWorker("Дмитрий Гущин", 42,"Инженер", "dmitr@mail.ru", "123-45-67", 70000);
        coWorkers[1]= new CoWorker("Светлана Перьева", 23,"Менеджер", "Sveta_konfeta@mail.ru", "543-32-10", 150000);
        coWorkers[2]= new CoWorker("Краб Губкин", 2,"Водолаз", "bubble@ocean.dno", "03", 2);
        coWorkers[3]= new CoWorker("Имран Зеленский", 56,"Царь", "head@company.com", "8-800", Long.MAX_VALUE);
        coWorkers[4]= new CoWorker("Тамара Летова", 31,"Дипломат", "letova@mail.ru", "0880", 120000);

        for (CoWorker coWorker : coWorkers) {
            if (coWorker.getAge()>=40)
            System.out.println(coWorker);
        }
    }
}
