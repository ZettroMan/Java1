package Lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleMap extends JPanel {
    public static final int H_VS_A = 0;
    public static final int H_VS_H = 1;

    private static int timeCounter = 0;
    private boolean boldTrigger = false;
    private boolean gameOverDisplayed = false;
    private static GameOverWindow gameOverWindow = new GameOverWindow();

    int fieldSizeX;
    int fieldSizeY;
    int winLength;

    int cellHeight;
    int cellWidth;

    static BasicStroke standardPen = new BasicStroke(5);
    static BasicStroke boldPen = new BasicStroke(8);

    boolean isInitialized = false;

    public BattleMap() {
        setBackground(new Color(0x1B186E));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render((Graphics2D) g);
    }

    private void render(Graphics2D g2D) {

        if (!isInitialized) {
            return;
        }

        if (++timeCounter == 300) {
            timeCounter = 0;
            boldTrigger = !boldTrigger;
        }

        if(Logic.gameFinish && (!gameOverDisplayed)) {
            gameOverDisplayed = true;
            gameOverWindow.setText(Logic.winnerName);
            gameOverWindow.setVisible(true);
        }

        g2D.setColor(new Color(0x7968EC));
        g2D.setStroke(standardPen);
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g2D.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g2D.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < Logic.SIZE; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                if (Logic.map[i][j] == Logic.DOT_X) {
                    drawX(g2D, i, j);
                }
                if (Logic.map[i][j] == Logic.DOT_O) {
                    drawO(g2D, i, j);
                }
            }
        }
        repaint();
    }

    public void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
//        System.out.printf("mode %d fieldSizeX %d fieldSizeY %d winLength %d \n"
//                , mode, fieldSizeX, fieldSizeY, winLength);

        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        timeCounter = 0;
        isInitialized = true;
        gameOverDisplayed = false;
        repaint();
    }

    private void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

//        System.out.println(cellY + " " + cellX);
        if (!Logic.gameFinish) {
            Logic.setHumanYX(cellY, cellX);
        }
//        else {
//            System.out.println(Logic.winnerName);
//        }
        repaint();
    }

    private void drawX(Graphics2D g, int cellY, int cellX) {
        if (boldTrigger && Logic.isWinner && (Logic.winline.contains(cellY, cellX))) {
            g.setStroke(boldPen);
            g.setColor(new Color(0xF5E804));
        } else {
            g.setStroke(standardPen);
            g.setColor(new Color(214, 43, 48));
        }
        g.drawLine(cellX * cellWidth + 8, cellY * cellHeight + 8,
                (cellX + 1) * cellWidth - 8, (cellY + 1) * cellHeight - 8);
        g.drawLine((cellX + 1) * cellWidth - 8, cellY * cellHeight + 8,
                cellX * cellWidth + 8, (cellY + 1) * cellHeight - 8);

    }

    private void drawO(Graphics2D g, int cellY, int cellX) {
        if (boldTrigger && Logic.isWinner && (Logic.winline.contains(cellY, cellX))) {
            g.setStroke(boldPen);
            g.setColor(new Color(0xF5E804));
        } else {
            g.setStroke(standardPen);
            g.setColor(new Color(119, 204, 134));
        }
        g.drawOval(cellX * cellWidth + 8, cellY * cellHeight + 8,
                cellWidth - 16, cellHeight - 16);

    }
}
