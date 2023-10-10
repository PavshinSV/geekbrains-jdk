package seminar01.chat_client.client;

import seminar01.chat_client.Internet;
import seminar01.chat_client.entities.Message;
import seminar01.chat_client.server.Server;

import java.util.ArrayList;
import java.util.List;

public class Client {
    Server server;
    Internet internet;
    private ClientWindow clientWindow;
    private String serverIp;
    private Integer serverPort;
    private String userName;
    private String userPassword;
    private boolean isLogin=false;

    private List<String> messages=new ArrayList<>();

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public Client(Internet internet) {
        this.internet = internet;
        serverIp = "127.0.0.1";
        serverPort = 50863;
        userName="Anonymous";
        userPassword="";
    }

    public void run() {
        clientWindow = new ClientWindow(this);
    }

    public ClientWindow getClientWindow() {
        return clientWindow;
    }

    public void setClientWindow(ClientWindow clientWindow) {
        this.clientWindow = clientWindow;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean logIn(){
        try {
            server = internet.logIn(this);
            return server.login(this);
        } catch (RuntimeException e){
            return false;
        }
    }

    public List<String> getMessages(){
        server.getMessages(this);
        return messages;
    }

    public void sendMessage(String message) {
        server.sendMessage(this,message);
    }
}