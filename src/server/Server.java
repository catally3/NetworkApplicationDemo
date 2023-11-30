package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	
	public int serverPort;
	public InetAddress serverIP;
	ServerSocket serverSocket;
	public ArrayList<Entry> log;
	
	public void MakeServer() {
		try {
		log = new ArrayList<Entry>();
		serverIP = InetAddress.getLocalHost();
		serverSocket = new ServerSocket(0);
		serverPort = serverSocket.getLocalPort();
		
		while (true) {
			Socket client = serverSocket.accept();
			ServerThread serverThread = new ServerThread(client, log);
			serverThread.start();
			System.out.println("Printing Current Log: ");
			if (log.size() > 0)
				for (Entry ent : log) {
					ent.printEntry();
				}
			else
				System.out.println("Current Log is Empty");
		}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}

}
