package Model;

public class ChatMessage {
    public int date;
    public int time;
    public int type;
    public String data;
    public String sentBy;

    public ChatMessage(String data, String sentBy)
    {
        this.data = data;
        this.sentBy = sentBy;
    }
}
