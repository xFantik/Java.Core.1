package ru.gb.home_works.lesson_8_Xand0;

import javax.swing.*;
import java.awt.*;

public class MyGameWindow extends JFrame {
    private final int button_down_height = 40;
    ImageIcon image_X;
    ImageIcon image_0;
    ImageIcon image_Blank;
    int elementSize = 50;
    private final int size_X = 30;
    private final int size_Y = 10;
    int winLineLength = 5;
    char[][] fieldChars;
    JButton[][] buttons;
    JPanel jPanel;
    Field field;

    public static void main(String[] args) {
        new MyGameWindow();
    }

    public MyGameWindow() {
        field = new Field(size_X, size_Y, winLineLength);
        fieldChars = field.field;
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(300, 300, elementSize * size_X, elementSize * size_Y + button_down_height);

        image_X = new ImageIcon("src/images/dog.png");
        image_0 = new ImageIcon("src/images/cat.png");
        image_Blank = new ImageIcon("src/images/blank.png");
        jPanel = new JPanel();
        createJButtonsField(jPanel);
        newGame();
        drawField();
        setVisible(true);
    }

    public void drawField() {
        JButton buttonUp = new JButton("Новая игра");
        buttonUp.setBounds(0, 0, elementSize * size_Y, button_down_height);
        buttonUp.addActionListener(actionEvent -> newGame());
        add(buttonUp, BorderLayout.SOUTH);
        getPlayingField(jPanel);
        add(jPanel);
        setVisible(true);
    }

    private void newGame() {
        field.clear();
        fieldChars = field.field;
        drawField();
        if (field.getCurrentState() == Field.BOT_STEP) {
            JOptionPane.showMessageDialog(null, "Мой ход!");
            field.doStep();
        } else
            JOptionPane.showMessageDialog(null, "Ходи первый!");
    }

    private void createJButtonsField(JPanel component) {
        buttons = new JButton[size_Y][size_X];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                JButton button = new JButton();
                buttons[i][j] = button;
                int finalI = i;
                int finalJ = j;
                button.addActionListener(actionEvent -> click(finalI, finalJ));
                component.add(button);
            }
        }
    }

    private void click(int i, int j) {
        if (field.isGameOver())
            return;
        if (!field.doStep(i, j)) {
            drawField();
            return;
        }
        drawField();
        if (field.isGameOver()) {
            drawResult();
            return;
        }
        field.doStep();
        drawField();
        if (field.isGameOver()) {
            drawResult();
        }
    }

    private void drawResult() {
        switch (field.getCurrentState()) {
            case Field.STANDOFF -> JOptionPane.showMessageDialog(null, "\"Больше линий здесь не нарисовать! Ничья!\"");
            case Field.botWin -> JOptionPane.showMessageDialog(null, "\"ХА! Комп Победил!\"");
            case Field.playerWin -> JOptionPane.showMessageDialog(null, "Мои поздравления! Ты выиграл!");
        }
        JOptionPane.showMessageDialog(null, "\"Спасибо за игру!\"");
//        setVisible(false);
    }

    private void getPlayingField(JPanel component) {
        component.setLayout(new GridLayout(size_Y, size_X));
        for (int i = 0; i < fieldChars.length; i++) {
            for (int j = 0; j < fieldChars[i].length; j++) {
                if (fieldChars[i][j] == '0')
                    buttons[i][j].setIcon(image_0);
                else if (fieldChars[i][j] == 'X')
                    buttons[i][j].setIcon(image_X);
                else
                    buttons[i][j].setIcon(image_Blank);
            }
        }

        component.setVisible(true);
    }


}
