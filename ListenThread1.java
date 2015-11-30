package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ListenThread1 extends Thread {	
	private Socket serverConnection;
	private BufferedReader reader;
	public ListenThread1() throws UnknownHostException, IOException{
		serverConnection = new Socket("localhost", 1002);
		reader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
		
	}

	
	public void run(){
		while(true) {
			try {
				String msg = reader.readLine();
				ClientGUI1.textArea.append(msg+"\n");
			}
			catch(IOException e) {
				System.out.println("Server has closed the connection: " + e);
				break;
			}
		}
	}

}
