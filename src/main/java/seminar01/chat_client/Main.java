package seminar01.chat_client;

import seminar01.chat_client.client.Client;
import seminar01.chat_client.server.Server;

public class Main {
    public static void main(String[] args) {
        Internet internet = new Internet();
        Server server = new Server(internet);
        Client client = new Client(internet);

        server.run();
        client.run();
    }
}
