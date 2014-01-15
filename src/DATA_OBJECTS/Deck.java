package DATA_OBJECTS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/*todo
 * make constructor initialize deck object if not initialized already,
 * other wise shuffle and return shuffled deck.
 */
public class Deck {
	private Card [] deck;
	
	
	private String [] suits= {"Spade", "Club", "Diamond", "Heart"};
	private String [] cardNames=
			{"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
	
	//constructor
	public Deck(){
		deck = new Card[52];
		for(int i=0; i<52; i++){
			deck[i] = new Card(((i/4)+1), cardNames[i/4], suits[i%suits.length], i%suits.length);
			
		}
		List <Card> list = Arrays.asList(deck);
		Collections.shuffle(list);
		deck = (Card []) list.toArray();
	}
	//getter
	public Card[] getDeck(){
		return deck;
	}
	
	public void shuffleDeck(){
		
	}

}
