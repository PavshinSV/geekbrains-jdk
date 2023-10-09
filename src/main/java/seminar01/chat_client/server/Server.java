package seminar01.chat_client.server;

public class Server {
    private String ip;
    private Integer port;
    private boolean isRunning=false;

    //region INITIALIZED
    public Server(){
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    //endregion

    public void run(){
        if (ip==null){
            ip = "127.0.0.1";
        }
        if (port==null){
            port=50863;
        }
        isRunning=true;
    }


}
