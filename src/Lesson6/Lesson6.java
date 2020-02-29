package Lesson6;

import java.util.Scanner;

public class Lesson6 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int animalsCount = 7;
        int selectedAnimal;
        int selectedAction;
        int actionValue;
        String tempName;
        Animal[] animals = new Animal[animalsCount];

        animals[0] = new Animal();
        animals[1] = new Animal("Кентавр", "Вася", 500, 150, 200);
        animals[2] = new Cat("Барсик");
        animals[3] = new Cat("Мурзик", 400, 300);
        animals[4] = new Dog();
        animals[4].setName("Барбос");
        animals[5] = new Horse("Росинант");
        animals[6] = new Bird("Хватайка");

        //Итак, у нас есть разные животные. Выведем информацию о них:
        //showInfo(animals);
        System.out.println("\nВсего у нас есть " + Animal.getAnimalsCount() + " разных животных. Из них:");
        System.out.print("котов - " + Cat.getCatsCount() + " шт., ");
        System.out.print("собак - " + Dog.getDogsCount() + " шт., ");
        System.out.print("лошадей - " + Horse.getHorsesCount() + " шт., ");
        System.out.print("птиц - " + Bird.getBirdsCount() + " шт. ))");

        System.out.println("\n\nДавайте поиграем с ними!");
        while (true) {
            selectedAnimal = -1;
            while (selectedAnimal < 0 || selectedAnimal > animalsCount) {
                showInfo(animals);
                System.out.println("Пожалуйста, выберите животное (0 - закончить игру):");
                selectedAnimal = sc.nextInt();
                if (selectedAnimal == 0) return;
            }
            selectedAnimal--;
            animals[selectedAnimal].readyForAction();
            selectedAction = -1;
            while (selectedAction < 0 || selectedAction > 7) {
                System.out.println("Выберите действие:\n" +
                        "1 - Пробежать; 2 - Перепрыгнуть препятствие; 3 - Проплыть; \n" +
                        "4 - Научить бегать; 5 - Научить прыгать; 6 - Научить плавать; \n" +
                        "7 - Дать новое имя; 0 - Накормить животненькое");
                selectedAction = sc.nextInt();
            }
            if (selectedAction == 0) {
                animals[selectedAnimal].feed();
                continue;
            }
            if (selectedAction == 7) {
                System.out.print("\nВведите мое новое имя: ");
                sc.nextLine();
                tempName = sc.nextLine();
                animals[selectedAnimal].setName(tempName);
                continue;
            }
            actionValue = 0;
            while (actionValue <= 0) {
                switch (selectedAction) {
                    case 1:
                    case 4:
                        System.out.println("Сколько метров бежим?");
                        break;
                    case 2:
                    case 5:
                        System.out.println("Какая высота препятствия (см)?");
                        break;
                    case 3:
                    case 6:
                        System.out.println("Сколько метров плывем?");
                }
                actionValue = sc.nextInt();
            }

            switch (selectedAction) {
                case 1:
                    animals[selectedAnimal].run(actionValue);
                    break;
                case 2:
                    animals[selectedAnimal].jump(actionValue);
                    break;
                case 3:
                    animals[selectedAnimal].swim(actionValue);
                    break;
                case 4:
                    animals[selectedAnimal].learnToRun(actionValue);
                    break;
                case 5:
                    animals[selectedAnimal].learnToJump(actionValue);
                    break;
                case 6:
                    animals[selectedAnimal].learnToSwim(actionValue);
                    break;
            }

        }
    }

    private static void showInfo(Animal[] animals) {
        System.out.println("Наши животные и их основные ТТХ:");
        for (int i = 0; i < animals.length; i++) {
            System.out.print((i + 1) + ".\t");
            animals[i].info();
        }

    }
}