package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	
	InetAddress serverIP;
	int welcomePort = 65500;
	ServerSocket serverSocket;
	ArrayList<Entry> log;
	
	
	public Server() throws IOException {
		log = new ArrayList<Entry>();
		serverIP = InetAddress.getLocalHost();
		serverSocket = new ServerSocket(welcomePort);
		while (true) {
			Socket client = serverSocket.accept();
			new ServerThread(client).start();
		}
	}

}
