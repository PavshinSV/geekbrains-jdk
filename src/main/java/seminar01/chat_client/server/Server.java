package seminar01.chat_client.server;

import seminar01.chat_client.client.Client;
import seminar01.chat_client.entities.Message;
import seminar01.chat_client.entities.User;
import seminar01.chat_client.Internet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private String ip;
    private Integer port;
    private boolean isRunning = false;
    private List<User> users;

    public Internet internet;

    //region INITIALIZED
    public Server(Internet internet) {
        prepareDir();
        this.internet = internet;
        internet.servers.add(this);
        getUsers();
    }

    private void getUsers() {
        users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("chatDir\\users.txt"))) {
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                String[] buf = tmp.split(";");
                User newUser = new User();
                newUser.setNickName(buf[0]);
                newUser.setHashCode(buf[1]);
                users.add(newUser);
            }
        } catch (FileNotFoundException e) {
            File file = new File("chatDir\\users.txt");
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        users.add(user);
        try (BufferedWriter br = new BufferedWriter(new FileWriter("chatDir\\users.txt", true))) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(user.getNickName()).append(";").append(user.getHashCode()).append("\n");
            br.append(stringBuffer);
            new File("chatDir\\" + user.getNickName()).mkdir();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void run() {
        if (ip == null) {
            ip = "127.0.0.1";
        }
        if (port == null) {
            port = 50863;
        }
        isRunning = true;
    }


    public boolean login(Client client) {
        for (User user : users) {
            if (user.getNickName().equals(client.getUserName())) {
                String hash = getHash(client);
                if (hash.equals(user.getHashCode())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        User newUser = new User();
        newUser.setNickName(client.getUserName());
        newUser.setHashCode(getHash(client));
        addUser(newUser);
        return true;
    }

    private String getHash(Client client) {
        String hash = client.getUserName() + client.getUserPassword();
        return String.valueOf(hash.hashCode());
    }

    private void prepareDir() {
        if (!(new File("chatDir").exists())) {
            new File("chatDir").mkdir();
        }
    }

    public void getMessages(Client client) {
        if (client.isLogin()) {
            try (BufferedReader br = new BufferedReader(new FileReader("chatDir\\" + client.getUserName() + "\\messages.txt"))) {
                String tmp = "";
                while ((tmp = br.readLine()) != null) {
                    client.addMessage(tmp);
                }
            } catch (FileNotFoundException e) {
                try {
                    new File("chatDir\\" + client.getUserName() + "\\messages.txt").createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean sendMessage(Client client, String message) {
        if (client.isLogin()) {
            try (BufferedWriter br = new BufferedWriter(new FileWriter("chatDir\\" + client.getUserName() + "\\messages.txt", true))) {
                br.append(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}
