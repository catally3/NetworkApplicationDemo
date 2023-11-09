package user;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Client {
   // public static void main(String[] args) {
	public void startClient(){
        try {
            //connect to the server using TCP
            String serverAddress = "123"; // Replace when ready
            int port = 0; // Replace with the server's port number
            Socket socket = new Socket(serverAddress, port); // TCP connection
            
            //set up input and output streams for the socket
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);

            //provide a name for the client during the initial attachment
            outputToServer.println("(Join) Becky 12.123.123.123,50 12.123.123.321,55 "); // (Join) clientName clientIP,clientPort serverIP,serverPort as stated in document

            //wait for the server's acknowledgment for a successful connection
            String serverResponse = inputFromServer.readLine();
            System.out.println("Server: " + serverResponse); //should the client be able to see the server's acknowledgments? 

            //unsure if manually input a hardcoded function, use keyboard scanner, or just auto create random functions
            outputToServer.println("(Calc) 2 + 2"); //sends hard-coded calc request to server

            // (Silly) Perhaps we could use random functions to generate the functions for us (Silly)
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

                //pause for a random interval (required by project)
                try {
                    Thread.sleep(interval * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            //end of (Sillyness)
               
            

            // close the connection
            outputToServer.println("(End) Andy 12.123.123.123,50"); //(End) clientName clientIP,clientPort as stated in document

            // close the streams and the socket
            inputFromServer.close();
            outputToServer.close();
            socket.close();
        } catch (UnknownHostException e1) {
            System.err.println("Failed to connect to host: " + e1.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    //}
	}
}

