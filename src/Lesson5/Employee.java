package Lesson5;

public class Employee {
    private String name;
    private String post;
    private String email;
    private String phoneNumber;
    private int salary; //зарплата (можно и float, чтобы до копейки, но для простоты - int)
    private int age;

    public Employee(String name, String post, int age, int salary, String phoneNumber, String email) {
        this.name = name;
        this.post = post;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void info() {
        System.out.println(this);  //решил сделать через toString() (можно было и по-другому)
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {  //может в таком виде и не стоит делать toString(), я просто попробовал )
        return "Данные о сотруднике: \n" +
                "\t Имя (Ф.И.О.)   : " + name + '\n' +
                "\t Возраст (лет)  : " + age + '\n' +
                "\t Должность      : " + post  + '\n' +
                "\t Зарплата (руб.): " + salary  + '\n' +
                "\t Номер телефона : " + phoneNumber + '\n' +
                "\t Email          : " + email;
    }
}
