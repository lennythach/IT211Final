import java.awt.image.BufferedImage;

public class BlackJackCards extends Card {
	
	public BlackJackCards(int theValue, int theSuit, BufferedImage card) {
		super(theValue, theSuit, card);
		// TODO Auto-generated constructor stub
	}
	
	public int blackJackValue(int gameValue) {
		//If card value is greater then 10 then return only 10s <--this makes all the face cards equal too 10
		if(getValue() > 10) {
			return 10;
		}
		//If card is equal to ace 
		else if(getValue() == 1) {
			if(gameValue < 11) { //<-- if game value is less then 11 then ace will equal 11
				return 11;
			}
			else { //<-- else ace value will equal to 1
				return 1;
			}
		}
		else {
		return getValue();
		}
	}
}
