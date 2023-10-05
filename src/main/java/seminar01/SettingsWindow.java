package seminar01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private final int WINDOW_HIGHT = 230;
    private final int WINDOW_WIDTH = 350;
    private GameWindow gameWindow;

    JButton btnStart = new JButton("Start new Game!!!");

    SettingsWindow(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        setLocationRelativeTo(this.gameWindow);
        setSize(WINDOW_WIDTH,WINDOW_HIGHT);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(0,3,3,3);
                setVisible(false);
            }
        });

        add(btnStart);
    }
}