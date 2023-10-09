package seminar01.chat_client;

import seminar01.chat_client.client.Client;
import seminar01.chat_client.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client();

        server.run();
        client.run();
    }
}
