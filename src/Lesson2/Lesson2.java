package Lesson2;

public class Lesson2 {
    public static void main(String[] args) {

        //Задание 1.
        int[] simple_array = {0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1};
        System.out.println("Массив из 0 и 1:");
        printArray(simple_array, 25);
        for (int i = 0; i < simple_array.length; i++)
            simple_array[i] = 1 - simple_array[i];
        System.out.println("\nПоменяли 0 и 1:");
        printArray(simple_array, 25);

        //Задание 2.
        int[] empty_array = new int[8];
        for (int i = 0; i < empty_array.length; i++)
            empty_array[i] = i * 3;
        System.out.println("\n\nМассив из задания 2:");
        printArray(empty_array, 20);

        //Задание 3.
        int[] fixed_array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("\n\nМассив из задания 3:");
        printArray(fixed_array, 20);
        for (int i = 0; i < fixed_array.length; i++)
            if (fixed_array[i] < 6) fixed_array[i] *= 2;
        System.out.println("\nПосле обработки:");
        printArray(fixed_array, 20);

        //Задание 4.
        int size = 20;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - i - 1] = 1;
        }

        System.out.println("\n\nМассив с 1 по диагоналям:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }


        //Задание 5. Работа с массивом. Поиск наибольшего и наименьшего значения
        //создаем массив, заполненный случайными значениями
        int[] array = generateRandomArray(97, 99);
        //выводим массив в консоль, по 25 чисел в каждой строке
        System.out.println("\n\nЗадание 5. Исходный массив:");
        printArray(array, 20);
        System.out.println();
        System.out.println("Максимальный элемент массива равен " + getMaxValue(array));
        System.out.println("Минимальный элемент массива равен " + getMinValue(array));
        System.out.println();

        //Задание 6. Работа с массивом. Проверка на сбалансированность
        //Используем массив из задания 5
        checkBalance(array);
        System.out.println();

        //Задание 7. Работа с массивом. Реализация циклического сдвига на N позиций
        //Используем массив из задания 5
        shiftArray(array, 1);
        printArray(array, 20);
        System.out.println("\n---------------------------------------------------------------------------------------------");
        shiftArray(array, 5);
        printArray(array, 20);

        //Задание 7-1. Реализация циклического сдвига на N позиций. Быстрый алгоритм
        System.out.println("\n---------------------------------------------------------------------------------------------");
        //сдвигаем влево на 6 позиций, массив должен вернуться к исходному состоянию
        shiftArrayQuick(array, -6);
        printArray(array, 20);

    }

    //Возвращает целочисленный массив длиной array_len, заполненный случайными значениями от -max_value до +max_value
    public static int[] generateRandomArray(int array_len, int max_value) {
        //объявляем целочисленный массив
        int[] array = new int[array_len];
        //заполняем массив случайными числами от 0 до max_value
        for (int i = 0; i < array_len; i++)
            array[i] = (int) (Math.random() * (max_value * 2 + 1) - max_value);
        return array;
    }

    //выводит массив в консоль по width_limit чисел в каждой строке
    public static void printArray(int[] array, int width_limit) {
        if (width_limit < 1) {
            System.out.println("Неверно указана ширина строки");
            return;
        }
        int width_counter = width_limit;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t\t");
            width_counter--;
            if (width_counter == 0) {
                System.out.println(); //перевод строки
                width_counter = width_limit;
            }
        }
    }

    public static int getMaxValue(int[] array) {
        int ret_val = array[0];
        if (array.length == 1) return ret_val;
        for (int i = 1; i < array.length; i++)
            if (ret_val < array[i]) ret_val = array[i];
        return ret_val;
    }

    public static int getMinValue(int[] array) {
        int ret_val = array[0];
        if (array.length == 1) return ret_val;
        for (int i = 1; i < array.length; i++)
            if (ret_val > array[i]) ret_val = array[i];
        return ret_val;
    }

    public static void checkBalance(int[] array) {
        if (isBalanced(array))
            System.out.println("Массив имеет точку баланса (как минимум одну)");
        else System.out.println("Массив не имеет точек баланса");
    }

    public static boolean isBalanced(int[] array) {
        int sum = sumArray(array);
        if (sum % 2 != 0) return false;
        sum /= 2;
        for (int i = 0; i < array.length; i++) {
            sum -= array[i];
            if (sum == 0) return true;
        }
        return false;
    }

    public static void shiftArray(int[] array, int shift_step) {
        //если shift_step отрицательный, то и после деления по модулю он тоже будет отрицательный
        //согласно спецификации Java (т.е. тот же знак, что и у делимого)
        int arr_len = array.length;
        if (arr_len < 2) return;
        //приводим shift_step к значениям от 0 до array.length - 1
        shift_step %= arr_len;
        if (shift_step == 0) return; //сдвигать не надо
        if (shift_step < 0) shift_step += arr_len;

        //определяем, в какую сторону оптимальнее делать сдвиг и последовательно сдвигаем
        if (shift_step <= arr_len / 2) { //если вправо будет меньше операций сдвига, чем влево
            System.out.println("Сдвигаем вправо на " + shift_step + " позиций");
            for (int i = 0; i < shift_step; i++) shiftToRight(array);
        } else {
            System.out.println("Сдвигаем влево на " + (arr_len - shift_step) + " позиций");
            for (int i = shift_step; i < arr_len; i++) shiftToLeft(array);
        }
    }

    public static void shiftToRight(int[] array) {
        int swap_value;
        int arr_len = array.length;
        if (arr_len < 2) return; //оставил данную проверку, хотя она и избыточна конкретно в этой программе
        swap_value = array[arr_len - 1];
        for (int i = arr_len - 1; i > 0; i--) array[i] = array[i - 1];
        array[0] = swap_value;
    }

    public static void shiftToLeft(int[] array) {
        int swap_value;
        int arr_len = array.length;
        if (arr_len < 2) return; //оставил данную проверку, хотя она и избыточна конкретно в этой программе
        swap_value = array[0];
        for (int i = 0; i < arr_len - 1; i++) array[i] = array[i + 1];
        array[arr_len - 1] = swap_value;
    }

    public static int sumArray(int[] array) {
        int ret_val = 0;
        for (int i = 0; i < array.length; i++) ret_val += array[i];
        return ret_val;
    }

    public static void shiftArrayQuick(int[] array, int shift_step) {
        int arr_len = array.length;
        if (arr_len < 2) return; //массив из 1 элемента можно не сдвигать

        shift_step %= arr_len;
        if (shift_step == 0) return; //сдвигать не надо
        //если shift_step отрицательный, то и после деления по модулю он тоже будет отрицательный
        //согласно спецификации Java (т.е. тот же знак, что и у делимого)

        //вывод в консоль оставлен для демонстрации работы метода
        if (shift_step > 0)   System.out.println("Сдвигаем вправо на " + shift_step + " позиций");
        else System.out.println("Сдвигаем влево на " + (-shift_step) + " позиций");

        //приводим shift_step к положительным значениям от 1 до array.length - 1
        if (shift_step < 0) shift_step += arr_len;

        //объявляем вспомогательные переменные
        int swap;
        int index, next_index;
        int nod = getNOD(arr_len, shift_step); //nod - наибольший общий делитель arr_len и shift_step - количество проходов по массиву
        int cycle_len = arr_len / nod;         //длина одного прохода
        //Далее делаем nod проходов по массиву по cycle_len каждый. Итого - arr_len операций по перестановке значений в массиве
        for (int i = 0; i < nod; i++) {
            index = arr_len - 1 - i; //индекс начальной ячейки для внутреннего цикла
            swap = array[index];
            for (int j = 0; j < cycle_len - 1; j++) {
                next_index = (arr_len + index - shift_step) % arr_len;
                array[index] = array[next_index];
                index = next_index;
            }
            array[index] = swap; //завершаем проход
        }
    }

    public static int getNOD(int a, int b) {
        return b == 0 ? a : getNOD(b, a % b);
    }
}
