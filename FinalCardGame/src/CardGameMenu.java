import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class CardGameMenu {
	

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardGameMenu window = new CardGameMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CardGameMenu() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Game Menu");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose Your Game!");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(107, 91, 239, 64);
		frame.getContentPane().add(lblNewLabel);
		//------------------------------------------------------------------------------------------------------------------
		//Guessing Game GUI
		//----------------------------------------------------------------------------------------------------------------------------------

		JButton guessGame = new JButton("Card Guessing ");
		guessGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.dispose();
					DeckOfCards deck = new DeckOfCards();
					deck.shuffle();
					new GUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		guessGame.setBackground(Color.CYAN);
		guessGame.setForeground(Color.BLACK);
		guessGame.setFont(new Font("Tahoma", Font.BOLD, 11));
		guessGame.setBounds(60, 313, 142, 68);
		frame.getContentPane().add(guessGame);
		
		
		//------------------------------------------------------------------------------------------------------------------
		//Black Jack GUI
		//----------------------------------------------------------------------------------------------------------------------------------
		JButton btnNewButton_1 = new JButton("Black Jack");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new BlackJackGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(268, 313, 152, 68);
		frame.getContentPane().add(btnNewButton_1);
		//------------------------------------------------------------------------------------------------------------------
		//About Dialog
		//----------------------------------------------------------------------------------------------------------------------------------

		JButton btnNewButton = new JButton("About");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand(); 
		        { 
		            // create a dialog Box 
		            JDialog d = new JDialog(frame, "Author"); 
		  
		            // create a label 
		            JLabel l = new JLabel("<html><div style ='text-align:center;'>"
		            		+ "Author: Lenny Thach"
		            		+ "<br> -IT211 - Intro to Java"
		            		+ "<br> -Final Card Game"
		            		+ "<br> -Date: August 23, 2020"
		            		+ "</div><html>"
		            		); 
		  
		            d.add(l); 
		  
		            // setsize of dialog 
		            d.setSize(150, 150); 
		  
		            // set visibility of dialog 
		            d.setVisible(true);
		        }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(165, 218, 142, 68);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\lenny\\eclipse-workspace\\FinalCardGame\\img\\background.png"));
		lblNewLabel_1.setBounds(10, 11, 464, 439);
		frame.getContentPane().add(lblNewLabel_1);
		

		
		
	}
}
