package Lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//Игра "Угадай слово" ("Поле чудес")
public class Lesson3_2 {

    public static void main(String[] args) {

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
                "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
                "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        char[] board = new char[15];
        Scanner in = new Scanner(System.in);
        String userAnswer;
        Random rand = new Random();
        int computerIndex;
        int minLength;
        int lettersOpened;
        boolean onceMore = true;

        System.out.println("Давай поиграем в слова!");

        do {

            lettersOpened = 0;
            computerIndex = rand.nextInt(words.length);
            System.out.println("\nЯ загадал название фрукта (овоща) на английском. Попробуй угадать!");
            //Инициализируем табло знаками '.'
            Arrays.fill(board, '.');

            do {
                System.out.print("\nПожалуйста, введи слово (пустая строка - закончить игру): ");
                userAnswer = in.nextLine();
                if (userAnswer.equals("")) {
                    onceMore = false;
                    System.out.printf("\nКонец игры. Было загадано слово \"%s\".\n", words[computerIndex]);
                    break;
                }

                if (userAnswer.equals(words[computerIndex])) {
                    System.out.println("\nПоздравляю! Ты угадал!");
                    break;
                }

                minLength = Math.min(userAnswer.length(), words[computerIndex].length());

                for (int i = 0; i < minLength; i++) {
                    if (userAnswer.charAt(i) == words[computerIndex].charAt(i)) {
                        if (board[i] == '.') {
                            lettersOpened++;
                            board[i] = userAnswer.charAt(i);
                        }
                    }
                }

                printBoard(board, lettersOpened);

                //такой вариант тоже теоретически возможен - слово целиком не было отгадано,
                //но все буквы открылись
                if (lettersOpened == words[computerIndex].length()) {
                    System.out.println("\nПоздравляю! Ты открыл все буквы в загаданном слове!");
                    break;
                }
            } while (true);

            if (onceMore) {
                System.out.println("\nСыграем ещё разок? \"Y\" или \"у\" - да, все остальное - нет");
                userAnswer = in.nextLine();
                if (!userAnswer.toLowerCase().equals("y")) {
                    onceMore = false;
                }
            }
        } while (onceMore);

        System.out.println("Спасибо за игру, приходи еще!");
    }

    private static void printBoard(char[] board, int ltrCount) {
        System.out.print("\nВнимание на табло:\n");

        for (char c : board) {
            System.out.print(c);
        }
        System.out.printf("\nТы уже отгадал %d букв!", ltrCount);

    }

}