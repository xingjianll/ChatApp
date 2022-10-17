package Communication;


import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Controller.IfCommunication;
import Communication.DataStructures.Data;
import Communication.DataStructures.Slice;
import Communication.DataStructures.SlicePacket;

public class ComManager extends Thread implements IfCommunication
{
    public static DatagramSocket socket;
    private ComUser user;

    private Sender sender;
    private Slicer slicer;
    private PacketHandler handler;
    private Matrix matrix;
    private Constructor constructor;
    private Receiver receiver;
    private TimeOut timeOut;

    private LinkedList<Data> msgList;
    private LinkedList<SlicePacket> slicePacketList;
    private ArrayList<SlicePacket> resendPacketList;
    private ArrayList<Slice> sliceList;
    private ArrayList<ArrayList<Slice>> sliceMatrix;
//	private ArrayList<SlicePacket> tOutSlicePacketList = new ArrayList<SlicePacket>();

    String destinationAddr;

    public ComManager()
    {
        msgList = new LinkedList<Data>();
        slicePacketList = new LinkedList<SlicePacket>();
        sliceList = new ArrayList<Slice>();
        sliceMatrix = new ArrayList<ArrayList<Slice>>();
    }

    public void init(int port, ComUser user)
    {
        try
        {
            //socket to receive data packet
            socket = new DatagramSocket(port);
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }

        this.user = user;

        receiver = new Receiver(sliceList, slicePacketList);
        receiver.start();

        matrix = new Matrix(sliceList, sliceMatrix);
        matrix.start();

        constructor = new Constructor(sliceMatrix, user);
        constructor.start();

        sender = new Sender(msgList);

        slicer = new Slicer(msgList, slicePacketList);
        slicer.start();

        handler = new PacketHandler(slicePacketList, resendPacketList);
        handler.start();

        timeOut = new TimeOut(slicePacketList, sliceMatrix);
    }

    public void send(String addr, int peerPort, String msg)
    {
        sender.send(addr, peerPort, msg);
    }

}
