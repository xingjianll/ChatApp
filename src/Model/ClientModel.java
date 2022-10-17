package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientModel
{
    final int LOGINPAGE = 0;
    final int MAINPAGE = 1;
    final int CHATPAGE = 2;
    private int state;
    private Profile profile;
    private Settings settings;
    private ArrayList<Friend> friendsList;
    private HashMap<String, ArrayList<ChatMessage>> chats;

    public ClientModel()
    {
        profile = new Profile("");
        this.friendsList = new ArrayList<>();
        this.chats = new HashMap<>();
        state = 0;
    }

    public Friend createFriend(String name)
    {
        return new Friend(name);
    }

    public void addFriend(Friend friend)
    {
        friendsList.add(friend);
    }

    public void addChat(String friend, ChatMessage msg)
    {
        chats.get(friend).add(msg);
    }

    public void setProfile(String name, int rating, String description)
    {
        profile.userName = name;
        profile.description = description;
        profile.rating = rating;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public void addNewMessage(String friend, String content)
    {
        ArrayList<ChatMessage> lst = chats.get(friend);
        ChatMessage msg = new ChatMessage(content, friend);
        lst.add(msg);
    }

    public String getUserName()
    {
        return profile.userName;
    }

    public ArrayList<String> getFriendsList()
    {
        ArrayList<String> toReturn = new ArrayList<>();
        for (int i = 0; i < friendsList.size(); i++)
        {
            String friendName = friendsList.get(i).profile.userName;
            toReturn.add(friendName);
        }
        return toReturn;
    }

    public HashMap<String, ArrayList<ChatMessage>> getChats()
    {
        return new HashMap<String, ArrayList<ChatMessage>>(chats);
    }

    public int getState()
    {
        return state;
    }
    public void loadNewMessage(Friend friend, ChatMessage message)
    {

    }
}

class Profile
{
    public String userName;
    public String description;
    public int rating;

    public Profile(String name)
    {
        userName = name;
    }

//    public void setUsername(String name)
//    {
//        userName = name;
//    }
//
//    public void setRating(int rating)
//    {}
//
//    public void setDescription(String des)
//    {}
//
//    public String getUsername()
//    {
//        return userName;
//    }
//
//    public int getRating()
//    {
//        return rating;
//    }
//
//    public String getDescription()
//    {
//        return description;
//    }
}

class Settings
{
}

class Friend
{
    Profile profile;

    public Friend(String name)
    {
        profile = new Profile(name);
    }
}



