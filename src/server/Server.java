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
		serverIP = InetAddress.getLocalHost(); // serverIP is generated using the LocalHostIP
		serverSocket = new ServerSocket(0); // ServerSocket(0) allocates an unused port num to serverSocket
		serverPort = serverSocket.getLocalPort(); // Retrieves allocated portnum so that it can be given to clients
		
		while (true) {
			Socket client = serverSocket.accept();
			ServerThread serverThread = new ServerThread(client, log); // pass client socket and log to thread
			serverThread.start();
			
			System.out.println("Printing Current Log: ");
			if (log.size() > 0) // prints each Entry if log is not empty
				for (Entry ent : log) {
					ent.printEntry();
				}
			else
				System.out.println("Current Log is Empty"); // prints that log is empty if it is empty
		}
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}

}
