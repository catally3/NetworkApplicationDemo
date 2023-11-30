package server;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.time.LocalTime;  
import java.time.format.DateTimeFormatter;  

public class ServerThread extends Thread{
	
	private Socket client;
	private static String closeMessage = "(End)";
	public ArrayList<Entry> log;
	
	public ServerThread(Socket client) {
		this.client = client;
	}
	
	public void run(ArrayList<Entry> log) {
		try {
		Entry entry;
		String clientName;
		int clientNum = log.size();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		// use String line = reader.readLine() to read line of text from client
		String line = reader.readLine();
		System.out.println(line);
		
		clientName = line.substring(7);
		
		PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
		// use writer.println("output string") to print line of text to client
		writer.println("(Joined) "+ clientName + " 0" + clientNum);
		
		entry = new Entry(clientNum, clientName, java.time.LocalTime.now());
		log.add(entry);
		line = reader.readLine();
		while (!line.contains(closeMessage)) {
			// process current line
			System.out.println(line);
			log.get(clientNum).updateRequestNum();
			int num1 = Integer.parseInt(line.split(" ", 4)[1]);	// read int from substring
			String operator = line.split(" ", 4)[2]; // read operator from next substring
			int num2 = Integer.parseInt(line.split(" ", 4)[3]); // read int from last substring
			int result = 0; 
			
			if (operator.matches("+")) 
				result = num1 + num2;
			else if (operator.matches("-")) 
				result = num1 - num2;
			else if (operator.matches("*")) 
				result = num1 * num2;
			else if (operator.matches("/")) {
				if (num2 == 0) // preventing divide by zero error
					result = 0;
				else 
					result = num1 / num2;
			}
			
			writer.println("(Result) (0" + clientNum + ", 0" + log.get(clientNum).getRequestNum() + ") " + result);
			
			// get new line
			line = reader.readLine();
		}
			
		client.close();
		log.get(clientNum).updateSeconds();
		
		return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start(ArrayList<Entry> log) {
		this.log = log;
		this.start();	
		return;
	}
}
