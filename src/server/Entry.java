package server;

public class Entry {

	int clientNum;
	String clientName;
	String clientIP;
	int clientPortNum;
	int requestNum;
	int seconds;
	
	public int getClientNum() {
		return clientNum;
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public String getClientIP() {
		return clientIP;
	}
	
	public int getClientPortNum() {
		return clientPortNum;
	}
	
	public int getRequestNum() {
		return requestNum;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	// do not pass requestNum, int seconds should be current time in seconds (1am would be 360)
	public Entry(int clientNum, String clientName, String clientIP, int clientPortNum, int seconds) {
		this.clientNum = clientNum;
		this.clientName = clientName;
		this.clientIP = clientIP;
		this.clientPortNum = clientPortNum;
		this.requestNum = 0;
		this.seconds = seconds;
	}
	
	// do not use default constructor in final program, strictly for testing/debugging
	public Entry() {
		this(0, "defaultName", "defaultIP", 0, 0);
	}
	
	// Increments requestNum by 1 and returns new requestNum
	public int updateRequestNum() {
		requestNum++;
		return requestNum;
	}
	
	// Input current time in seconds to update Entry.seconds to total elapsed time
	public void updateSeconds(int currentSeconds) {
		seconds = currentSeconds - seconds;
	}
	
	public void printEntry() {
		// should print formatted values for this Entry to console
		// in or similar to format: clientNum", "clientName", "clientIP","clientPortNum", "requestNum", "seconds"\n"
	}

}
