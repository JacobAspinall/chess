package chessClient;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import chessClient.ChessEnums.PieceColor;
import static chessClient.ChessEnums.PieceColor.*;
import static chessClient.ChessEnums.PieceType.*;


public class ChessBoardGUI extends JPanel {
	
	private static BufferedImage boardLayer;
	private static BufferedImage pieceLayer;
	public static BufferedImage highlightLayer;
	private static BufferedImage blackPawn;
	private static BufferedImage blackKnight;
	private static BufferedImage blackBishop;
	private static BufferedImage blackRook;
	private static BufferedImage blackQueen;
	private static BufferedImage blackKing;
	private static BufferedImage whitePawn;
	private static BufferedImage whiteKnight;
	private static BufferedImage whiteBishop;
	private static BufferedImage whiteRook;
	private static BufferedImage whiteQueen;
	private static BufferedImage whiteKing;
	
	private PieceColor playerColor;
	private ChessPiece selectedPiece;
	private ArrayList<Integer> possibleMoves;
	
	
	public ChessBoard board;
	
	public ChessBoardGUI(){
		boardLayer = new BufferedImage(962, 545, BufferedImage.TYPE_INT_ARGB );
		pieceLayer = new BufferedImage(962, 545, BufferedImage.TYPE_INT_ARGB );
		highlightLayer = new BufferedImage(962, 545, BufferedImage.TYPE_INT_ARGB );
		board = new ChessBoard();
		playerColor = WHITE;
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		//Combine all layers in order to prevent flickering
		BufferedImage combinedLayers = new BufferedImage(962, 545, BufferedImage.TYPE_INT_ARGB);
		
		
		combinedLayers.getGraphics().drawImage(boardLayer, 0,0, this);
		combinedLayers.getGraphics().drawImage(highlightLayer, 0,0, this);
		combinedLayers.getGraphics().drawImage(pieceLayer, 0,0, this);
		
		g.drawImage(combinedLayers, 0, 0, this);
		
		
		
	}
	
	public void initialize(){
		
		Graphics g = boardLayer.getGraphics();
		
		//Paint board
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				g.setColor(new Color(255, 206, 158));
				
				if(i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0)
					g.setColor(new Color(209, 139, 71));
				g.fillRect(i*68 + 209, j*68, 68, 68);
			}
		}
		
		//Paint starting position
	
		loadImages();
		printBoard();
	
		
		
		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent mouseEvent){

				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				boolean moved = false;
				if(e.getButton() == MouseEvent.BUTTON1 && toChessBoardPosition(e) >=0){
					if(board.get(toChessBoardPosition(e)).getColor() == playerColor)
						highlightMoves(toChessBoardPosition(e));
					else if(selectedPiece != null){
						for(int possibleMove : possibleMoves){
							if(toChessBoardPosition(e) == possibleMove){
								String output = "m";
								String from = ((Integer)selectedPiece.getLocation()).toString();
								String to = ((Integer)possibleMove).toString();
								if(from.length() == 1)
									from = "0" + from;
								if(to.length() == 1)
									to = "0" + to;
										
								MainWindow.network.send(output + from + to);
								moved = true;
								
							}

						}
						if(moved == false){
							selectedPiece = null;
							clearHighlights();
						}
						
					}
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void makeMoveGUI(int from, int to){
		
		board.move(from, to);
		printBoard();
		clearHighlights();
		
		paintComponent(getGraphics());
		
	}
	
	private void loadImages(){
		BufferedImage fullImage = null;
		try{
			fullImage = ImageIO.read(new File("ChessPieces.png"));
		} catch(IOException e){
			System.out.println("Cant load images");
		}
		
		whiteKing = fullImage.getSubimage(0, 0, 68, 68);
		whiteQueen = fullImage.getSubimage(68, 0, 68, 68);
		whiteBishop = fullImage.getSubimage(136, 0, 68, 68);
		whiteKnight = fullImage.getSubimage(204, 0, 68, 68);
		whiteRook = fullImage.getSubimage(272, 0, 68, 68);
		whitePawn = fullImage.getSubimage(340, 0, 68, 68);
		blackKing = fullImage.getSubimage(0, 68, 68, 68);
		blackQueen = fullImage.getSubimage(68, 68, 68, 68);
		blackBishop = fullImage.getSubimage(136, 68, 68, 68);
		blackKnight = fullImage.getSubimage(204, 68, 68, 68);
		blackRook = fullImage.getSubimage(272, 68, 68, 68);
		blackPawn = fullImage.getSubimage(340, 68, 68, 68);
				
	}
	
	
	private void printBoard(){
		int boardOffset = 209;
		
		//clear pieces
		Graphics2D temp = (Graphics2D) pieceLayer.getGraphics();
		temp.setBackground(new Color(0,0,0,0));
		temp.setColor(new Color(0,0,0,0));
		temp.fillRect(0,0,pieceLayer.getWidth(), pieceLayer.getHeight());
		temp.clearRect(0,0,pieceLayer.getWidth(), pieceLayer.getHeight());

		
		for(int i = 0; i < 64; i++){
			if(board.get(i).getType() == EMPTY){
				continue;
			}
			else if(board.get(i).getType() == WHITE_PAWN){
				pieceLayer.getGraphics().drawImage(whitePawn, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == WHITE_KNIGHT){
				pieceLayer.getGraphics().drawImage(whiteKnight, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == WHITE_BISHOP){
				pieceLayer.getGraphics().drawImage(whiteBishop, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == WHITE_ROOK){
				pieceLayer.getGraphics().drawImage(whiteRook, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == WHITE_QUEEN){
				pieceLayer.getGraphics().drawImage(whiteQueen, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == WHITE_KING){
				pieceLayer.getGraphics().drawImage(whiteKing, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == BLACK_PAWN){
				pieceLayer.getGraphics().drawImage(blackPawn, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == BLACK_KNIGHT){
				pieceLayer.getGraphics().drawImage(blackKnight, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == BLACK_BISHOP){
				pieceLayer.getGraphics().drawImage(blackBishop, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == BLACK_ROOK){
				pieceLayer.getGraphics().drawImage(blackRook, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == BLACK_QUEEN){
				pieceLayer.getGraphics().drawImage(blackQueen, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			else if(board.get(i).getType() == BLACK_KING){
				pieceLayer.getGraphics().drawImage(blackKing, boardOffset + i % 8 * 68, i / 8 * 68, this);
			}
			
			
		}
	}
	
	public static int toChessBoardPosition(MouseEvent e){
		int x = e.getX() - 209;
		int y = e.getY();
		System.out.println("x = " + x + " y = " + y);
		if(x >= 0 && x <= 544 && y <= 544){
			return ((y / 68) * 8) + x / 68;
		}
		else{
			return -1;
		}
	}
	
	private void highlightSquare(int chessBoardPosition) {
		
		if(chessBoardPosition == -1){
			return;
		}
		Graphics2D g = (Graphics2D) highlightLayer.getGraphics();
		
		g.setColor(new Color(255,0,0,126));
		g.fillRect(chessBoardPosition % 8 * 68 + 209, chessBoardPosition / 8 * 68 ,68,68);
		g.setColor(Color.black);
		
		paintComponent(getGraphics());

	}
	
	private void clearHighlights(){
			
		Graphics2D temp = (Graphics2D) highlightLayer.getGraphics();
		temp.setBackground(new Color(0,0,0,0));
		temp.setColor(new Color(0,0,0,0));
		temp.fillRect(0,0,highlightLayer.getWidth(), highlightLayer.getHeight());
		temp.clearRect(0,0,highlightLayer.getWidth(), highlightLayer.getHeight());

		this.paintComponent(getGraphics());
	}
	
	private void highlightMoves(int pieceLocation){
		selectedPiece = board.get(pieceLocation);
		possibleMoves = board.getMoves(selectedPiece);
		
		clearHighlights();
		
		for(int location : possibleMoves){
			highlightSquare(location);
		}
	}

}
