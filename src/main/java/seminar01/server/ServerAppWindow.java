package seminar01.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerAppWindow extends JFrame {
    private int WINDOW_HEIGHT = 400;
    private int WINDOW_WIDTH = 450;
    private final ServerApp serverApp;
    JTextArea textArea;


    public ServerAppWindow(ServerApp serverApp) {
        this.serverApp = serverApp;
        setTitle("Cool Server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));

        JPanel btnPanel = new JPanel(new GridLayout(1, 0, 3, 3));

        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");

        btnPanel.add(startBtn);
        btnPanel.add(stopBtn);
        mainPanel.add(btnPanel);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane);

        add(mainPanel);

        setVisible(true);

        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverApp.isRunning) {
                    serverApp.isRunning = !serverApp.isRunning;
                    serverApp.log("Нажата кнопка остановки сервера");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    serverApp.log("Внимание! Сервер остановлен!");
                }
            }
        });

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverApp.isRunning) {
                    serverApp.isRunning = !serverApp.isRunning;
                    serverApp.log("Нажата кнопка запуска сервера");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    serverApp.log("Внимание! Сервер запущен!");
                }
            }
        });
    }

    public void printMessage(String message) {
        textArea.append(message);
    }
}
