package Lesson8;

public class Logic {
    static int SIZE = 3;
    static int DOTS_TO_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;
    static long baseKarma = (long) Math.pow(5, DOTS_TO_WIN);
    static Winline winline = new Winline();
    static boolean isWinner = false;

    static boolean gameFinish = false;
    static String winnerName ="...";

    public static void go() {
        gameFinish = true;
//        printMap();

        if (checkWin(DOT_X, DOT_X)) {
//            System.out.println("Human WINS!!!");
            winnerName ="       Human WINS!!!";
            isWinner = true;
            return;
        }

        if (checkDraw()) {
//            System.out.println("Draw!");
            winnerName ="        D R A W !!!";
            return;
        }

        aiTurn();
//        printMap();

        if (checkWin(DOT_O, DOT_O)) {
//            System.out.println("Computer WINS!!!");
            winnerName ="     Computer WINS!!!";
            isWinner = true;
            return;
        }

        if (checkDraw()) {
//            System.out.println("Draw!");
            winnerName ="        D R A W !!!";
            return;
        }
        gameFinish = false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

//    private static void printMap() {
//        System.out.print("  ");
//        for (int i = 1; i <= SIZE; i++) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//
//        for (int i = 0; i < SIZE; i++) {
//            System.out.print(i + 1 + " ");
//            for (int j = 0; j < SIZE; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    public static void setHumanYX(int y, int x){
        if(isCellValid(y,x)){
            map[y][x] = DOT_X;
            go();
        }
    }

    private static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    private static void aiTurn() {
        int x, y;
        long currentKarma, maxKarma;
        int maxKarmaX = 0, maxKarmaY = 0;

        //1-я задача: если есть возможность - победить
        for (y = 0; y < SIZE; y++) {
            for (x = 0; x < SIZE; x++) {
                if (isCellValid(y, x)) {
                    map[y][x] = DOT_O;
                    if (checkWin(DOT_O, DOT_O)) {
                        return;  //это я удачно сходил - компьютер выиграл!
                    }
                    map[y][x] = DOT_EMPTY;
                }
            }
        }
        //победить не удалось. Задача 2 - не проиграть
        for (y = 0; y < SIZE; y++) {
            for (x = 0; x < SIZE; x++) {
                if (isCellValid(y, x)) {
                    map[y][x] = DOT_X;
                    if (checkWin(DOT_X, DOT_X)) {
                        map[y][x] = DOT_O; //если следующим ходом человек может победить - блокируем
                        return;
                    }
                    map[y][x] = DOT_EMPTY;
                }
            }
        }

        //задача 3 - сходить наиболее оптимальным образом
        //определяем выгоду от хода в каждое из доступных полей и определяем наиболее выгодное поле
        //Выгоду определяем по алгоритму в методе getKarma() (возможны и другие способы вычисления)
        maxKarma = -1;
        for (y = 0; y < SIZE; y++) {
            for (x = 0; x < SIZE; x++) {
                if (isCellValid(y, x)) {
                    //здесь, у profitForAI() и damageForHuman() можно добавить весовые коэффициенты и тогда
                    //стратегия компьютера будет меняться либо в сторону "победить" либо в сторону "навредить".
                    //Эмпирическим путем пока подобран коэффициент 2 в пользу "победить"
                    currentKarma = profitForAI(y, x) * 2 + damageForHuman(y, x);
                    if (currentKarma > maxKarma) {
                        maxKarma = currentKarma;
                        maxKarmaY = y;
                        maxKarmaX = x;
                    }
                }
            }
        }

        map[maxKarmaY][maxKarmaX] = DOT_O; //ходим в поле с максимальной кармой
    }

    private static long profitForAI(int y, int x) {
        return getKarma(y, x, DOT_O, DOT_X);
    }

    private static long damageForHuman(int y, int x) {
        return getKarma(y, x, DOT_X, DOT_O);
    }


    private static long getKarma(int y, int x, char goodSymbol, char wallSymbol) {
        long karma = 0;
        karma += getLineKarma(y, x, goodSymbol, wallSymbol, 0, 1);
        karma += getLineKarma(y, x, goodSymbol, wallSymbol, 1, 0);
        karma += getLineKarma(y, x, goodSymbol, wallSymbol, 1, 1);
        karma += getLineKarma(y, x, goodSymbol, wallSymbol, 1, -1);
        return karma;
    }

    private static long getLineKarma(int startY, int startX, char goodSymbol, char wallSymbol, int stepY, int stepX) {
        //плюсуем в карму за каждый goodSymbol, который находится от данной точки
        //в прямой видимости на расстоянии не более DOTS_TO_WIN-1 (в направлениях (stepY, stepX) и (-stepY, -stepX))
        //Т.е. между этим goodSymbol и точкой (y, x) не должно быть wallSymbol
        int i;
        int x, y;
        long karma1 = 0;  //в направлении (stepY, stepX)
        long karma2 = 0;  //в направлении (-stepY, -stepX)
        long karma3 = 0;  //бонус за вставку посередине
        long profitKarma = 0; //итоговая карма
        int maxLength;  //длина максимально возможной линии в данных направлениях
        //Если DOTS_TO_WIN недостижим, то карму не плюсуем
        int goodCount;

        //идем в сторону stepY, stepX
        maxLength = 1;
        x = startX;
        y = startY;
        goodCount = 0;
        for (i = 1; i < DOTS_TO_WIN; i++) {
            x += stepX;
            y += stepY;
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) break; //выход за пределы поля
            if (map[y][x] == wallSymbol) break;
            if (map[y][x] == goodSymbol) {
                goodCount++;  //считаем goodSymbol
                karma1 += 2 * DOTS_TO_WIN / i; //немного плюсуем за расстояние (по началу, лучше ставить ближе к противнику)
            }
            maxLength++;
        }
        //плюсуем за общее количество
        if (goodCount > 0) {
            karma1 += baseKarma / Math.pow(5, DOTS_TO_WIN - goodCount); //чем больше знаков - тем больше карма
        }

        //идем в сторону -stepY, -stepX
        x = startX;
        y = startY;
        goodCount = 0;
        for (i = 1; i < DOTS_TO_WIN; i++) {
            x -= stepX;
            y -= stepY;
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) break; //выход за пределы поля
            if (map[y][x] == wallSymbol) break;
            if (map[y][x] == goodSymbol) {
                goodCount++;  //считаем goodSymbol
                karma2 += 2 * DOTS_TO_WIN / i; //немного плюсуем за расстояние (по началу, лучше ставить ближе к противнику)
            }
            maxLength++;
        }

        if (goodCount > 0) {
            karma2 += baseKarma / Math.pow(5, DOTS_TO_WIN - goodCount); //чем больше знаков - тем больше карма
        }

        //карму увеличиваем только если максимальная длина возможного ряда больше либо равна DOTS_TO_WIN
        if (maxLength >= DOTS_TO_WIN) {
            if (karma1 > 0 && karma2 > 0) { //если в том и другом направлении есть goodSymbols
                karma3 = (karma1 + karma2) / 2; //бонус за вставку посередине
            }
            profitKarma = karma1 + karma2 + karma3;
        }

        return profitKarma; //возвращаем посчитанное (накопленное)
    }


    private static boolean checkDraw() {
        if(checkWin(DOT_O, DOT_EMPTY)) return false;
        if(checkWin(DOT_X, DOT_EMPTY)) return false;
        return true;
    }

    private static boolean checkWin(char symbol, char symbol2) {

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                //смотрим, в каких направлениях можно проверить из стартовой точки (y, x)
                if (x <= SIZE - DOTS_TO_WIN) {  //можно проверить вправо
                    if (checkRow(y, x, 0, 1, symbol, symbol2)) return true;
                    if (y <= SIZE - DOTS_TO_WIN) { //можно проверить вправо и вниз
                        if (checkRow(y, x, 1, 1, symbol, symbol2)) return true;
                    }
                    if (y >= DOTS_TO_WIN - 1) { //можно проверить вправо и вверх
                        if (checkRow(y, x, -1, 1, symbol, symbol2)) return true;
                    }
                }
                if (y <= SIZE - DOTS_TO_WIN) { //можно проверить вниз
                    if (checkRow(y, x, 1, 0, symbol, symbol2)) return true;
                }
            }
        }
        return false;
    }

    private static boolean checkRow(int startY, int startX, int stepY, int stepX, char symbol, char symbol2) {
        int stY = startY;
        int stX = startX;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if ((map[stY][stX] != symbol) && (map[stY][stX] != symbol2)) return false;
            stY += stepY;
            stX += stepX;
        }
        winline.set(startY, startX,stepY, stepX, DOTS_TO_WIN);
        return true;
    }
}

