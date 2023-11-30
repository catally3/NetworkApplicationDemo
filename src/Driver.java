import user.Client;
import server.Server;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
    	Server server = new Server(); //start the server
    	
    	Thread thread0 = new Thread(() -> server.MakeServer());
    	thread0.start();
    	
    	
        // Create instances of the Client class with different names
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        Thread.sleep(100);
        
        client1.serverPort = server.serverPort; client1.serverAddress = server.serverIP;
        client2.serverPort = server.serverPort; client2.serverAddress = server.serverIP;
        client3.serverPort = server.serverPort; client3.serverAddress = server.serverIP;

        
        client1.startClient("Becky"); //runs the first client
        //After the first client is completed, two threads will be created to create 2 clients that operate at the same time
        
        // Create two threads for 2 clients to run simultaneously as required by project
        Thread thread1 = new Thread(() -> client2.startClient("Alice"));
        Thread.sleep(1000);
        Thread thread2 = new Thread(() -> client3.startClient("Bob"));

        // Start the two threads
        thread1.start();
        thread2.start();

        // Wait for all threads to finish
        try {
        	thread0.join();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
