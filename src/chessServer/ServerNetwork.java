package chessServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerNetwork {
	
	private ArrayList<Socket> connections = new ArrayList<Socket>();
	
	private final int PORT = 444;
	
	public ServerNetwork(){
		
		
	}
	
	public void open(){
		
		try{
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Starting Server...");
			
			//Thread to listen for clients trying to connect
			Runnable connectionListener = new Runnable(){
				public void run(){
					while(true){
						try{
							Socket newConnection = server.accept();
							connections.add(newConnection);
							
							//Thread to listen for incoming messages from this client
							Runnable messageListener = new Runnable(){
								public void run(){
									while(true){
										receive(newConnection);
									}
								}
							};
							
							new Thread(messageListener).start();
							
						}
						catch(IOException e){
							System.out.println("Cannot connect to client.");
						}
					}
				}
			};
			
			new Thread(connectionListener).start();
			
		}
		catch(Exception e){
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
						System.out.println(input);
						broadcast(input);
					}
				}
			}		
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void send(Socket clientConnection, String output){
		try{
			PrintWriter out = new PrintWriter(clientConnection.getOutputStream());
			out.println(output);
			out.flush();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void broadcast(String output){
		
		for(Socket s : connections){
			send(s, output);
		}
	}

}
