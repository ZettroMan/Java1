package Lesson5;

public class Lesson5 {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иванов Иван Иванович",
                "Руководитель проекта",
                54,
                150000,
                "3322-22-33-22",
                "megaboss@mail.ru");
        employees[1] = new Employee("Фантазеров Антуан Захарович",
                "Креативный директор",
                48,
                120000,
                "1486-45-9-831",
                "creative@mail.ru");
        employees[2] = new Employee("Прогнозов Пересвет Ярославович",
                "Экономист-аналитик",
                35,
                85000,
                "1234-56-78-90",
                "econom@mail.ru");
        employees[3] = new Employee("Безденежных Элеонора Андреевна",
                "Бухгалтер",
                41,
                85000,
                "7777-500-500",
                "denegnet@mail.ru");
        employees[4] = new Employee("Байткодов Никодим Ефграфович",
                "Java-разработчик",
                25,
                60000,
                "1001-00-00-11",
                "mastermind@mail.ru");
        System.out.println("\nCписок сотрудников компании старше 40 лет: \n");
        for (Employee empl: employees) {
            if(empl.getAge() > 40) {
                empl.info();
            }
        }
    }
}
