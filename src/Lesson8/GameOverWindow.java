package Lesson8;

import javax.swing.*;
import java.awt.*;

public class GameOverWindow extends JFrame {

    private static final int WINDOW_POS_X = 600;
    private static final int WINDOW_POS_Y = 470;
    private static final int WINDOW_HEIGHT = 100;
    private static final int WINDOW_WIDTH = 300;
    private static JLabel textLabel =  new JLabel();

    public GameOverWindow() {
        setTitle("");
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        textLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        add(textLabel);
        setVisible(false);
    }

    public void setText(String text) {
        textLabel.setText(text);
    }
}
