import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class DeckOfCards {
	public static BlackJackCards[] deck;
	// private int currentCard;

	public DeckOfCards() throws IOException {

		deck = new BlackJackCards[52];

		int deckNum = 0;
		int deckImage = 1;

		for (int i = 0; i < 4; i++) { //since there are 4 suits it needs to iterate 4 times 

			for (int j = 1; j < 14; j++) { //since there are 14 values in each suits 

				deck[deckNum] = new BlackJackCards(j, i, ImageIO.read(new File("images/" + deckImage + ".gif")));//Creating a new object and inserting the image every time it loops
				deckNum++;
				deckImage++;
			}
		}
	}
	//Creating the shuffle method
	public void shuffle() {

		int deckLength = deck.length - 1;

		for (int i = 0; i < deckLength; i++) {
			int r = i + (int) (Math.random() * (deckLength - i));
			BlackJackCards temp = deck[r];
			deck[r] = deck[i];
			deck[i] = temp;
		}
	}
	//Creating Dealing card method
	public int dealTopCard() {
		int topCard = -1;

		if (deck.length > 0) {
			topCard++;
			return deck[topCard].getRank();

		} else {
			return 0;
		}
	}
}
