package seminar01.chat_client.entities;

public class Message {
    public String chatWithUserName;
    public String message;
    public boolean direction;//true - к chatWithUserName, false - от chatWithUserName;

    public Message(String chatWithUserName, String message, boolean direction) {
        this.chatWithUserName = chatWithUserName;
        this.message = message;
        this.direction = direction;
    }
}