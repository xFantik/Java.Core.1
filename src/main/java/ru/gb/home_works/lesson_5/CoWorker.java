package ru.gb.home_works.lesson_5;

public class CoWorker {
    private String name;
    private int age;
    private String position;
    private String email;
    private String phone;
    private long salary;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public long getSalary() {
        return salary;
    }

    public CoWorker(String name, int age, String position, String email, String phone, long salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
            info.append("  : ").append(name)
                .append(" :\nВозраст  : ").append(age)
                .append("\nДолжность: ").append(position)
                .append("\nE-mail   : ").append(email)
                .append("\nPhone    : ").append(phone)
                .append("\nЗарплата : ").append(salary).append('\n');


        return info.toString();
    }
}
