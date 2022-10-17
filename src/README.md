
The code contained in the src package is my design for the client of a basic messaging system. The system has 4 major compoments:

1. Controller
2. Model
3. View
4. Communication

The Controller is the highest up in the hierarchy, so it depends on all other three classes. The controller is responsible for determining the type of input and make appropriate responses to the inputs, such as updating the view or changing the state of the model. In addition, it can use the Communication component to send messages to the server.

The View component and Communication component are similar in that it each implements an interface defined inside the Controller component (IfCommunication, ScreenView). This allows for greater portability as the implementations can be altered in the future to allow for extension of the program (such as changing the ConsoleView class inside the view component to AppView to create a GUI). 

In addition, these two classes are also responsible for detecting message and user inputs. Once an input is detected, a callback method inside the Controller class is called and the Controller class handles all inputs.

The Model component holds a model of the current state of the chat client, it contains information such as Username, FriendList, as well as the current page that the user sees (i.e. login page, chat, main page etc.).

The Model class has no access to any other components, whereas ConsoleView and ComManager has access to the instance of the controller. However, only the corresponding callback method inside Controller is accessable to these two classes.