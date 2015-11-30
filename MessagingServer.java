package clientServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class MessagingServer {

	public static void main(String [] args) throws IOException {

		// create server sockets
		ServerSocket inputSocket1 = new ServerSocket(1000);
		ServerSocket inputSocket2 = new ServerSocket(1001);
		ServerSocket listenSocket1 = new ServerSocket(1002);
		ServerSocket listenSocket2 = new ServerSocket(1003);
		
		// Wait for the clients and chat screens to attempt to start a connection
		Socket s1 = inputSocket1.accept();
		System.out.println("Client1 has connected");
		Socket s2 = inputSocket2.accept();
		System.out.println("Client2 has connected");
		Socket s3 = listenSocket1.accept();
		System.out.println("Client1 ready");
		Socket s4 = listenSocket2.accept();
		System.out.println("Client2 ready");
		
		PrintWriter pw1 = new PrintWriter(s3.getOutputStream());
		PrintWriter pw2 = new PrintWriter(s4.getOutputStream());
		
		// create BufferedReaders
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));

		//create nicknames for the clients
		String nick1 = "Client1";
		String nick2 = "Client2";
		
		//create an encode
		Encode encode = new Encode();
		while(true) {
			
			// check if there is data 
			if(reader1.ready()) {
				//generate a timestamp and format it
				GregorianCalendar date = new GregorianCalendar();
				String time = "["+String.format("%02d",date.get(Calendar.HOUR_OF_DAY))+":"+String.format("%02d",date.get(Calendar.MINUTE))+":"+String.format("%02d",date.get(Calendar.SECOND))+"]";
				
				//decrypt the message
				String message = encode.decrypt(reader1.readLine());
				//allows client to change nickname if message starts with '/nick'
				if (message.contains(" ") && message.substring(0, message.indexOf(" ")).equals("/nick")){
					String oldnick1 = nick1;
					nick1 = message.substring(message.indexOf(" ")+1, message.length());
					pw1.println("**"+oldnick1+" is now known as "+nick1+"**");
					pw1.flush();
					pw2.println("**"+oldnick1+" is now known as "+nick1+"**");
					pw2.flush();
				}
				// send text from client1 to both consoles 
				else if(!message.equals("$")) {
					pw1.println(time+" <"+nick1+"> "+message);
					pw1.flush();
					pw2.println(time+" <"+nick1+"> "+message);
					pw2.flush();
				}
				else {
					break;
				}
			}
			//code for second client
			else if(reader2.ready()) {
				GregorianCalendar date = new GregorianCalendar();
				String time = "["+String.format("%02d",date.get(Calendar.HOUR_OF_DAY))+":"+String.format("%02d",date.get(Calendar.MINUTE))+":"+String.format("%02d",date.get(Calendar.SECOND))+"]";
				
				String message2 = encode.decrypt(reader2.readLine());
				
				if (message2.contains(" ") && message2.substring(0, message2.indexOf(" ")).equals("/nick")){
					String oldnick2 = nick2;
					nick2 = message2.substring(message2.indexOf(" ")+1, message2.length());
					pw1.println("**"+oldnick2+" is now known as "+nick2+"**");
					pw1.flush();
					pw2.println("**"+oldnick2+" is now known as "+nick2+"**");
					pw2.flush();
				}
				else if(!message2.equals("$")) {
					pw2.println(time+" <"+nick2+"> "+message2);
					pw2.flush();
					pw1.println(time+" <"+nick2+"> "+message2);
					pw1.flush();
				}
					
				else {
					break;
				}
			}
		}
	
		// close connection
		System.out.println("Client closed the Connection.");
		s1.close();
		s2.close();
	}
}