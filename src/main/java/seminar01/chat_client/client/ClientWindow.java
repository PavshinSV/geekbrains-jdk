package seminar01.chat_client.client;

import seminar01.chat_client.entities.User;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame {
    private int CLIENT_WINDOW_HEIGHT = 400;
    private int CLIENT_WINDOW_WIDTH = 600;

    public ClientWindow(Client client) {
        setTitle("Chat Client Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(CLIENT_WINDOW_WIDTH, CLIENT_WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));


        //region Серверная часть
        JPanel settings = new JPanel(new GridLayout(2, 0, 10, 10));

        JTextField serverIp = new JTextField(client.getServerIp());

        JTextField serverPort = new JTextField(client.getServerPort().toString());

        JLabel nickNameLabel = new JLabel("Nick:");
        nickNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField userNickname = new JTextField(client.getUserName());

        JLabel passwordLabel = new JLabel("password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JPasswordField userPassword = new JPasswordField(client.getUserPassword());
        JButton loginBtn = new JButton("LogIn");

        settings.add(serverIp);

        settings.add(serverPort);

        settings.add(nickNameLabel);
        settings.add(userNickname);

        settings.add(passwordLabel);
        settings.add(userPassword);
        settings.add(loginBtn);
        add(settings, BorderLayout.NORTH);
        //endregion

        //region Клиентская часть
        JPanel mainPanel = new JPanel(new FlowLayout());
        JList contactList = new JList<User>();
        contactList.setBackground(Color.WHITE);
        contactList.setSize(200,CLIENT_WINDOW_HEIGHT);
        mainPanel.add(contactList);

        JLabel separator =new JLabel(" ");
        separator.setBackground(Color.GRAY);
        separator.setSize(5,CLIENT_WINDOW_HEIGHT);
        mainPanel.add(separator);

        JTextArea chatArea =new JTextArea();
        chatArea.setBackground(Color.WHITE);
        chatArea.setSize(200,CLIENT_WINDOW_HEIGHT);
        mainPanel.add(chatArea);

        add(mainPanel);
        //endregion

        setVisible(true);
    }
}
