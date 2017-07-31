package chessServer;



import static chessClient.ChessEnums.PieceType;
import static chessClient.ChessEnums.PieceColor;
import static chessClient.ChessEnums.PieceColor.*;


public class ChessPiece {

	private PieceType type;
	private PieceColor color;
	private int location;
	private boolean hasMoved;
	
	
	public ChessPiece(PieceType type, int location, PieceColor color){
		this.type = type;
		this.location = location;
		this.color = color;
		hasMoved = false;
	}
	
	public PieceType getType(){
		return type;
	}
	
	public int getLocation(){
		return location;
	}
	
	public PieceColor getColor(){
		return color;
	}
	
	public boolean hasMoved(){
		return hasMoved;
	}
	
	public void setType(PieceType type){
		this.type = type;
	}
	
	public void setLocation(int location){
		this.location = location;
	}
	
	public void setColor(PieceColor color){
		this.color = color;
	}
	
	public void setHasMoved(boolean b){
		hasMoved = b;
	}
	
	public PieceColor oppositeColor(){
		if(color == WHITE)
			return BLACK;
		if(color == BLACK)
			return WHITE;
					
		return NONE;
	}
		
	
}
