package Lesson3;

import java.util.Scanner;

//Игра "Угадайка"
public class Lesson3_1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int computerNumber;
        int userNumber;
        int tryCount;
        boolean onceMore;

        do {
            computerNumber = (int) (Math.random() * 10);
            System.out.println("Давай поиграем! Я загадал число от 0 до 9.\nТвоя задача - угадать его с 3 попыток");
            tryCount = 3;
            for (int i = 0; i < tryCount; i++) {
                //для 3 попыток сделал switch, чтобы лучше читалось в консоли. Для большего количества
                //попыток, конечно следует использовать что-то более универсальное, типа printf(...).
                while (true) {
                    switch (i) {
                        case 0:
                            System.out.print("\nПервая попытка. Твое число: ");
                            break;
                        case 1:
                            System.out.print("\nВторая попытка. Твое число: ");
                            break;
                        default:
                            System.out.print("\nПоследняя попытка. Твое число: ");
                            break;
                    }
                    if (in.hasNextInt()) {
                        userNumber = in.nextInt();

                        if (userNumber >= 0 && userNumber <= 9) {
                            break;
                        }
                        System.out.print("\nТвое число находится за пределами диапазона 0-9. Попробуй еще раз.");
                        in.nextLine(); //очищаем буфер ввода
                    } else {
                        in.nextLine(); //очищаем буфер ввода, если было введено не число
                    }
                }
                if (userNumber == computerNumber) {
                    System.out.println("\n\nПоздравляю, ты угадал!");
                    break;
                }

                if (i == 2) {
                    System.out.println("\nЗакончились попытки. К моему великому сожалению, ты проиграл (((");
                    System.out.printf("\nБыло загадано число %d.", computerNumber);
                    break;
                }

                if (userNumber > computerNumber) {
                    System.out.println("\nНе угадал, мое число меньше.");
                } else {
                    System.out.println("\nНе угадал, мое число больше.");
                }
            }
            System.out.println("\nСыграем ещё разок? \"1\" - да, все остальное - нет");
            onceMore = false;
            if (in.hasNextInt()) {
                userNumber = in.nextInt();

                if (userNumber == 1) {
                    onceMore = true;
                }
            }
        } while (onceMore);

        System.out.println("\nСпасибо за игру, приходи еще!");
    }
}