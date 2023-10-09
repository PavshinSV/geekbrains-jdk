package seminar01.chat_client.client;

public class Client {
    private ClientWindow clientWindow;
    private String serverIp;
    private Integer serverPort;
    private String userName;
    private String userPassword;

    public Client() {
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
}