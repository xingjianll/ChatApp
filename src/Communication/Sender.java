package Communication;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;

import Communication.DataStructures.Data;

public class Sender
{
	private LinkedList<Data> msgList;

	
	public Sender(LinkedList<Data> msgList2)
	{
		this.msgList = msgList2;
	}
	
	public void send(String peerIP, int peerPort, String msg)
	{
		Data data = new Data();
		data.ip = peerIP;
		data.port = peerPort;
		data.data = msg.getBytes(StandardCharsets.UTF_8);
		
		System.out.println("========================");
		System.out.println("send message length: " + data.data.length);
		
		synchronized(msgList)
		{
			msgList.add(data);
		}
	}
}
