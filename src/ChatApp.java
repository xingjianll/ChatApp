import Communication.ComManager;
import Communication.ComUser;
import Controller.Controller;
import Controller.IfCommunication;
import Model.ClientModel;
import Util.IfTimerUser;
import Util.Timer;
import View.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ChatApp
{
    public static void main(String[] args) throws IOException
    {
        ConsoleView view = new ConsoleView();
        ComManager manager = new ComManager();
        ClientModel model = new ClientModel();
        Controller controller = new Controller(view, model, manager);

    }
}
