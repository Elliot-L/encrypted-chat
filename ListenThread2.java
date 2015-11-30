package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

//this is class that listens for messages
public class ListenThread2 extends Thread {	
	//instance variables
	private Socket serverConnection;
	private BufferedReader reader;
	
	//constructor 
	public ListenThread2() throws UnknownHostException, IOException{
		serverConnection = new Socket("localhost", 1003);
		reader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
		
	}
	
	//method to listen for messages from server and append them to the GUI
	public void run(){
		while(true) {
			try {
				String msg = reader.readLine();
				ClientGUI2.textArea.append(msg+"\n");
			}
			catch(IOException e) {
				System.out.println("Server has closed the connection: " + e);
				break;
			}
		}
	}

}