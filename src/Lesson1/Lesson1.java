package Lesson1;

public class Lesson1 {
    public static void main(String[] args) {
        //Объявляем переменные всех пройденных на уроке типов
        byte byte_var1 = 0x2f; // = 47
        byte byte_var2 = 95;
        short short_var = 1523;
        int int_var = 46575;
        long long_var = 35467892345L;
        float float_var = 45.98f;
        double double_var1 = 567.78E21; // = 5.6778E23
        double double_var2 = 0x4F14p12; // = 20244 * 2^12 == 20244 * 4096 == 82919424 == 8.2919424 * 10^7 == 8.2919424E7
        char char_var1 = 'x';
        char char_var2 = '\u0057'; // = 'W'
        boolean bool_var = false;
        String my_string = "Некоторый текст";
        //Для проверки выводим в консоль
        System.out.println("byte_var1 = " + byte_var1);
        System.out.println("byte_var2 = " + byte_var2);
        System.out.println("short_var = " + short_var);
        System.out.println("int_var = " + int_var);
        System.out.println("long_var = " + long_var);
        System.out.println("float_var = " + float_var);
        System.out.println("double_var1 = " + double_var1);
        System.out.println("double_var2 = " + double_var2);
        System.out.println("char_var1 = " + char_var1);
        System.out.println("char_var2 = " + char_var2);
        System.out.println("bool_var = " + bool_var);
        System.out.println("my_string = " + my_string);

        //проверяем работу метода calculate()
        System.out.println(calculate(int_var, long_var, float_var, double_var2));

        //проверяем работу метода check_condition()
        System.out.println(check_condition(12, 5)); // true
        System.out.println(check_condition(2, 7));  // false
        System.out.println(check_condition(13, 9)); // false

        //проверяем метод print_neg_or_pos()
        print_neg_or_pos(34);
        print_neg_or_pos(-15);
        print_neg_or_pos(0);

        //проверяем метод is_negative()
        System.out.println(is_negative(15));  // false
        System.out.println(is_negative(-45)); // true
        System.out.println(is_negative(0));   // false

        //Всем привет))
        print_greeting("Вася");
        print_greeting("Петя");
        print_greeting("Изаура и Женуария");

        //Проверка на високосность
        System.out.println("");
        check_leap_year(-654);  // слишком рано, не было такого понятия
        check_leap_year(-40);   // не високосный
        check_leap_year(-33);   // високосный
        check_leap_year(-15);   // не високосный
        check_leap_year(-1);    // високосный
        check_leap_year(0);     // не было такого года
        check_leap_year(3);     // не високосный
        check_leap_year(4);     // високосный
        check_leap_year(1224);  // високосный
        check_leap_year(1453);  // не високосный
        check_leap_year(1584);  // високосный
        check_leap_year(1600);  // високосный
        check_leap_year(1800);  // не високосный
        check_leap_year(2000);  // високосный
        check_leap_year(24356);  // високосный

    }

    public static double calculate(double a, double b, double c, double d) {
        return (a * (b + (c / d)));
    }

    //написал для int, аналогично можно и для long, float и double
    public static boolean check_condition(int a, int b) {
        return (((a + b) >= 10) && ((a + b) <= 20));
    }

    //тело if без {} ибо в одну строку))
    public static void print_neg_or_pos(int value) {
        if (value < 0) System.out.println("Число " + value + " отрицательное");
        else System.out.println("Число " + value + " положительное");
    }

    //Проверяет, отрицательное ли число или нет
    public static boolean is_negative(int a) {
        return a < 0;
    }

    //Выводит в консоль приветствие
    public static void print_greeting(String name) {
        System.out.println("Привет, " + name + "!");
    }

    /*Выводит в консоль - високосный год (параметр year) или нет.
     Положительные значения year соответствуют годам нашей эры, отрицательные - годам до нашей эры.
     Понятие "високосный год" было введено Юлием Цезарем с 1 января 45 г. до нашей эры.
     Это был первый високосный год. Прошу заметить - нечетное значение, так как 0 года нашей эры не существует.
     Есть 1 год до н.э. и 1 год н.э. Т.е. -1 + 1 = 1 ))) Вот такая арифметика.
     До 1582 года н.э. каждый четвертый год, начиная с 45 г. до н.э. был високосным.
     С 1582 года н.э., когда папа римский Григорий XIII провёл реформу календаря, стали применяться правила,
     описанные в задании.
     Метод check_leap_year() учитывает данные исторические нюансы. */
    public static void check_leap_year(int year) {
        if (year == 0) {
            System.out.println("Вы ошиблись. Нулевого года нет в календаре.");
            return;
        }
        if (year < -45) {
            //В С++ меня бы за такое сложение int+String, наверное, убили бы... А Java позволяет)). Буду привыкать.
            System.out.println((-year) + " год до н.э. не високосный. Первый високосный год - 45 г. до н.э.");
            return;
        }

        if (year < 0) {
            if ((year + 1) % 4 == 0) System.out.println((-year) + " год до н.э. - високосный.");
            else System.out.println((-year) + " год до н.э. - не високосный.");
        } else if (year < 1582) {
            if (year % 4 == 0) System.out.println(year + " год - високосный.");
            else System.out.println(year + " год - не високосный.");
        } else {
            if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
                System.out.println(year + " год - високосный");
            else System.out.println(year + " год - не високосный");
        }
    }

}
