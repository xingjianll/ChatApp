package Controller;

import Model.ChatMessage;
import View.InputListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ScreenView
{
    public void init() throws IOException;
    public void goback();
    public void updateChat(String message);
    public void displayMainPage(String userName, ArrayList<String> chatList);
    public void displayLoginPage();
    public void displayChatPage(ArrayList<ChatMessage> chatList);
    public void setInputListener(InputListener listener);
}
