package chessClient;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientNetwork {
	
	private Socket serverConnection;
	private final int PORT = 444;
	private final String HOST = "localhost";
	
	public void connect(){
		try{
			serverConnection = new Socket(HOST, PORT);
			System.out.println("Connected to " + HOST);
			
			//Thread to listen for incoming messages from this client
			Runnable messageListener = new Runnable(){
				public void run(){
					while(true){
						receive(serverConnection);
					}
				}
			};
			
			new Thread(messageListener).start();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void receive(Socket connection){
		try{
			Scanner inputScanner = new Scanner(connection.getInputStream());
			
			while(true){
				if(inputScanner.hasNext()){
					String input = inputScanner.nextLine();
					if(!input.equals("")){
						MainWindow.displayChat(input);
						System.out.println(input);
					}
				}
			}		
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void send(String output){
		try{
			PrintWriter out = new PrintWriter(serverConnection.getOutputStream());
			out.println(output);
			out.flush();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}

}
