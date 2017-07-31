package chessClient;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;
import javax.swing.DropMode;

import java.awt.Color;

import javax.swing.JToolBar;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.List;
import java.awt.Label;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private static JTextArea textArea;
	public static ClientNetwork network;
	private static ChessBoardGUI chessBoardGUI;
	private static String username = "";
	private static ArrayList<ChessBoardGUI> games = new ArrayList<ChessBoardGUI>();

	/**
	 * Create the application.
	 */
	public MainWindow(ClientNetwork newNetwork) {
		network = newNetwork;
		initialize();
		network.connect();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(46, 139, 87));
		frame.setBackground(new Color(46, 139, 87));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(46, 139, 87));
		panel.setBounds(303, 570, 962, 190);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(new Color(60, 179, 113));

		textField.setBounds(10, 170, 942, 20);
		panel.add(textField);
		textField.setColumns(80);
		textField.setBorder(BorderFactory.createEmptyBorder());
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(60, 179, 113));
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 942, 148);
		textArea.setRows(8);
		textArea.setColumns(90);
		panel.add(textArea);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 1280, 16);
		frame.getContentPane().add(toolBar);
		
		chessBoardGUI = new ChessBoardGUI();
		chessBoardGUI.setBackground(new Color(46, 139, 87));
		chessBoardGUI.setBounds(303, 27, 962, 545);
		frame.getContentPane().add(chessBoardGUI);
		
		JList<String> playerList = new JList<String>();
		playerList.setBackground(new Color(60, 179, 113));
		playerList.setBounds(10, 72, 287, 364);
		frame.getContentPane().add(playerList);
		playerList.setListData(Arrays.copyOf(network.getUsernames().toArray(), network.getUsernames().toArray().length, String[].class));
		
		JList<ChessBoardGUI> gameList = new JList<ChessBoardGUI>();
		gameList.setBackground(new Color(60, 179, 113));
		gameList.setBounds(10, 492, 287, 268);
		frame.getContentPane().add(gameList);
		gameList.setListData(Arrays.copyOf(games.toArray(), games.toArray().length, ChessBoardGUI[].class));
		
		Label label = new Label("Players");
		label.setFont(new Font("Dialog", Font.PLAIN, 28));
		label.setAlignment(Label.CENTER);
		label.setBounds(10, 22, 287, 44);
		frame.getContentPane().add(label);
		
		Label label_1 = new Label("Games");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 28));
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(10, 442, 287, 44);
		frame.getContentPane().add(label_1);
		
		chessBoardGUI.initialize();
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == (KeyEvent.VK_ENTER) && textField.getText() != null){
					String input = textField.getText();
					String[] splitInput = input.split(" ");
					if(input.substring(0, 1).equals("/")){
						if(splitInput[0].equals("/username")){
							if(splitInput.length > 1){
								for(int i = 1; i < splitInput.length; i++){
									MainWindow.username += splitInput[i];
								}
							}
						}
						if(splitInput[0].equals("/newgame")){
							
						}
						
					}
					else{
						input = "c" + textField.getText();
					}
					network.send(input);
					textField.setText("");
					
				}
			}
		});
	}
	
	public static void displayChat(String message){
		textArea.append(message + System.lineSeparator());
	}
	
	public static void recieveMove(String move){
		String fromString = move.substring(0, 2);
		String toString = move.substring(2);
		
		int from = Integer.parseInt(fromString);
		int to = Integer.parseInt(toString);
		
		chessBoardGUI.makeMoveGUI(from, to);
		
		
		
	}
	
	public void createGame(){
		games.add(new ChessBoardGUI());
	}
	
	
	
	
}
