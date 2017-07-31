package chessClient;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientNetwork {
	
	private Socket serverConnection;
	private final int PORT = 444;
	private final String HOST = "localhost";
	private ArrayList<String> usernames = new ArrayList<String>();
	
	public void connect(){
		
		usernames.add("bobby");
		usernames.add("sally");
		usernames.add("Richard");
		usernames.add("bobby");
		
		try{
			serverConnection = new Socket(HOST, PORT);
			MainWindow.displayChat("Connected to " + HOST);
			
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
			MainWindow.displayChat("Cannot connect to server...");
		}
	}
	
	public void receive(Socket connection){
		try{
			Scanner inputScanner = new Scanner(connection.getInputStream());
				while(true){
					if(inputScanner.hasNext()){
						
						String input = inputScanner.nextLine();
						System.out.println(input);
						char id = input.charAt(0);
						input = input.substring(1);
						
						switch(id){
						case 'c' : 
							MainWindow.displayChat(input);
							break;
						case 'm' :
							MainWindow.recieveMove(input);
							break;
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
		catch(Exception e){
			MainWindow.displayChat("Not Connected");
		}
	}
	
	public ArrayList<String> getUsernames(){
		return usernames;
	}

}
