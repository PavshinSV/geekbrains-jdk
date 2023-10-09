package seminar01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private final int DOT_PADDING = 5;
    private int gameOverType;
    private final int STATE_DRAW = 0;
    private final int WIN_HUMAN = 1;
    private final int WIN_AI = 2;

    private boolean isGameOver;
    private boolean isInitialized;

    private final String MSG_WIN_HUMAN = "Победил человек";
    private final String MSG_WIN_AI = "Победил компьютер";
    private final String MSG_DRAW = "Ничья";

    int panelWidth;
    int panelHeight;
    int cellWidth;
    int cellHeight;
    private final char HUMAN_DOT = 1;
    private final char AI_DOT = 2;
    private final char EMPTY_DOT = 0;
    private int fieldSizeX = 3;
    private int fieldSizeY = 3;

    private int len = 3;
    private char[][] field;
    private Random random;

    Map() {
        initMap();
        random = new Random();
        setBackground(Color.GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }

    private void initMap() {
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private void initMap(int Sx, int Sy) {
        fieldSizeX = Sx;
        fieldSizeY = Sy;
        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }

    private boolean checkWin(char c) {
        if (len > fieldSizeY || len > fieldSizeX) {
            throw new RuntimeException("Длина линии победы не может быть длиннее чем размер поля");
        }
        for (int i = 0; i < fieldSizeY; i++) {
            int stop = fieldSizeX - len + 1;
            for (int shift = 0; shift < stop; shift++) {
                for (int j = shift; j < shift + len; j++) {
                    if (c != field[i][j]) {
                        break;
                    }
                    if (j == shift + len - 1) {
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int stop = fieldSizeY - len + 1;
            for (int shift = 0; shift < stop; shift++) {
                for (int j = shift; j < shift + len; j++) {
                    if (c != field[j][i]) {
                        break;
                    }
                    if (j == shift + len - 1) {
                        return true;
                    }
                }
            }
        }

//TODO: Доделать проверку диагоналей для общего случая
        int repeatStop=fieldSizeY-len+1;
        for (int repeat=0;repeat<repeatStop;repeat++){
            int shiftStop=fieldSizeX-len+1;
            for(int shift=0;shift<shiftStop;shift++){
                for (int i=0; i<len;i++){
                    int y=repeat+i;
                    int x= shift+i;
                    if (c!=field[y][x]){
                        break;
                    }
                    if (i == len-1) {
                        return true;
                    }
                }
            }
        }

        for (int repeat=0;repeat<repeatStop;repeat++){
            int shiftStop=fieldSizeX-len+1;
            for(int shift=0;shift<shiftStop;shift++){
                for (int i=0; i<len;i++){
                    int y=repeat+len-i-1;
                    int x= shift+i;
                    if (c!=field[y][x]){
                        break;
                    }
                    if (i == len-1) {
                        return true;
                    }
                }
            }
        }


        return false;
    }

    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    public void update(MouseEvent e) {
        if (isGameOver) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) {
            return;
        }

        field[cellY][cellX] = HUMAN_DOT;
        if (checkEndGame(HUMAN_DOT, WIN_HUMAN)) {
            return;
        }
        aiTurn();
        repaint();
        if (checkEndGame(AI_DOT, WIN_AI)) {
            return;
        }

//        System.out.println(cellX + " " + cellY);
        repaint();
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
                break;
            case WIN_AI:
                g.drawString(MSG_WIN_AI, 20, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("UNEXPECTED BEHAVIOR");
        }
    }

    private boolean checkEndGame(char dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
//        fSzX = 5;
//        fSzY = 5;
        fieldSizeX = fSzX;
        fieldSizeY = fSzY;
        initMap(fieldSizeX, fieldSizeY);
        //System.out.printf("Mode: %d\nSize: %d, %d; Length: %d\n-------\n", mode, fSzX, fSzY, wLen);
        isGameOver = false;
        isInitialized = true;
        len=wLen;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        g.setColor(Color.BLACK);
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellWidth = panelWidth / fieldSizeX;
        cellHeight = panelHeight / fieldSizeY;

        for (int i = 1; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int i = 1; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) {
                    continue;
                } else if (field[y][x] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);

                } else if (field[y][x] == AI_DOT) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + DOT_PADDING, y * cellHeight + DOT_PADDING, cellWidth - DOT_PADDING * 2, cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("UNKNOWN CHARACTER " + field[y][x] + " in cell x:" + x + ", y:" + y);
                }

            }
        }
        if (isGameOver) {
            showMessageGameOver(g);
        }
    }
}
