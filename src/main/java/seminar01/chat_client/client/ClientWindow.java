package seminar01.chat_client.client;

import seminar01.chat_client.entities.Message;
import seminar01.chat_client.entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class ClientWindow extends JFrame {
    private int CLIENT_WINDOW_HEIGHT = 400;
    private int CLIENT_WINDOW_WIDTH = 600;

    private Client client;
    private boolean isLogin = false;
    JTextArea chatArea;

    public ClientWindow(Client client) {
        this.client = client;
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

        JLabel loginLabel = new JLabel("Please logIn!");
        settings.add(loginLabel);

        add(settings, BorderLayout.NORTH);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.setUserName(userNickname.getText());
                char[] pass = userPassword.getPassword();
                String password = "";
                for (int i = 0; i < pass.length; i++) {
                    password += pass[i];
                }
                client.setUserPassword(password);
                client.setServerIp(serverIp.getText());
                client.setServerPort(Integer.valueOf(serverPort.getText()));
                if (client.logIn()) {
                    isLogin = true;
                    client.setLogin(true);
                    loginLabel.setForeground(Color.GREEN);
                    loginLabel.setText("Login - Success!!");
                    getMessages();
                } else {
                    loginLabel.setForeground(Color.RED);
                    loginLabel.setText("Login - Failure!!");
                    isLogin = false;
                    client.setLogin(false);
                }
            }
        });
        //endregion

        //region Клиентская часть
        JPanel leftpanel = new JPanel();
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
        leftpanel.setBackground(Color.GREEN);
        JList contactList = new JList<String>();
        contactList.setLayoutOrientation(JList.VERTICAL);
        contactList.setBackground(Color.GREEN);
        contactList.setListData(new String[]{"Sergey", "Василёк", "Sne]|[ana"});
        //JScrollPane leftScroll = new JScrollPane();
        //leftScroll.setBackground(Color.GREEN);
        leftpanel.add(new JLabel("Список контактов"));
        //leftScroll.add(contactList);
        leftpanel.add(contactList);
        add(leftpanel, BorderLayout.WEST);

        chatArea = new JTextArea();
        add(chatArea, BorderLayout.CENTER);

        JPanel botom = new JPanel();
        botom.setLayout(new BoxLayout(botom, BoxLayout.X_AXIS));
        JTextField messageTextField = new JTextField();
        messageTextField.setSize(100, 15);
        messageTextField.setBackground(Color.CYAN);
        JButton sendBtn = new JButton("Send");

        botom.add(messageTextField);
        botom.add(sendBtn);
        add(botom, BorderLayout.SOUTH);
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client.server != null) {
                    String message = messageTextField.getText()+"\n";
                    chatArea.append(message);
                    client.sendMessage(message);
                    messageTextField.setText("");
                }
                messageTextField.setText("");
            }
        });


        //endregion

        setVisible(true);
    }

    private void getMessages() {

        if (!isLogin) {
            return;
        }
        for (String message : client.getMessages()) {
            chatArea.append(message);
        }
    }
}
