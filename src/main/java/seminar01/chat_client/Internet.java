package seminar01.chat_client;

import seminar01.chat_client.client.Client;
import seminar01.chat_client.server.Server;

import java.util.ArrayList;
import java.util.List;

public class Internet {
    public List<Server> servers;

    {
        servers = new ArrayList<>();
    }

    public void addServer(Server server) {
        servers.add(server);
    }

    public void removeServer(Server server) {
        servers.remove(server);
    }

    public Server logIn(Client client) {
        for (Server server : servers) {
            if (server.getIp().equals(client.getServerIp()) && server.getPort() == client.getServerPort()) {
                return server;
            }
        }
        throw new RuntimeException("502");
    }
}