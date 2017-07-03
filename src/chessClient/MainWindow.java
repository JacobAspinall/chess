package chessClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
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

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private static JTextArea textArea;
	private ClientNetwork network;

	/**
	 * Create the application.
	 */
	public MainWindow(ClientNetwork network) {
		this.network = network;
		initialize();
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

		textField.setBounds(10, 170, 942, 20);
		panel.add(textField);
		textField.setColumns(80);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 942, 148);
		textArea.setRows(8);
		textArea.setColumns(90);
		panel.add(textArea);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 1280, 16);
		frame.getContentPane().add(toolBar);
		
		ChessBoardGUI chessBoardGUI = new ChessBoardGUI();
		chessBoardGUI.setBackground(new Color(46, 139, 87));
		chessBoardGUI.setBounds(303, 27, 962, 545);
		frame.getContentPane().add(chessBoardGUI);
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == (KeyEvent.VK_ENTER) && textField.getText() != null){
					String s = textField.getText();
					network.send(textField.getText());
					textField.setText("");
				}
			}
		});
	}
	
	public static void displayChat(String message){
		textArea.append(message + System.lineSeparator());
	}
}
