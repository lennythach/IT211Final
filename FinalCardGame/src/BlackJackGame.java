import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import BlackJack.SwingAction_1;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class BlackJackGame {
	
	public DeckOfCards deck = new DeckOfCards();
	public JFrame frame;
	
	Card backCard = new Card(100, 100, ImageIO.read(new File("images/b.gif")));
	
	public JPanel contentPane;

	
	JLabel gameStatus = new JLabel("");
	JLabel firstCard = new JLabel(new ImageIcon(backCard.getCardImage()));
	//--------------------------------------------------------
	//Buttons
	//-------------------------------------------------------------------------------
	JButton HitBtn = new JButton("Hit!");
	JButton stayBtn = new JButton("Stay!");
	JButton DealBtn = new JButton("Deal!");
	JButton newHand = new JButton("NewHand!");
	
	int nextCard = 4;
	int playerValue = 0;
	int dealerValue = 0;
	public final Action action = new SwingAction();
	public final Action action_1 = new SwingAction_1();
	
	//--------------------------------------------------------
	//Creating the Panels and labels
	//------------------------------------------------------------------------
	public final JPanel DealerPanel = new JPanel();
	public final JPanel PlayerPanel = new JPanel();
	public final JLabel DealerLabel = new JLabel("Dealer's Hand");
	public final JLabel PlayerLabel = new JLabel("Player's Hand");
	private final JLabel lblNewLabel_2 = new JLabel("GameStats");
	private final JButton ruleBtn = new JButton("Rules");
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BlackJackGame frame = new BlackJackGame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public BlackJackGame() throws IOException {
		
		// Shuffling our Deck Every new Game
		deck.shuffle();
		//----------------------------------------------
		// Frames
		//-----------------------------------------------------------------------------
		frame = new JFrame();
		frame.setTitle("BlackJack!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 769, 481);
		frame.setVisible(true);
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				Image img = Toolkit.getDefaultToolkit().getImage(BlackJackGame.class.getResource("/img/background.png"));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//----------------------------------------------
		// Buttons
		//-----------------------------------------------------------------------------
		HitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Create a new Label for the next card when player hits
				PlayerPanel.add(new JLabel(new ImageIcon(DeckOfCards.deck[nextCard].getCardImage())));
				
				//Updates the player values from new card hit
				playerValue += DeckOfCards.deck[nextCard].blackJackValue(playerValue);
				nextCard++;
				
				//Updates the card image on the panel
				PlayerPanel.updateUI();
				
				//Updates the Game status in the center
				gameStatus.setText("<html><p>Player Score: " + playerValue + "</p></html>");
				checkGameStatus();
				System.out.println("dealer value: " + dealerValue);
				System.out.println("user value: " + playerValue);
			}
		});
		//Creating the Hit button
		HitBtn.setBounds(154, 397, 89, 23);
		HitBtn.setEnabled(false);
		contentPane.add(HitBtn);
		
		//Creating the Stay button
		stayBtn.setAction(action);
		stayBtn.setBounds(253, 397, 89, 23);
		stayBtn.setEnabled(false);
		contentPane.add(stayBtn);
		
		//Creating the Deal button
		DealBtn.setBounds(389, 397, 89, 23);
		contentPane.add(DealBtn);
		DealBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Display the dealer Panel
				DealerPanel.setVisible(true);
				//Toggling the buttons on and off
				stayBtn.setEnabled(true);
				HitBtn.setEnabled(true);
				DealBtn.setEnabled(false); //to disable the button
				
				//Dealing two cards to the player and the dealer 
				DealerPanel.add(firstCard);				
				PlayerPanel.add(new JLabel(new ImageIcon(DeckOfCards.deck[1].getCardImage())));				
				DealerPanel.add(new JLabel(new ImageIcon(DeckOfCards.deck[2].getCardImage())));				
				PlayerPanel.add(new JLabel(new ImageIcon(DeckOfCards.deck[3].getCardImage())));
				
				//Updating the panels with the new cards
				DealerPanel.updateUI();
				PlayerPanel.updateUI();
				
				initialValue();

				//Updating the game status
				gameStatus.setText("<html><p>Player Score: " + playerValue + "</p></html>");
				System.out.println("dealer value: " + dealerValue);
				System.out.println("user value: " + playerValue);
			}
		});
		
		// newHand button
		newHand.setBounds(488, 397, 89, 23);
		newHand.setEnabled(false);
		contentPane.add(newHand);
		newHand.setAction(action_1);
		
		//Creating the rule button
		ruleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create a dialog Box 
	            JDialog d = new JDialog(frame, "Rules"); 
	  
	            // create a label 
	            JLabel l = new JLabel("<html><div style ='text-align:center;'>"
	            		+ "<br> Try to hit a 21"
	            		+ "<br> Dealer will have only one face card up"
	            		+ "<br> If Player goes past 21 then Player Bust!"
	            		+ "<br> If Dealer goes past 21 then Dealer Bust!"
	            		+ "<br> If Dealer has a higher number then Player loses."
	            		+ "<br> If Player has a higher number then Dealer then Player wins!"
	            		+ "<br> Player can choose to stay if they are satified with their number"
	            		+ "<br> If player and dealer ties then player will push"
	            		+ "<br> Player can choose to play again by hitting the newHand button"
	            		+ "</div><html>"
	            		); 
	  
	            d.getContentPane().add(l); 
	  
	            // setsize of dialog 
	            d.setSize(300, 300); 
	  
	            // set visibility of dialog 
	            d.setVisible(true); 
			}
		});
		ruleBtn.setBounds(654, 397, 89, 23);
		contentPane.add(ruleBtn);

		//----------------------------------------------
		// Panel
		//-----------------------------------------------------------------------------
		
		//Dealer's Panels
		DealerPanel.setBounds(154, 47, 443, 112);
		contentPane.add(DealerPanel);
		
		//Player Panel
		PlayerPanel.setBounds(154, 249, 443, 112);
		contentPane.add(PlayerPanel);
		gameStatus.setForeground(Color.WHITE);
		
		//Status of the Game
		gameStatus.setBounds(295, 178, 151, 60);
		contentPane.add(gameStatus);
		PlayerLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		PlayerLabel.setForeground(Color.WHITE);
		PlayerLabel.setBounds(333, 373, 82, 13);
		contentPane.add(PlayerLabel);
		DealerLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		DealerLabel.setForeground(Color.WHITE);
		DealerLabel.setBounds(312, 22, 89, 14);
		contentPane.add(DealerLabel);
	}
	
	//----------------------------------------------
	// Methods
	//-----------------------------------------------------------------------------
	//Setting the value when the deal button is clicked
	public void initialValue() {		
		dealerValue += DeckOfCards.deck[0].blackJackValue(dealerValue);
		playerValue += DeckOfCards.deck[1].blackJackValue(playerValue);
		dealerValue += DeckOfCards.deck[2].blackJackValue(dealerValue);
		playerValue += DeckOfCards.deck[3].blackJackValue(playerValue);	
	}
	
	//Updating the Game Status
	public void checkGameStatus() {
		
		//If player goes over 21
		if(playerValue > 21) {
			//display the back image
			firstCard = new JLabel(new ImageIcon(DeckOfCards.deck[0].getCardImage()));
			//remove the second label
			DealerPanel.remove(0);
			//replace the second label
			DealerPanel.add(firstCard, 0);
			//update the dealer panel
			DealerPanel.updateUI();
			//toggle the buttons on an off
			HitBtn.setEnabled(false);
			stayBtn.setEnabled(false);
			newHand.setEnabled(true);
			//set the game status of player
			gameStatus.setText("Bust");
			
		}
	}
	//Flips the Dealers card once it is the dealer turn
	public void updateBackCard() {
        //display the back faced image
        firstCard = new JLabel(new ImageIcon(DeckOfCards.deck[0].getCardImage()));
        DealerPanel.remove(0);
        DealerPanel.add(firstCard, 0);
        DealerPanel.updateUI();

        //disable all buttons, and enable restart button
        HitBtn.setEnabled(false);
        stayBtn.setEnabled(false);
        newHand.setEnabled(true);
    }
	// Creating our Stay Method
	public class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Stay");
			putValue(SHORT_DESCRIPTION, "Some short description");
			
		}
		
		//Updating the game once the User Hit Stay
		public void actionPerformed(ActionEvent e) {
			firstCard = new JLabel(new ImageIcon(DeckOfCards.deck[0].getCardImage()));
			//First card on dealer panel is removed
			DealerPanel.remove(0);
			//first card on dealer panel is replace
			DealerPanel.add(firstCard, 0);
			//Update the dealers panel
			DealerPanel.updateUI();
			//toggle the buttons on and off
			HitBtn.setEnabled(false);
			stayBtn.setEnabled(false);
			newHand.setEnabled(true);
			
				//while the dealer value is less then 17			
				while(dealerValue < 17) {
				//Dealer will hit again 
				dealerValue += DeckOfCards.deck[nextCard].blackJackValue(dealerValue);
				//Dealer will add another card image to the panel
				DealerPanel.add(new JLabel(new ImageIcon(DeckOfCards.deck[nextCard].getCardImage())));
				//Panel will update
				DealerPanel.updateUI();
				nextCard++;
				}
				
				//if player score is higher then dealer or dealer value is greater then 21
				if(playerValue > dealerValue || dealerValue > 21) {
					//updated games status that player wins
					gameStatus.setText("<html><p>Player Wins!"
										+ "<br> Player Score: " + playerValue
										+ " <br> Dealer Score: " + dealerValue +"</p></html>");
				}
				//If player score and dealer score are even then Player Push!
				else if(playerValue == dealerValue) {
					gameStatus.setText("<html><p>Player Push!"
							+ "<br> Player Score: " + playerValue
							+ " <br> Dealer Score: " + dealerValue +"</p></html>");
				}
				//Dealer wins
				else {
					gameStatus.setText("<html><p>Dealer Wins!"
							+ "<br> Player Score: " + playerValue
							+ " <br> Dealer Score: " + dealerValue +"</p></html>");
				}
		}
	}
	
	//----------------------------------------------
	// PlayAgain Button
	//-----------------------------------------------------------------------------
	public class SwingAction_1 extends AbstractAction { 
		public SwingAction_1() {
			putValue(NAME, "NewHand");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			//shuffle deck
			deck.shuffle();
			//reset all values and disable and enables buttons
			DealerPanel.removeAll();
			PlayerPanel.removeAll();
			DealerPanel.updateUI();
			PlayerPanel.updateUI();
			HitBtn.setEnabled(false);
			stayBtn.setEnabled(false);
			DealBtn.setEnabled(true);
			gameStatus.setText("");
			dealerValue = 0;
			playerValue = 0;
			nextCard = 0;
			firstCard = new JLabel(new ImageIcon(backCard.getCardImage()));
			System.out.println("Restart Button Works!");
		}
	}
	

}
