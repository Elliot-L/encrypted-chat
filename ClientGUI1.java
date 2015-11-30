package clientServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientGUI1{
	//instance variables
	private JFrame frame; 
	private JTextField textField;
	static JTextArea textArea;
	private Socket socket;
	private PrintWriter writer;
	 
	//Launch the application.
	public static void main(String[] args) throws UnknownHostException, IOException {

		ListenThread1 listen = new ListenThread1();
		listen.start();
		
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI1 window = new ClientGUI1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//constructor for the GUI
	public ClientGUI1() throws UnknownHostException, IOException {
		//initialize the socket and print writer 
		socket = new Socket("localhost", 1000);
		writer = new PrintWriter(socket.getOutputStream());
		
		frame = new JFrame("Encrytpted Chat");
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				String text = textField.getText();
		        sendMessage(text);
		        textField.setText("");
			}
		});
		frame.getContentPane().add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
	}
	
	//method to encrypt message and send it to the server
	public void sendMessage(String message){
		// encrypt message
		Encode encode = new Encode();
		String encryptedMessage = encode.encrypt(message);
					
		//using the writer to send the message
		if(!encryptedMessage.equals("")){
			writer.println(encryptedMessage);
			writer.flush();
		}
	}
	
}