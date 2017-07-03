package chessClient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ChessBoardGUI extends JPanel {
	
	@Override
	public void paintComponent(Graphics g){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				g.setColor(Color.white);
				
				if(i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0)
					g.setColor(Color.black);
				g.fillRect(i*68 + 209, j*68, 68, 68);
			}
		}	
	}
	
	public void initialize(){
		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent mouseEvent){
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
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

}
