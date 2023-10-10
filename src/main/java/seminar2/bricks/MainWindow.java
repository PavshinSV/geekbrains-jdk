package seminar2.bricks;

import seminar2.common.Background;
import seminar2.common.CanvasRepaintListener;
import seminar2.common.Interactable;
import seminar2.common.MainCanvas;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements CanvasRepaintListener {
    private int WIDTH = 800;
    private int HEIGHT = 600;
    private int POS_X = 400;
    private int POS_Y = 200;
    private final Interactable[] interactables = new Interactable[10];
    Background background;

    public MainWindow() {
        setTitle("Кирпичики");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        background = new Background();
        interactables[0]=background;

        for (int i = 1; i < interactables.length-1; i++) {
            interactables[i] = new Brick();
        }

        MainCanvas canvas = new MainCanvas(this);

        interactables[9]=background;
        add(canvas);

        setVisible(true);
    }

    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i < interactables.length; i++) {
            interactables[i].update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i < interactables.length; i++) {
            interactables[i].render(canvas, g);
        }
    }
}