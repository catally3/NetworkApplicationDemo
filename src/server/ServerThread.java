package server;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	
	private Socket client;
	private static String closeMessage = "(End)";
	
	public ServerThread(Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		// use String line = reader.readLine() to read line of text from client
		String line = reader.readLine();
		
		PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
		// use writer.println("output string") to print line of text to client
		while (!line.contains(closeMessage)) {
			// process current line
			// get new line
			line = reader.readLine();
		}
		
			client.close();
		return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
