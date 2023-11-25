package user;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Client {
   
	//String clientName;
	InetAddress clientIP;
	InetAddress serverAddress;
	int serverPort = 65500;
	
	public void startClient(String clientName){
        try {
            //connect to the server using TCP
            serverAddress = InetAddress.getByName("server"); // Address of the server
            Socket socket = new Socket(serverAddress, serverPort); // TCP connection
            
            //set up input and output streams for the socket
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);

            //provide a name for the client during the initial attachment
            clientIP = InetAddress.getLocalHost();
            outputToServer.println("(Join) " + clientName); // (Join) clientName clientIP,clientPort serverIP,serverPort as stated in document

            //wait for the server's acknowledgment for a successful connection
            String serverResponse = inputFromServer.readLine();
            
            System.out.println("Server: " + serverResponse); 
            
            //Random functions to generate the functions
            Random random = new Random();

            for (int i = 0; i < 5; i++) {
                //generate random numbers between 0 and 99
                int num1 = random.nextInt(100);
                int num2 = random.nextInt(100);

                //an array of math operators for randomness
                String[] operators = {"+", "-", "*", "/"};

                //generate a random operator from the array
                String operator = operators[random.nextInt(operators.length)];

                //create the random equation
                String equation = num1 + " " + operator + " " + num2;

                //sends the random equation to the server
                outputToServer.println("(Calc) " + equation);

                //generate a random interval between 1 and 5 seconds
                int interval = random.nextInt(5) + 1;
                
               //receive and print result from the server
                String serverResult;
                serverResult = inputFromServer.readLine();
                System.out.println("Result from server: " + serverResult);
                
                //pause for a random interval (required by project)
                try {
                    Thread.sleep(interval * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           //end of random equation generator                 
            
            // close the connection
            outputToServer.println("(End) " + clientName); //(End) clientName as stated in document

            // close the streams and the socket
            inputFromServer.close();
            outputToServer.close();
            socket.close();
        } catch (UnknownHostException e1) {
            System.err.println("Failed to connect to host: " + e1.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
	}
}

