import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI implements ActionListener, ItemListener {
public DeckOfCards deck;

	Image img = Toolkit.getDefaultToolkit().getImage("img/background.png");

	//Creating the Label and frames
	Card backCard = new Card(100,100, ImageIO.read(new File("images/b.gif")));
	JFrame window = new JFrame("Card Game!");
	JButton button = new JButton("Guess!");
	JPanel contentPane = new JPanel(new BorderLayout()){
		public void paintComponent(Graphics g) {
			Image img = Toolkit.getDefaultToolkit().getImage(BlackJackGame.class.getResource("/img/background.png"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	};
	JPanel suitPan = new JPanel(new BorderLayout());
	JPanel valuePan = new JPanel(new BorderLayout());
	JLabel backCardImage = new JLabel(new ImageIcon(backCard.getCardImage()));
	JLabel cardImage;
	JLabel gameStats;
	ImageIcon imgIcon;
	JComboBox suitSelection;
	JComboBox valueSelection;
	
	//setting up the integer variables
	int suit = 0;
	int value = 1;
	int nextCard = 0;
	int userCardRank = 1;
	int win = 0;
	int trys = 0;
	int rmngCards = 52;

	public GUI() throws IOException {
		deck = new DeckOfCards();

		// shuffle deck
		deck.shuffle();

		// printing out rank and card name in the console.. for cheating (:
		System.out.println(DeckOfCards.deck[nextCard].getRank());
		System.out.println(DeckOfCards.deck[nextCard].toString());

		window.setSize(1250, 1000);
		((JFrame) window).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button.addActionListener(this);
		button.setSize(300, 400);
		button.setBackground(Color.CYAN);
		// creating panel in window
		contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		contentPane.setLayout(new GridLayout(2, 3, 20, 20));
		suitPan.setLayout(new GridLayout(0, 1));
		valuePan.setLayout(new GridLayout(0, 1));
		// creating card image
		imgIcon = new ImageIcon();
		cardImage = new JLabel(imgIcon);
		//Creating the Game status
		gameStats = new JLabel("<html>Make You Choice!<br>" + "Wins: " + win + "<br> Try's: " + trys + "<br>Cards Left: "
				+ rmngCards + "</html>");
		gameStats.setForeground(Color.white);
		
		//Creating the Suit Label
		JLabel suitLabel = new JLabel("Pick Your Suit");
		String[] suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
		suitSelection = new JComboBox(suits);
		
		//Creating the value Label
		JLabel valueLabel = new JLabel("Pick Your Value");
		String[] value = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		valueSelection = new JComboBox(value);
		
		//Adding a drop down menu to the suit label
		suitSelection.addItemListener(this);
		suitPan.add(suitLabel);
		suitPan.add(suitSelection);
		
		//Adding a drop down menu to the value label
		valueSelection.addItemListener(this);
		valuePan.add(valueLabel);
		valuePan.add(valueSelection);

		// Adding labels to panel
		contentPane.add(backCardImage);
		contentPane.add(suitPan);
		contentPane.add(gameStats);
		contentPane.add(cardImage);
		contentPane.add(valuePan);
		
		contentPane.add(button);
		window.add(contentPane, BorderLayout.CENTER);
		window.pack();
		window.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Displaying the card images
		imgIcon.setImage(DeckOfCards.deck[nextCard].getCardImage());
		cardImage.repaint();
		
		//If Card is correct
		if (DeckOfCards.deck[nextCard].getRank() == userCardRank) {
			//Player score will update 
			System.out.println("you got it");
			rmngCards--;
			win++;
		}
		//else status will update wrong
		else {
			System.out.println("wrong");
			rmngCards--;
			trys++;
		}

		gameStats.setText("<html>Try Again!<br>" + "Wins: " + win + "<br> Loss: " + trys + "<br>Cards Left: " + rmngCards
				+ "</html>");
		nextCard++;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		suit = suitSelection.getSelectedIndex();
		value = valueSelection.getSelectedIndex() + 1;

		userCardRank = (suit * 13) + value;
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DeckOfCards deck = new DeckOfCards();
		deck.shuffle();
		new GUI();
	}
}
