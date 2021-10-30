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
    private char emptyDot = '∙';

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

    public Field(char[][] pole, int size, int winLineLength) {
        fieldSize = size;
        this.winLineLength = winLineLength;
        this.field = pole;
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

            int[] nextBotStep = new int[2];
            while (true) {

                calculateStep(nextBotStep);

                if (doStep(nextBotStep[0], nextBotStep[1], botChar)) {
                    waitImitation();
                    System.out.println((nextBotStep[0] + 1) + " " + (nextBotStep[1] + 1));
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
                } else if (!doStep(x, y, userChar)) {
                    System.out.println("Поле занято. Повторите попытку");
                    continue;
                } else {
                    return true;
                }
            }
        }
    }

    private void calculateStep(int[] nextBotStep) {
        int[][] allPossibleStepsBuffer = new int[fieldSize * fieldSize][3];
        int tmp = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == emptyDot) {
                    allPossibleStepsBuffer[tmp][0] = i;
                    allPossibleStepsBuffer[tmp][1] = j;
                    allPossibleStepsBuffer[tmp][2] = 0;
                    if (i == 0) {
                        allPossibleStepsBuffer[tmp][2] -= 50;
                    }
                    if (j == 0) {
                        allPossibleStepsBuffer[tmp][2] -= 50;
                    }
                    if (i == fieldSize - 1) {
                        allPossibleStepsBuffer[tmp][2] -= 50;
                    }
                    if (j == fieldSize - 1) {
                        allPossibleStepsBuffer[tmp][2] -= 50;
                    }
                    if (i == 1) {
                        allPossibleStepsBuffer[tmp][2] -= 25;
                    }
                    if (j == 1) {
                        allPossibleStepsBuffer[tmp][2] -= 25;
                    }
                    if (i == fieldSize - 2) {
                        allPossibleStepsBuffer[tmp][2] -= 25;
                    }
                    if (j == fieldSize - 2) {
                        allPossibleStepsBuffer[tmp][2] -= 25;
                    }

                    tmp++;
                }
            }
        }
        int[][] allPossibleSteps = new int[tmp][3];                   //Маассив со всеми возможными координатами ходов. Третий элемент - вес хода
        System.arraycopy(allPossibleStepsBuffer, 0, allPossibleSteps, 0, allPossibleSteps.length);
        allPossibleStepsBuffer = null;

//        System.out.println("Все возможные ходы:");
//        for (int i = 0; i < allPossibleSteps.length; i++) {
//            System.out.println("x,y: "+allPossibleSteps[i][0]+" " +allPossibleSteps[i][1]+" вес:" + allPossibleSteps[i][2]);
//        }
//        sortPossibleSteps(allPossibleSteps);
//        for (int i = 0; i < allPossibleSteps.length; i++) {
//            System.out.println("x,y: "+allPossibleSteps[i][0]+" " +allPossibleSteps[i][1]+" вес:" + allPossibleSteps[i][2]);
//        }


        char[][] virtualField = new char[fieldSize][fieldSize];         //Виртуальное поле для рассмотрения ходов

        for (int i = 0; i < allPossibleSteps.length; i++) {
            for (int j = 0; j < virtualField.length; j++) {
                System.arraycopy(field[j], 0, virtualField[j], 0, field[j].length);
            }
            virtualField[allPossibleSteps[i][0]][allPossibleSteps[i][1]] = botChar;
            Field virtField = new Field(virtualField, fieldSize, winLineLength);
            allPossibleSteps[i][2] += virtField.calculateWidth();
        }
        int sameStepNumber = sortPossibleSteps(allPossibleSteps);

//        for (int i = 0; i < allPossibleSteps.length; i++) {
//            System.out.println("x,y: " + (allPossibleSteps[i][0] + 1) + " " + (1 + allPossibleSteps[i][1]) + " вес:" + allPossibleSteps[i][2]);
//        }

        int index = new Random().nextInt(sameStepNumber);
        nextBotStep[0] = allPossibleSteps[index][0];
        nextBotStep[1] = allPossibleSteps[index][1];
//        Random random = new Random();
//        nextBotStep[0] = random.nextInt(fieldSize);
//        nextBotStep[1] = random.nextInt(fieldSize);


//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[i].length; j++) {
//                if (field[i][j] == botChar) {
//                    int t = drawLineRow(i, j, botChar);
//                    if (t >= winLineLength - 1) {
//                        if (tryAddLineRow(i, j, t, nextBotStep))
//                            System.out.println("Так решил алгоритм поиска выигрыша: " + nextBotStep[0] + " " + nextBotStep[1]);
//                        continue;
//
//                    }
//                    t = drawLineColumn(i, j, botChar);
//                    if (t >= winLineLength - 1) {
//                        if (tryAddLineColumn(i, j, t, nextBotStep))
//                            System.out.println("Так решил алгоритм поиска выигрыша: " + nextBotStep[0] + " " + nextBotStep[1]);
//                        continue;
//                    }
//                    t = drawLineDiag(i, j, botChar);
//                    if (t >= winLineLength - 1) {
//                        if (tryAddLineDiag(i, j, t, nextBotStep))
//                            System.out.println("Так решил алгоритм поиска выигрыша: " + nextBotStep[0] + " " + nextBotStep[1]);
//                        continue;
//                    }
//                    t = drawLineBackDiag(i, j, botChar);
//                    if (t >= winLineLength - 1) {
//                        if (tryAddLineBackDiag(i, j, t, nextBotStep))
//                            System.out.println("Так решил алгоритм поиска выигрыша: " + nextBotStep[0] + " " + nextBotStep[1]);
//                        continue;
//                    }
//                }
//            }
//        }

    }

    private int calculateWidth() {
        int result = 0;
        char symb = botChar;
        for (int i = 0; i < field.length; i++) {                                    //проверяем полезность хода (длины линий после простановки)
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == symb) {
                    int tempLineLength = drawLineRow(i, j, symb);
                    if (tempLineLength >= winLineLength) {
                        result += 1000000;
                    } else if (tempLineLength >= winLineLength - 1) {
                        result = tryAddLineRow(i, j, tempLineLength) * 10000;
                    } else if (tempLineLength >= winLineLength - 2) {
                        result += tryAddLineRow(i, j, tempLineLength) * 2000;
                    }
                    tempLineLength = drawLineColumn(i, j, symb);
                    if (tempLineLength >= winLineLength) {
                        result += 1000000;
                    } else if (tempLineLength >= winLineLength - 1) {
                        result = tryAddLineColumn(i, j, tempLineLength) * 10000;
                    } else if (tempLineLength >= winLineLength - 2) {
                        result += tryAddLineColumn(i, j, tempLineLength) * 2000;
                    }
                    tempLineLength = drawLineDiag(i, j, symb);
                    if (tempLineLength >= winLineLength) {
                        result += 1000000;
                    } else if (tempLineLength >= winLineLength - 1) {
                        result += tryAddLineDiag(i, j, tempLineLength) * 10000;

                    } else if (tempLineLength >= winLineLength - 2) {
                        result += tryAddLineDiag(i, j, tempLineLength) * 2000;
                    }
                    tempLineLength = drawLineBackDiag(i, j, symb);
                    if (tempLineLength >= winLineLength) {
                        result += 1000000;
                    } else if (tempLineLength >= winLineLength - 1) {
                        result += tryAddLineBackDiag(i, j, tempLineLength) * 10000;
                    } else if (tempLineLength >= winLineLength - 2) {
                        result += tryAddLineBackDiag(i, j, tempLineLength) * 2000;

                    }
                }
            }
        }
        symb = userChar;
        for (int i = 0; i < field.length; i++) {                                        //проверяем расклад для противника
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == symb) {
                    int tempLineLength = drawLineRow(i, j, symb);
                    if (tempLineLength >= winLineLength - 1) {
                        result-=tryAddLineRow(i, j, tempLineLength)* 100000;

                    } else if (tempLineLength >= winLineLength - 2) {
                        result-=tryAddLineRow(i, j, tempLineLength)* 60000;

                    }
                    tempLineLength = drawLineColumn(i, j, symb);
                    if (tempLineLength >= winLineLength - 1) {
                        result-=tryAddLineColumn(i, j, tempLineLength)* 100000;

                    } else if (tempLineLength >= winLineLength - 2) {
                        result-=tryAddLineColumn(i, j, tempLineLength)* 60000;

                    }
                    tempLineLength = drawLineDiag(i, j, symb);
                    if (tempLineLength >= winLineLength - 1) {
                        result-=tryAddLineDiag(i, j, tempLineLength)* 100000;

                    } else if (tempLineLength >= winLineLength - 2) {
                        result-=tryAddLineDiag(i, j, tempLineLength)* 60000;

                    }
                    tempLineLength = drawLineBackDiag(i, j, symb);
                    if (tempLineLength >= winLineLength - 1) {
                        result-=tryAddLineBackDiag(i, j, tempLineLength)* 100000;

                    } else if (tempLineLength >= winLineLength - 2) {
                        result-=tryAddLineBackDiag(i, j, tempLineLength)* 60000;

                    }
                }
            }
        }
        return result;
    }

    public boolean doStep(int x, int y, char symb) {
        if (field[x][y] == emptyDot) {
            field[x][y] = symb;
            currentState = (symb == botChar) ? PLAYER_STEP : BOT_STEP;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nx\n");
        result.append((char) 8595);
        result.append("  ");
        for (int i = 0; i < field.length; i++) {
            result.append(i + 1);
            result.append(' ');
        }
        result.append((char) (8592));
        result.append("y\n");
        for (int i = 0; i < field.length; i++) {
            result.append(i + 1 + "  ");
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
                Thread.sleep(150);
                System.out.print(".");
            }

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
            } else return length;
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
            } else return length;
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
            } else return length;
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
            } else return length;
        }
//        System.out.println("Длина " + symb + " = " + length);
        return length;
    }

    private boolean tryAddLineRow(int i, int j, int currentLength, int[] nextBotStep) {
        if (j + currentLength < field[i].length) {
            j += currentLength;
            if (field[i][j] == emptyDot) {
                nextBotStep[0] = i;
                nextBotStep[1] = j;
                return true;
            }
        }
        if (j > 0) {
            j--;
            if (field[i][j] == emptyDot) {
                nextBotStep[0] = i;
                nextBotStep[1] = j;
                return true;
            }
        }
        return false;
    }

    private boolean tryAddLineColumn(int i, int j, int currentLength, int[] nextBotStep) {
        if (i + currentLength < field.length) {
            i += (currentLength);
            if (field[i][j] == emptyDot) {
                nextBotStep[0] = i;
                nextBotStep[1] = j;
                return true;
            }
        }
        if (i > 0) {
            i--;
            if (field[i][j] == emptyDot) {
                nextBotStep[0] = i;
                nextBotStep[1] = j;
                return true;
            }
        }
        return false;
    }

    private boolean tryAddLineDiag(int i, int j, int currentLength, int[] nextBotStep) {
        if (j + currentLength < field[i].length && i + currentLength < field.length) {
            j += currentLength;
            i += currentLength;
            if (field[i][j] == emptyDot) {
                nextBotStep[0] = i;
                nextBotStep[1] = j;
                return true;
            }
        }
        if (j > 0 && i > 0) {
            j--;
            i--;
            if (field[i][j] == emptyDot) {
                nextBotStep[0] = i;
                nextBotStep[1] = j;
                return true;
            }
        }
        return false;
    }

    private boolean tryAddLineBackDiag(int i, int j, int currentLength, int[] nextBotStep) {
        if (j + currentLength < field[i].length && i > 0) {
            j += currentLength;
            i--;
            if (field[i][j] == emptyDot) {
                nextBotStep[0] = i;
                nextBotStep[1] = j;
                return true;
            }
        }
        if (j > 0 && i + currentLength < field.length) {
            j--;
            i += currentLength;
            if (field[i][j] == emptyDot) {
                nextBotStep[0] = i;
                nextBotStep[1] = j;
                return true;
            }
        }
        return false;

    }


    private int tryAddLineRow(int i, int j, int currentLength) {
        int result = 0;
        if (j + currentLength < field[i].length) {
            j += currentLength;
            if (field[i][j] == emptyDot) {
                result++;
            }
        }
        if (j > 0) {
            j--;
            if (field[i][j] == emptyDot) {
                result++;
            }
        }
        return result;
    }

    private int tryAddLineColumn(int i, int j, int currentLength) {
        int result = 0;
        if (i + currentLength < field.length) {
            i += (currentLength);
            if (field[i][j] == emptyDot) {
                result++;
            }
        }
        if (i > 0) {
            i--;
            if (field[i][j] == emptyDot) {
                result++;
            }
        }
        return result;
    }

    private int tryAddLineDiag(int i, int j, int currentLength) {
        int result = 0;
        if (j + currentLength < field[i].length && i + currentLength < field.length) {
            j += currentLength;
            i += currentLength;
            if (field[i][j] == emptyDot) {
                result++;
            }
        }
        if (j > 0 && i > 0) {
            j--;
            i--;
            if (field[i][j] == emptyDot) {
                result++;
            }
        }
        return result;
    }

    private int tryAddLineBackDiag(int i, int j, int currentLength) {
        int result = 0;
        if (j + currentLength < field[i].length && i > 0) {
            j += currentLength;
            i--;
            if (field[i][j] == emptyDot) {
                result++;
            }
        }
        if (j > 0 && i + currentLength < field.length) {
            j--;
            i += currentLength;
            if (field[i][j] == emptyDot) {
                result++;
            }
        }
        return result;

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

    public int getCurrentState() {
        return currentState;
    }

    private int sortPossibleSteps(int[][] array) {
        boolean sorted = false;
        int[] temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i][2] < array[i + 1][2]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        int maxWidth = array[0][2];
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i][2] == maxWidth) {
                return i + 1;
            }


        }
        return 1;

    }
}