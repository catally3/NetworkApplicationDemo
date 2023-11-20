package server;
import java.time.LocalTime; 

public class Entry {

	private int clientNum;
	private String clientName;
	private int requestNum;
	private LocalTime time;
	
	public int getClientNum() {
		return clientNum;
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public int getRequestNum() {
		return requestNum;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	// do not pass requestNum, int seconds should be current time in seconds (1am would be 360)
	public Entry(int clientNum, String clientName, LocalTime time) {
		this.clientNum = clientNum;
		this.clientName = clientName;
		this.requestNum = 0;
		this.time = time;
	}
	
	// do not use default constructor in final program, strictly for testing/debugging
	public Entry() {
		this(0, "defaultName", java.time.LocalTime.now());
	}
	
	// Increments requestNum by 1 and returns new requestNum
	public int updateRequestNum() {
		requestNum++;
		return requestNum;
	}
	
	// Input current time in seconds to update Entry.seconds to total elapsed time
	public void updateSeconds() {
		this.time = java.time.LocalTime.from(time);
	}
	
	public void printEntry() {
		// should print formatted values for this Entry to console
		System.out.println(clientNum + ", " + clientName + ", " + requestNum + ", " + time);
	}

}
