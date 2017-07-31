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
										int status = receive(newConnection);
										if(status == -1){
											connections.remove(newConnection);
											return;
										}
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
	
	public int receive(Socket connection){
		Scanner inputScanner; 
		try{
			inputScanner = new Scanner(connection.getInputStream());
			
			//while(true){
				//if(inputScanner.hasNext()){
					
					String input = inputScanner.nextLine();
					char id = input.charAt(0);
					input = input.substring(1);
					
					switch(id){
					
					case 'c' :
						broadcastChat(input);
						return 1;
					case 'm' :
						broadcastMove(input);
						return 1;
					}
				//}
			//}		
				
		}
		catch(Exception e){
			try {
				connection.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return -1;
		}
		
		return 1;
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
	
	public void broadcastChat(String input){
		String output = "c" + input;
		broadcast(output);
	}
	
	public void broadcastMove(String input){
		String output = "m" + input;
		broadcast(output);
	}

}
