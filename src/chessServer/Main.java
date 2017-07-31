package chessServer;

public class Main {
	
	private static ChessBoard board;

	public static void main(String[] args) {
		ServerNetwork server = new ServerNetwork();
		server.open();
		
		board = new ChessBoard();

	}

}
