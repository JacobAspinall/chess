package chessClient;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		ClientNetwork client = new ClientNetwork();
		client.connect();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(client);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
