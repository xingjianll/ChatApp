package Controller;

import Communication.ComUser;

import java.net.InetAddress;

public interface IfCommunication
{
    public void init(int port, ComUser user);
    public void send(String address, int peerPort, String msg);

}
