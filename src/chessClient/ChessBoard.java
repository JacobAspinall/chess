package chessClient;


import static chessClient.ChessEnums.PieceType.*;
import static chessClient.ChessEnums.PieceColor;
import static chessClient.ChessEnums.PieceColor.*;

import java.util.ArrayList;

public class ChessBoard {
	
	private ChessPiece[] board = new ChessPiece[64];
	
	public ChessBoard(){
		board[0] = new ChessPiece(BLACK_ROOK, 0, BLACK);
		board[1] = new ChessPiece(BLACK_KNIGHT, 1, BLACK);
		board[2] = new ChessPiece(BLACK_BISHOP, 2, BLACK);
		board[3] = new ChessPiece(BLACK_QUEEN, 3, BLACK);
		board[4] = new ChessPiece(BLACK_KING, 4, BLACK);
		board[5] = new ChessPiece(BLACK_BISHOP, 5, BLACK);
		board[6] = new ChessPiece(BLACK_KNIGHT, 6, BLACK);
		board[7] = new ChessPiece(BLACK_ROOK, 7, BLACK);
		board[8] = new ChessPiece(BLACK_PAWN, 8, BLACK);
		board[9] = new ChessPiece(BLACK_PAWN, 9, BLACK);
		board[10] = new ChessPiece(BLACK_PAWN, 10, BLACK);
		board[11] = new ChessPiece(BLACK_PAWN, 11, BLACK);
		board[12] = new ChessPiece(BLACK_PAWN, 12, BLACK);
		board[13] = new ChessPiece(BLACK_PAWN, 13, BLACK);
		board[14] = new ChessPiece(BLACK_PAWN, 14, BLACK);
		board[15] = new ChessPiece(BLACK_PAWN, 15, BLACK);
		
		for(int i = 16; i < 48; i++){
			board[i] = new ChessPiece(EMPTY, i, NONE);
		}
		
		board[48] = new ChessPiece(WHITE_PAWN, 48, WHITE);
		board[49] = new ChessPiece(WHITE_PAWN, 49, WHITE);
		board[50] = new ChessPiece(WHITE_PAWN, 50, WHITE);
		board[51] = new ChessPiece(WHITE_PAWN, 51, WHITE);
		board[52] = new ChessPiece(WHITE_PAWN, 52, WHITE);
		board[53] = new ChessPiece(WHITE_PAWN, 53, WHITE);
		board[54] = new ChessPiece(WHITE_PAWN, 54, WHITE);
		board[55] = new ChessPiece(WHITE_PAWN, 55, WHITE);
		board[56] = new ChessPiece(WHITE_ROOK, 56, WHITE);
		board[57] = new ChessPiece(WHITE_KNIGHT, 57, WHITE);
		board[58] = new ChessPiece(WHITE_BISHOP, 58, WHITE);
		board[59] = new ChessPiece(WHITE_QUEEN, 59, WHITE);
		board[60] = new ChessPiece(WHITE_KING, 60, WHITE);
		board[61] = new ChessPiece(WHITE_BISHOP, 61, WHITE);
		board[62] = new ChessPiece(WHITE_KNIGHT, 62, WHITE);
		board[63] = new ChessPiece(WHITE_ROOK, 63, WHITE);
		
	}

	
	public ChessPiece get(int i){
		return board[i];
	}
	
	public static int getRow(int location){
		return location / 8;
	}
	
	public static int getColumn(int location){
		return location % 8;
	}
	
	public PieceColor getPieceColor(int location){
		return board[location].getColor();
	}
	
	public void move(int from, int to){
		ChessPiece temp = board[to];
		board[to] = board[from];
		board[from] = new ChessPiece(EMPTY, from, NONE);
		board[to].setLocation(to);
		board[to].setHasMoved(true);
		
	}
	
	public ArrayList<Integer> getMoves(ChessPiece p){

		if(p.getType() == WHITE_PAWN){
			return getPawnMoves(p);
		}
		else if(p.getType() == WHITE_KNIGHT){
			return getKnightMoves(p);
		}
		else if(p.getType() == WHITE_BISHOP){
			return getBishopMoves(p);	
		}
		else if(p.getType() == WHITE_ROOK){
			return getRookMoves(p);	
		}
		else if(p.getType() == WHITE_QUEEN){
			return getQueenMoves(p);		
		}
		else if(p.getType() == WHITE_KING){
			return getKingMoves(p);
		}
		else if(p.getType() == BLACK_PAWN){
			return getPawnMoves(p);
		}
		else if(p.getType() == BLACK_KNIGHT){
			return getKnightMoves(p);
		}
		else if(p.getType() == BLACK_BISHOP){
			return getBishopMoves(p);
		}
		else if(p.getType() == BLACK_ROOK){
			return getRookMoves(p);
		}
		else if(p.getType() == BLACK_QUEEN){
			return getQueenMoves(p);
		}
		else if(p.getType() == BLACK_KING){
			return getKingMoves(p);
		}
		else{
			return new ArrayList<Integer>();
		}
	}
	
	private ArrayList<Integer> getPawnMoves(ChessPiece p){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		if(p.getType() == WHITE_PAWN){
			if(p.hasMoved() == false && getPieceColor(p.getLocation() - 16) == NONE){
				moves.add(p.getLocation() - 16);
			}
			
				if(getPieceColor(p.getLocation() - 8) == NONE)
					moves.add(p.getLocation() - 8);
				if(getRow(p.getLocation() - 8) == getRow(p.getLocation() - 7) && getPieceColor(p.getLocation() - 7) == p.oppositeColor())
					moves.add(p.getLocation() - 7);
				if(getRow(p.getLocation() - 8) == getRow(p.getLocation() - 9) && getPieceColor(p.getLocation() - 9) == p.oppositeColor())
					moves.add(p.getLocation() - 9);
		}
		
		if(p.getType() == BLACK_PAWN){
			if(p.hasMoved() == false && getPieceColor(p.getLocation() + 16) == NONE){
				moves.add(p.getLocation() + 16);
			}
			
			if(getPieceColor(p.getLocation() + 8) == NONE)
				moves.add(p.getLocation() + 8);
			if(getRow(p.getLocation() + 8) == getRow(p.getLocation() + 7) && getPieceColor(p.getLocation() + 7) == p.oppositeColor())
				moves.add(p.getLocation() + 7);
			if(getRow(p.getLocation() + 8) == getRow(p.getLocation() + 9) && getPieceColor(p.getLocation() + 9) == p.oppositeColor())
				moves.add(p.getLocation() + 9);
		}
		
		return moves;
	}
	
	private ArrayList<Integer> getKnightMoves(ChessPiece p){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
			if((getRow(p.getLocation() - 15) == getRow(p.getLocation() - 16)) && p.getLocation() - 15 >= 0 && p.getColor() != getPieceColor(p.getLocation() - 15))
				moves.add(p.getLocation() - 15);
			if((getRow(p.getLocation() - 6) == getRow(p.getLocation() - 8)) && p.getLocation() - 6 >= 0 && p.getColor() != getPieceColor(p.getLocation() - 6))
				moves.add(p.getLocation() - 6);
			if((getRow(p.getLocation() + 10) == getRow(p.getLocation() + 8)) && p.getLocation() + 10 <= 63 && p.getColor() != getPieceColor(p.getLocation() + 10))
				moves.add(p.getLocation() + 10);
			if((getRow(p.getLocation() + 17) == getRow(p.getLocation() + 16)) && p.getLocation() + 17 <= 63 && p.getColor() != getPieceColor(p.getLocation() + 17))
				moves.add(p.getLocation() + 17);
			if((getRow(p.getLocation() + 15) == getRow(p.getLocation() + 16)) && p.getLocation() + 15 <= 63 && p.getColor() != getPieceColor(p.getLocation() + 15))
				moves.add(p.getLocation() + 15);
			if((getRow(p.getLocation() + 6) == getRow(p.getLocation() + 8)) && p.getLocation() + 6 <= 63 && p.getColor() != getPieceColor(p.getLocation() + 6))
				moves.add(p.getLocation() + 6);
			if((getRow(p.getLocation() - 10) == getRow(p.getLocation() - 8)) && p.getLocation() - 10 >= 0 && p.getColor() != getPieceColor(p.getLocation() - 10))
				moves.add(p.getLocation() - 10);
			if((getRow(p.getLocation() - 17) == getRow(p.getLocation() - 16)) && p.getLocation() - 17 >= 0 && p.getColor() != getPieceColor(p.getLocation() - 17))
				moves.add(p.getLocation() - 17);
		
		return moves;
	}
	
	private ArrayList<Integer> getBishopMoves(ChessPiece p){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		//Up and to the right
		for(int i = Math.max(getColumn(p.getLocation()), 7 - getRow(p.getLocation())), j = 1; i < 7; i++, j++){
			
			if(getPieceColor(p.getLocation() - (8 * j) + j) == NONE)
				moves.add(p.getLocation() - (8 * j) + j);
			else if(p.getColor() == getPieceColor(p.getLocation() - (8 * j) + j))
				break;
			else{
				moves.add(p.getLocation() - (8 * j) + j);
				break;
			}
		}
		
		//Up and to the left
		for(int i = Math.max(7 - getColumn(p.getLocation()), 7 - getRow(p.getLocation())), j = 1; i < 7; i++, j++){

			if(getPieceColor(p.getLocation() - (8 * j) - j) == NONE)
				moves.add(p.getLocation() - (8 * j) - j);
			else if(p.getColor() == getPieceColor(p.getLocation() - (8 * j) - j))
				break;
			else{
				moves.add(p.getLocation() - (8 * j) - j);
				break;
			}
		}
		
		//Down and to the left
		for(int i = Math.max(7 -getColumn(p.getLocation()), getRow(p.getLocation())), j = 1; i < 7; i++, j++){
			
			if(getPieceColor(p.getLocation() + (8 * j) - j) == NONE)
				moves.add(p.getLocation() + (8 * j) - j);
			else if(p.getColor() == getPieceColor(p.getLocation() + (8 * j) - j))
				break;
			else{
				moves.add(p.getLocation() + (8 * j) - j);
				break;
			}
		}
		
		//Down and to the right
		for(int i = Math.max(getColumn(p.getLocation()), getRow(p.getLocation())), j = 1; i < 7; i++, j++){

			if(getPieceColor(p.getLocation() + (8 * j) + j) == NONE)
				moves.add(p.getLocation() + (8 * j) + j);
			else if(p.getColor() == getPieceColor(p.getLocation() + (8 * j) + j))
				break;
			else{
				moves.add(p.getLocation() + (8 * j) + j);
				break;
			}
		}
		return moves;
	}
	
	private ArrayList<Integer> getRookMoves(ChessPiece p){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		//Up
		for(int i =  7 - getRow(p.getLocation()), j = 1; i < 7; i++, j++){
			if(getPieceColor(p.getLocation() - (8 * j)) == NONE)
				moves.add(p.getLocation() - (8 * j));
			else if(p.getColor() == getPieceColor(p.getLocation() - (8 * j)))
				break;
			else{
				moves.add(p.getLocation() - (8 * j));
				break;
			}
		}
		
		//Down
		for(int i = getRow(p.getLocation()), j = 1; i < 7; i++, j++){
			if(getPieceColor(p.getLocation() + (8 * j)) == NONE)
				moves.add(p.getLocation() + (8 * j));
			else if(p.getColor() == getPieceColor(p.getLocation() + (8 * j)))
				break;
			else{
				moves.add(p.getLocation() + (8 * j));
				break;
			}
		}
		
		//Right
		for(int i = getColumn(p.getLocation()), j = 1; i < 7; i++, j++){
			if(getPieceColor(p.getLocation() + j) == NONE)
				moves.add(p.getLocation() + j);
			else if(p.getColor() == getPieceColor(p.getLocation() + j))
				break;
			else{
				moves.add(p.getLocation() + j);
				break;
			}
		}
		
		//Left
		//Right
		for(int i = 7 - getColumn(p.getLocation()), j = 1; i < 7; i++, j++){
			if(getPieceColor(p.getLocation() - j) == NONE)
				moves.add(p.getLocation() - j);
			else if(p.getColor() == getPieceColor(p.getLocation() - j))
				break;
			else{
				moves.add(p.getLocation() - j);
				break;
			}
		}
		
		
		return moves;
	}
	
	private ArrayList<Integer> getQueenMoves(ChessPiece p){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		ArrayList<Integer> bishopMoves = getBishopMoves(p);
		ArrayList<Integer> rookMoves = getRookMoves(p);
		
		moves.addAll(bishopMoves);
		moves.addAll(rookMoves);
		
		return moves;
	}
	
	private ArrayList<Integer> getKingMoves(ChessPiece p){
		ArrayList<Integer> moves = new ArrayList<Integer>();
		

			if(p.getLocation() - 9 >= 0 && getRow(p.getLocation() - 9) == getRow(p.getLocation() - 8) && p.getColor() != getPieceColor(p.getLocation() - 9))
				moves.add(p.getLocation() - 9);
			if(p.getLocation() - 8 >= 0 && p.getColor() != getPieceColor(p.getLocation() - 8))
				moves.add(p.getLocation() - 8);
			if(p.getLocation() - 7 >= 0 && getRow(p.getLocation() - 7) == getRow(p.getLocation() - 8) && p.getColor() != getPieceColor(p.getLocation() - 7))
				moves.add(p.getLocation() - 7);
			
			if(p.getLocation() - 1 >= 0 && getRow(p.getLocation() - 1) == getRow(p.getLocation()) && p.getColor() != getPieceColor(p.getLocation() - 1))
				moves.add(p.getLocation() - 1);
			if(p.getLocation() + 1 <= 63 && getRow(p.getLocation() + 1) == getRow(p.getLocation()) && p.getColor() != getPieceColor(p.getLocation() -+ 1))
				moves.add(p.getLocation() + 1);
			
			if(p.getLocation() + 9 <= 63 && getRow(p.getLocation() + 9) == getRow(p.getLocation() + 8) && p.getColor() != getPieceColor(p.getLocation() + 9))
				moves.add(p.getLocation() + 9);
			if(p.getLocation() + 8 <= 63 && p.getColor() != getPieceColor(p.getLocation() + 8))
				moves.add(p.getLocation() + 8);
			if(p.getLocation() + 7 <= 63 && getRow(p.getLocation() + 7) == getRow(p.getLocation() + 8) && p.getColor() != getPieceColor(p.getLocation() + 7))
				moves.add(p.getLocation() + 7);

		
		return moves;
	}
		
}
