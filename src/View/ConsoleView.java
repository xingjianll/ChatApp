package View;

import Controller.ScreenView;
import Model.ChatMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleView implements ScreenView
{
    InputListener listener;

    public ConsoleView()
    {
    }

    @Override
    public void init() throws IOException
    {
        System.out.println("Application initialized.");
        displayLoginPage();
            while (true)
            {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                // Reading data using readLine
                String input = reader.readLine();

                listener.ActionPerformed(input);
            }
    }

    @Override
    public void goback() {

    }

    @Override
    public void updateChat(String message)
    {
        System.out.println("chat");
    }

    @Override
    public void displayMainPage(String userName, ArrayList<String> chatList)
    {
        System.out.println("++++++++++++++++++++ " + userName + " ++++++++++++++++++++ ");
        System.out.println("Friends:");
        for (String element : chatList)
        {
            System.out.println(element);
        }
        System.out.println("++++++++++++++++++++ " + userName + " ++++++++++++++++++++ ");

    }

    public void displayLoginPage()
    {
        System.out.println("please enter username: ");
    }

    @Override
    public void displayChatPage(ArrayList<ChatMessage> chatList)
    {
        System.out.println("++++++++++++++++++++ Conversation with " + chatList.get(0).sentBy + " ++++++++++++++++++++ ");

        for (ChatMessage element : chatList)
        {
            System.out.println(element.sentBy + ": " + element.data);
        }
    }

    @Override
    public void setInputListener(InputListener listener) {
        this.listener = listener;
    }
}
