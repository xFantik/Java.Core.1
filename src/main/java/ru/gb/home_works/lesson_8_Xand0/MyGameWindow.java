package ru.gb.home_works.lesson_8_Xand0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGameWindow extends JFrame {
    private ImageIcon image_X;
    private ImageIcon image_0;
    private ImageIcon image_Blank;
    int elementSize = 50;
    private final int size_X = 30;
    private final int size_Y = 15;
    int winLineLength = 5;
    JButton[][] buttons;
    JPanel jPanel;
    Field field;

    public static void main(String[] args) {
        new MyGameWindow();
    }

    public MyGameWindow() {
        field = new Field(size_X, size_Y, winLineLength);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(200, 200, elementSize * size_X, elementSize * size_Y);

        image_0 = new ImageIcon("src/images/smile3.png");
        image_X = new ImageIcon("src/images/smile4.png");
        image_Blank = new ImageIcon("src/images/blank.png");
        jPanel = new JPanel();
        createJButtonsField(jPanel);



        JMenuBar menuBar = new JMenuBar();

        JMenu new_game_menu = new JMenu("Новая игра");
        JMenuItem new_game = new JMenuItem("Новая игра");
        JMenu select_images = new JMenu("Images");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        new_game.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGame();
                drawField();
            }
        });


        JMenuItem cats = new JMenuItem("Котики");
        cats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image_0 = new ImageIcon("src/images/dog.png");
                image_X = new ImageIcon("src/images/cat.png");
                drawField();
            }
        });

        JMenuItem smiles = new JMenuItem("Смайлики");
        smiles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image_X = new ImageIcon("src/images/smile1.png");
                image_0 = new ImageIcon("src/images/smile2.png");
                drawField();
            }
        });
        JMenuItem bugs = new JMenuItem("Жуки");
        bugs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                image_X = new ImageIcon("src/images/smile4.png");
                image_0 = new ImageIcon("src/images/smile3.png");
                drawField();
            }
        });


        select_images.add(cats);
        select_images.add(smiles);
        select_images.add(bugs);


        exitItem.setMaximumSize(new Dimension(100,100));

        new_game_menu.add(new_game);
        menuBar.add(new_game_menu);
        menuBar.add(select_images);
        menuBar.add(exitItem);
        setJMenuBar(menuBar);

        newGame();
        drawField();
        setVisible(true);


    }


    public void drawField() {
        getPlayingField(jPanel);
        add(jPanel);
        setVisible(true);
    }

    private void newGame() {
        field.clear();
        drawField();
        if (field.getCurrentState() == Field.BOT_STEP) {
            JOptionPane.showMessageDialog(null, "Не против, если я буду ходить первым?");
            field.doStep();
            drawField();
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
        drawField();
        switch (field.getCurrentState()) {
            case Field.STANDOFF -> JOptionPane.showMessageDialog(null, "Больше линий здесь не нарисовать! Ничья!");
            case Field.botWin -> JOptionPane.showMessageDialog(null, "ХА! Комп Победил!");
            case Field.playerWin -> JOptionPane.showMessageDialog(null, "Мои поздравления! Ты выиграл!");
        }
//        JOptionPane.showMessageDialog(null, "\"Спасибо за игру!\"");
//        setVisible(false);
    }

    private void getPlayingField(JPanel component) {
        component.setLayout(new GridLayout(size_Y, size_X));
        for (int i = 0; i < field.getArrayLength(); i++) {
            for (int j = 0; j < field.getArrayLength(i); j++) {
                if (field.getArraySymbol(i,j) == '0')
                    buttons[i][j].setIcon(image_0);
                else if (field.getArraySymbol(i,j) == 'X'){
                    buttons[i][j].setIcon(image_X);
                }
                else
                    buttons[i][j].setIcon(image_Blank);
                if (field.isWinDot(i,j))
                    buttons[i][j].setBackground(new Color(0x94C2BE));
                else buttons[i][j].setBackground(null);
            }
        }
        component.setVisible(true);
    }
}
