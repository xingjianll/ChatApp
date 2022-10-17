package Controller;

import Communication.ComManager;
import Communication.ComUser;
import Model.ChatMessage;
import Model.ClientModel;
import View.InputListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Controller.ChatConstants.CHATS_SPR;
import static Controller.ChatConstants.FRIEND_SPR;

public class Controller implements InputListener, ComUser
{
    ScreenView view;
    ClientModel model;
    ComManager comManager;

    public Controller(ScreenView view, ClientModel model, ComManager comManager) throws IOException {
        this.view = view;
        this.model = model;
        this.comManager = comManager;

        view.setInputListener(this);

        comManager.init(2200, this);
        view.init();
    }

    public void setProfile(String name, int rating, String description)
    {
        model.setProfile(name, rating, description);
    }

    public void setState(int state)
    {
        model.setState(state);
    }
    public void displayMainPage()
    {
        view.displayMainPage(model.getUserName(), model.getFriendsList());
    }

    public void displayChat(String name)
    {
         ArrayList<ChatMessage> toReturn = new ArrayList<>(model.getChats().get(name));
        view.displayChatPage(toReturn);
    }





    public void ActionPerformed(String input)
    {
        if (model.getState() == 0)
        {
            comManager.send("192.168.2.11", 4396, input);
            model.setProfile(input, 3, "Who is this dude?");
        }

        for (String element : model.getFriendsList())
        {
            if (Objects.equals(input, element))
            {
                setState(2);
            }
            break;
        }

        if (Objects.equals(input, "main screen"))
        {
            setState(1);
            displayMainPage();
        }

        if (model.getState() == 2)
        {
            comManager.send("192.168.2.11", 4396, input);
        }
    }

    @Override
    public void onMsg(String msg)
    {
        int sep1 = msg.indexOf(ChatConstants.SPR);
        int sep2 = msg.indexOf(ChatConstants.SPR, sep1+1);
        int sep3 = msg.indexOf(ChatConstants.SPR, sep2+1);

        int msgId = Integer.parseInt(msg.substring(0, sep1));
        String content = msg.substring(sep3+1);

        if (msgId == 0)
        {
            if (content.equals("fail"))
            {
                System.out.println("Login failed, please enter your username again.");
            }
            else
            {
                //example of a login message from the server "Andy/Kevin/Marcus/David#"msg1"$"msg2"$"msg1"$"msg2"
                int a1 = content.indexOf(ChatConstants.LOGIN_SPR);
                String friendNames = content.substring(0, sep1);
                String allChatMsg = content.substring(sep1+1);

                int previousSep = 0;
                for (int i = 0; i < friendNames.length(); i++)
                {
                    char c = friendNames.charAt(i);
                    //Process char
                    if (c == FRIEND_SPR)
                    {
                        String friendName = friendNames.substring(previousSep, i);
                        model.addFriend(model.createFriend(friendName));
                        previousSep = i;
                    }
                }

                for (int i = 0; i < allChatMsg.length(); i++)
                {
                    char c = allChatMsg.charAt(i);
                    //Process char
                    if (c == CHATS_SPR)
                    {
                        String chat = friendNames.substring(previousSep, i);
                        model.addChat("server", new ChatMessage(chat, "someone"));
                        previousSep = i;
                    }
                }

            }
        }
        else if (msgId == 2)
        {
            String peerUser = msg.substring(sep1 + 1, sep2);
            model.addNewMessage(peerUser, content);
            if (model.getState() == 2)
            {
                view.updateChat(content);
            }
        }
    }


}







