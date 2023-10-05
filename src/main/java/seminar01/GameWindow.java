package seminar01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private final int WINDOW_HIGHT = 555;
    private final int WINDOW_WIDTH = 507;
    private final int WINDOW_POSX = 800;
    private final int WINDOW_POSY = 300;
    JButton buttonStart = new JButton("New Game");
    JButton buttonEnd = new JButton("Exit");
    Map map;
    SettingsWindow settings;

    GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HIGHT);
        setResizable(false);
        setTitle("Крестики нолики LTD");

        JPanel panelBottom = new JPanel(new GridLayout(1, 2));
        panelBottom.add(buttonStart);
        panelBottom.add(buttonEnd);
        settings = new SettingsWindow(this);
        map = new Map();

        add(panelBottom, BorderLayout.SOUTH);
        add(map);

        buttonEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });

        setVisible(true);
    }

    public void startNewGame(int mode, int fSzX, int fSzY, int length) {
        map.startNewGame(mode, fSzX, fSzY, length);
    }
}