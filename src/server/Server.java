package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	
	InetAddress serverIP;
	int welcomePort = 65500;
	ServerSocket serverSocket;
	ArrayList<Entry> log;
	
	public void MakeServer() {
		try {
		log = new ArrayList<Entry>();
		serverIP = InetAddress.getLocalHost();
		serverSocket = new ServerSocket(welcomePort);
		while (true) {
			Socket client = serverSocket.accept();
			new ServerThread(client).start(log);
			System.out.println("Printing Current Log: ");
			for (Entry ent : log) {
				ent.printEntry();
			}
		}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
