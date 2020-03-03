package Lesson7;

import java.util.Random;
import java.util.Scanner;

public class Lesson7 {

    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int baseAppetite = 15;
        int newFood = 0;
        int catsCount = rand.nextInt(4) + 3;
        Cat[] cats = new Cat[catsCount];
        System.out.println("У нас дома живут " + catsCount + " котов. Вот их имена и аппетиты:");
        for (int i = 0; i < catsCount; i++) {
            cats[i] = new Cat("Номер " + i, baseAppetite + rand.nextInt(20));
        }
        showInfo(cats);
        Plate plate = new Plate(90, 200);

        //основной цикл, коты начинают есть
        for (; ; ) {
            System.out.println("\n" + plate + "\n");
            System.out.println("Коты начинают есть");
            for (Cat c : cats) {
                c.eat(plate);
            }
            System.out.println("\n" + plate + "\n");
            showInfo(cats);
            System.out.println("Сколько еды добавить в тарелку (0 - закончить)?");
            newFood = sc.nextInt();
            if (newFood == 0) {
                return;
            }
            //визуально очищаем консоль
            for (int i = 0; i < 10; i++) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            }
            if (newFood < 0) {
                newFood = -newFood; //берем модуль)
            }
            //наполняем тарелку
            plate.addFood(newFood);

            //Некоторые сытые коты начинают голодать, голодные остаются голодными
            for (Cat c : cats) {
                if (c.isFullUp()) {
                    if (rand.nextInt(2) == 0) {
                        c.setHungry();
                        System.out.println("Кот " + c.getName() + " внезапно проголодался!");
                    }
                }
            }
        }

    }

    private static void showInfo(Cat[] cats) {
        for (Cat cat : cats) {
            System.out.println("Кот " + cat.getName() + ". Аппетит - " + cat.getAppetite() +
                    (cat.isFullUp() ? ". Сыт и доволен)." : ". ГОЛОДЕН!"));
        }
    }
}
