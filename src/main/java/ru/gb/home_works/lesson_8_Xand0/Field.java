package ru.gb.home_works.lesson_8_Xand0;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private ArrayList<Line> lines;                                     //массив всех возможных линий
    private char[][] field;                                             //игровое поле
    private int currentState;                                       // статус игры (чей ход, чья победа, ничья)
    private final int fieldSizeX;
    private final int fieldSizeY;
    private final int winLineLength;

    public static final int PLAYER_STEP = 1;
    public static final int BOT_STEP = 2;
    public static final int playerWin = 91;
    public static final int botWin = 92;
    public static final int STANDOFF = 55;

    private final char botChar = 'X';
    private final char userChar = '0';
    private final char emptyDot = '∙';

    private Line winLine;
    private int lastStepI;
    private int lastStepJ;


    public Field(int sizeX, int sizeY, int winLineLength) {
        fieldSizeX = sizeX;
        fieldSizeY = sizeY;
        this.winLineLength = winLineLength;
        clear();
        whoIsFirstPlayer();
    }

    private void whoIsFirstPlayer() {
        if (new Random().nextInt(2) == 0) {
            currentState = BOT_STEP;
        } else {
            currentState = PLAYER_STEP;
        }
    }


    public void clear() {
        char[][] field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < field.length; i++) {                           //Заполняем игровое поле точками
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = emptyDot;
            }
        }
        this.field = field;
        winLine=null;
        createLines();
        whoIsFirstPlayer();
    }

    public int getArrayLength(int i){
        return field[i].length;
    }
    public int getArrayLength(){
        return field.length;
    }
    public char getArraySymbol(int i, int j){
        return field[i][j];
    }

    public int getLastStepI() {
        return lastStepI;
    }

    public int getLastStepJ() {
        return lastStepJ;
    }

    public void doStep() {
        if (!hasEmptySlot()) {
            currentState = STANDOFF;
            return;
        }
        int[] nextBotStep = new int[2];
        calculateStep(nextBotStep);                                                         //вычисление лучшего хода
        doStep(nextBotStep[0], nextBotStep[1], botChar);

    }

    public boolean doStep(int x, int y) {

        if (currentState==BOT_STEP)
            return doStep(x, y, botChar);
        else
             return doStep(x, y, userChar);
    }

    public boolean doStep(int x, int y, char symbol) {
        if (field[x][y] == emptyDot) {
            field[x][y] = symbol;
            currentState = (symbol == botChar) ? PLAYER_STEP : BOT_STEP;
            lastStepI =x;
            lastStepJ=y;
            return true;
        }
        return false;
    }

    private void calculateStep(int[] nextBotStep) {                                 //Вычисление лучшего хода

        int[][] allPossibleSteps = getAllPossibleSteps();                           //   Получение массива всех пустых клеток
        //    {координата Х, координата У, "вес" хода (считается далее)}

        for (int i = 0; i < lines.size(); i++) {                                    //  Анализ всех линий
            Line l = lines.get(i);
            int lineWeight = 100;
            int usedDots = l.getLineChars(botChar);                                 //  Количество занятых точек в линиях бота
            if (usedDots == winLineLength - 1)                                      //
                lineWeight += 123456789;                                            //  Чем ближе к полному заполнению линии
            if (usedDots == winLineLength - 2)                                      //          тем больший вес линии
                lineWeight += 1234567;
            lineWeight += usedDots * 1000;

            usedDots = l.getLineChars(userChar);                                    // так же учитываем заполненость линий игроком
            if (usedDots == winLineLength - 1)
                lineWeight += 12345678;
            if (usedDots == winLineLength - 2)
                lineWeight += 123456;
            lineWeight += usedDots * 800;

            for (int j = 0; j < l.dotes.length; j++) {
                if (field[l.dotes[j][0]][l.dotes[j][1]] == emptyDot) {               //  Для всех пустых клеток в линнии
                    updateWeight(allPossibleSteps,                                   //          обновляем их вес в массиве всех свободных клеток
                            l.dotes[j][0], l.dotes[j][1], lineWeight);
                }
            }
        }

        sortPossibleSteps(allPossibleSteps);                                        //  сортировка свободных клеток по "весу"
        nextBotStep[0] = allPossibleSteps[0][0];                                    //  сохранение лучшего хода
        nextBotStep[1] = allPossibleSteps[0][1];
//        for (int i = 0; i < allPossibleSteps.length; i++) {
//            System.out.println("x,y: " + (allPossibleSteps[i][0] + 1) + " " + (1 + allPossibleSteps[i][1]) + " вес:" + allPossibleSteps[i][2]);
//        }

    }

    private void updateWeight(int[][] possibleSteps, int i, int j, int addedWeight) {
        for (int k = 0; k < possibleSteps.length; k++) {
            if (possibleSteps[k][0] == i && possibleSteps[k][1] == j) {
                possibleSteps[k][2] += addedWeight;
            }
        }
    }

    private void sortPossibleSteps(int[][] array) {
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
    }


    private int[][] getAllPossibleSteps() {
        int[][] allPossibleStepsBuffer = new int[fieldSizeX * fieldSizeY][3];
        int tmp = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == emptyDot) {
                    allPossibleStepsBuffer[tmp][0] = i;
                    allPossibleStepsBuffer[tmp][1] = j;
                    allPossibleStepsBuffer[tmp][2] = 0;
                    tmp++;
                }
            }
        }
        int[][] allPossibleSteps = new int[tmp][3];
        System.arraycopy(allPossibleStepsBuffer, 0, allPossibleSteps, 0, allPossibleSteps.length);
        return allPossibleSteps;
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

    private void createLines() {
        lines = new ArrayList<Line>();
        Line tmpLine;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {                         // От каждой клетки поля
                tmpLine = getLineRow(i, j);                                     //          пытаемся строить линию по 4м направлениям
                if (tmpLine != null)                                            // Если линия вернулась,
                    lines.add(tmpLine);                                         //          добавляем в массив
                tmpLine = getLineColumn(i, j);
                if (tmpLine != null)
                    lines.add(tmpLine);
                tmpLine = getLineDiag(i, j);
                if (tmpLine != null)
                    lines.add(tmpLine);
                tmpLine = getLineBackDiag(i, j);
                if (tmpLine != null)
                    lines.add(tmpLine);
            }
        }
//        for (int i = 0; i < lines.size(); i++) {
//            System.out.println(lines.get(i));
//        }
    }

    private Line getLineRow(int i, int j) {
        if (j + winLineLength - 1 < field[i].length) {
            int[][] dotes = new int[winLineLength][2];
            for (int k = 0; k < winLineLength; k++) {
                dotes[k] = new int[]{i, j + k};
            }
            return new Line(dotes);
        }
        return null;
    }

    private Line getLineColumn(int i, int j) {
        if (i + winLineLength - 1 < field.length) {
            int[][] dotes = new int[winLineLength][2];
            for (int k = 0; k < winLineLength; k++) {
                dotes[k] = new int[]{i + k, j};
            }
            return new Line(dotes);
        }
        return null;
    }

    private Line getLineDiag(int i, int j) {
        if (j + winLineLength - 1 < field[i].length && i + winLineLength - 1 < field.length) {
            int[][] dotes = new int[winLineLength][2];
            for (int k = 0; k < winLineLength; k++) {
                dotes[k] = new int[]{i + k, j + k};
            }
            return new Line(dotes);
        }
        return null;
    }

    private Line getLineBackDiag(int i, int j) {
        if (i - winLineLength + 1 >= 0 && j + winLineLength - 1 < field[i].length) {
            int[][] dotes = new int[winLineLength][2];
            for (int k = 0; k < winLineLength; k++) {
                dotes[k] = new int[]{i - k, j + k};
            }
            return new Line(dotes);
        }
        return null;
    }

    public boolean isGameOver() {
        boolean gameOverFlag = false;
        for (int i = 0; i < lines.size(); i++) {                                    // Для всех линий
            Line l = lines.get(i);                                                  //
            if (l.getLineStatus() > 90) {                                           // Если линия выиграла, меняем статус игры
                currentState = lines.get(i).getLineStatus();
                gameOverFlag = true;
            }
            if (l.getLineStatus() == STANDOFF) {                                    //если линия "ничья"
                lines.remove(i);                                                    //          удаляем из массива
                i--;
            }
        }
        if (lines.size() == 0) {                                                   // если возможных для победы линий не осталось
            currentState = STANDOFF;                                              //              статус игры - "ничья"
            gameOverFlag = true;
        }
        return gameOverFlag;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void waitImitation() {
//        try {
//            for (int i = 0; i < 10; i++) {
//                Thread.sleep(150);
//                System.out.print(".");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public boolean isWinDot(int i, int j){
        if (winLine==null) return false;
        for (int k = 0; k < winLine.dotes.length; k++) {
            if (i==winLine.dotes[k][0]&&j==winLine.dotes[k][1]){
                return true;
            }
        }
        return false;
    }

    private class Line {

        public static final int EMPTY_LINE = 0;                             //  пустая линия
        public static final int BOT_LINE = 52;                              //  линия   только с крестиками
        public static final int USER_LINE = 51;                             //          только с крестиками
        private int[][] dotes;

        public Line(int[][] dotes) {
            this.dotes = dotes;
        }

        public int getLineStatus() {
            int counterUserChars = getLineChars(userChar);
            int counterBotChars = getLineChars(botChar);
            int counterEmptyDots = getLineChars(emptyDot);

            if (counterBotChars == winLineLength) {
                winLine = this;
                return botWin;
            }
            if (counterUserChars == winLineLength) {
                winLine = this;
                return playerWin;
            }
            if (counterEmptyDots == winLineLength) return EMPTY_LINE;
            if (counterBotChars > 0 && counterUserChars > 0) return STANDOFF;
            if (counterBotChars > 0 && counterUserChars == 0) return BOT_LINE;
            if (counterBotChars == 0 && counterUserChars > 0) return USER_LINE;
            return 1000;
        }

        private int getLineChars(char symbol) {
            int counter = 0;
            for (int i = 0; i < dotes.length; i++) {
                if (field[dotes[i][0]][dotes[i][1]] == symbol) {
                    counter++;
                }
            }
            return counter;
        }
    }

    @Override
    public String toString() { //метод печать поля
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < field.length; i++) {
            result.append(i + 1).append("  ");
            for (int j = 0; j < field[i].length; j++) {
                result.append(field[i][j]).append(" ");
            }
            result.append('\n');
        }
        return result.toString();
    }


}
