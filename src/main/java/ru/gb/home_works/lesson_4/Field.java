package ru.gb.home_works.lesson_4;

import java.util.Random;
import java.util.Scanner;

public class Field {
    public static final int PLAYER_STEP = 1;
    public static final int BOT_STEP = 2;
    public static final int playerWin = 91;
    public static final int botWin = 92;
    public static final int STANDOFF = 22;


    private int currentState = 0;
    private char[][] field;
    private char botChar = 'X';
    private char userChar = '0';
    private char emptyDot = '.';

    private int fieldSize;
    private int winLineLength;

    public Field(int size, int winLineLength) {
        fieldSize = size;
        this.winLineLength = winLineLength;
        char[][] field = new char[size][size];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = emptyDot;
            }
        }
        this.field = field;
    }

    public boolean doStep() {
        if (currentState == 0) {
            if (new Random().nextInt(2) == 0) {
                System.out.println("Я хожу первый!");
                currentState = BOT_STEP;
            } else {
                System.out.println("Начинай игру");
                currentState = PLAYER_STEP;
            }
        }
        if (!hasEmptySlot()) {
            currentState = STANDOFF;
            return false;
        }

        if (currentState == BOT_STEP) {
            System.out.print("Мой ход");
            Random random = new Random();
            while (true) {
                int i = random.nextInt(fieldSize);
                int j = random.nextInt(fieldSize);
                if (field[i][j] == emptyDot) {
                    field[i][j] = botChar;
                    currentState = PLAYER_STEP;
                    waitImitation();
                    isWin(botChar);
                    return true;
                }
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Ваш ход:");
                int x = scanner.nextInt();
                int y;
                if (x >= 10) {
                    y = x % 10;
                    x /= 10;
                } else
                    y = scanner.nextInt();
                x--;
                y--;
                if (x >= fieldSize || y >= fieldSize || y < 0 || x < 0) {
                    System.out.printf("Мимо поля. Допустимые значени координат от 1 до %d. Повторите попытку.", fieldSize);
                    continue;
                } else if (!doStep(x, y)) {
                    System.out.println("Поле занято. Повторите попытку");
                    continue;
                } else {
                    isWin(userChar);
                    return true;
                }
            }
        }
    }

    public boolean doStep(int x, int y) {
        if (field[x][y] == emptyDot) {
            field[x][y] = userChar;
            currentState = BOT_STEP;
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                result.append(field[i][j] + " ");
            }
            result.append('\n');
        }
        return result.toString();
    }

    public boolean hasEmptySlot() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == emptyDot) {
                    return true;
                }
            }
        }
        return false;
    }

    public void waitImitation() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int drawLineRow(int i, int j, char symb) {
        int length = 1;
        while (j + 1 < field[i].length) {
            j++;
            if (field[i][j] == symb) {
                length++;
            }
        }
//        System.out.println("Длина " + symb + " = " + length);
        return length;
    }

    private int drawLineColumn(int i, int j, char symb) {
        int length = 1;
        while (i + 1 < field.length) {
            i++;
            if (field[i][j] == symb) {
                length++;
            }
        }
//        System.out.println("Длина " + symb + " = " + length);
        return length;
    }

    private int drawLineDiag(int i, int j, char symb) {
        int length = 1;
        while (i + 1 < field.length && j + 1 < field[i].length) {
            i++;
            j++;
            if (field[i][j] == symb) {
                length++;
            }
        }
//        System.out.println("Длина " + symb + " = " + length);
        return length;
    }

    private int drawLineBackDiag(int i, int j, char symb) {
        int length = 1;
        while (i > 0 && j + 1 < field[i].length) {
            i--;
            j++;
            if (field[i][j] == symb) {
                length++;
            }
        }
//        System.out.println("Длина " + symb + " = " + length);
        return length;
    }

    private boolean isWin(char symb) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == symb) {
                    int t = drawLineRow(i, j, symb);
                    if (t >= winLineLength) return true;
                    t = drawLineColumn(i, j, symb);
                    if (t >= winLineLength) return true;
                    t = drawLineDiag(i, j, symb);
                    if (t >= winLineLength) return true;
                    t = drawLineBackDiag(i, j, symb);
                    if (t >= winLineLength) return true;
                }
            }
        }
        return false;
    }


    public boolean isWin() {
        if (isWin(botChar)) {
            currentState = botWin;
            return true;
        }
        if (isWin(userChar)) {
            currentState = playerWin;
            return true;
        }
        return false;
    }

//    private boolean isWinRow() {
//        for (int x = 0; x < field.length; x++) {
//            if ((field[x][0] == field[x][1] && field[x][2] == field[x][1]) && field[x][0] != emptyDot) {
//                if (field[x][0] == botChar)
//                    currentState = botWin;
//                else
//                    currentState = playerWin;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isWinColumn() {
//        for (int x = 0; x < field.length; x++) {
//            if ((field[0][x] == field[1][x] && field[2][x] == field[1][x]) && field[0][x] != emptyDot) {
//                if (field[0][x] == botChar)
//                    currentState = botWin;
//                else
//                    currentState = playerWin;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isWinDiagonal() {
//        if (((field[0][0] == field[1][1] && field[2][2] == field[1][1]) || field[2][0] == field[1][1] && field[0][2] == field[1][1]) && field[1][1] != emptyDot) {
//            if (field[1][1] == botChar)
//                currentState = botWin;
//            else
//                currentState = playerWin;
//            return true;
//        }
//        return false;
//    }

    public int getCurrentState() {
        return currentState;
    }
}