package DATA_OBJECTS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;



public class CurrentPlayedCards {
	private String playType;
	private ArrayList<Card> lastPlayedCards;
	private Player currentPlayer;
	
public CurrentPlayedCards(){
	lastPlayedCards = new ArrayList<Card>();
	currentPlayer=null;
	playType=null;
}
	//~~~~~~~~~~~~~~~GETTERS SETTERS~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	public String getPlayType() {
		return playType;
	}

	public void setPlayType(String playType) {
		this.playType = playType;
	}

	public ArrayList<Card> getLastPlayedCards() {
		return lastPlayedCards;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	
	//~~~~~~~~~~~~~~~~~PUBLIC METHODS~~~~~~~~~~~~~~~~//
	public void printLastPlayedCards(){
		for(Card c : lastPlayedCards){
			c.printCard();
		}
	}
	


	/**
	 * When Player chooses cards to play, add cards is called. addCards checks
	 * to see if the cards that a player wants to play is able to be played. 
	 * cards could be played, lastcards stack is cleared and the played cards
	 * get pushed to the stack
	 * @param playedCards
	 * @param player
	 * @return
	 */
	public boolean addCards(ArrayList<Card> playedCards, Player player){
		String type;
		int numCardsPlayed = playedCards.size();
		Collections.sort(playedCards);
		type = findPlayType(playedCards);
		System.out.println("[DEBUG]playType = " + playType + "~~type = " + type + " ~~~is starter? " + playedCards.get(0).starter() );
		//will play hand if it contains 3 of spades and is the first hand to be played
		if (playType ==null && type !=null && playedCards.get(0).starter()){
			
			setLastPlayedCards(playedCards);
			player.removeCards(playedCards);
			playType = type;
			currentPlayer = player;
			return true;
		}
		//if the played cards is a valid type
		else if(type!=null){
			//if player is current player, then all others have passed and current
			//player can play what he wants
			if (player == currentPlayer){
				System.out.println("[DEBUG] player == currentPlayer");
				setLastPlayedCards(playedCards);
				player.removeCards(playedCards);
				playType = type;
				return true;
			}
			//bomb played beats anything
			else if (type.equals("bomb")){
				setLastPlayedCards(playedCards);
				player.removeCards(playedCards);
				currentPlayer = player;
				return true;
			}
			//if played hand is correct type
			else if (type.equals(playType) && (numCardsPlayed == lastPlayedCards.size())){
				//higher type gets played
				if (lastPlayedCards.get(0).getValue()< playedCards.get(0).getValue()){
					setLastPlayedCards(playedCards);
					player.removeCards(playedCards);
					currentPlayer = player;
					return true;
				}
				//if cards are same number, higher suit value gets 
				
				else if (lastPlayedCards.get(0).getValue() == playedCards.get(0).getValue()){
					
					if (lastPlayedCards.get(0).getSuitValue() < playedCards.get(0).getSuitValue()){
						setLastPlayedCards(playedCards);
						player.removeCards(playedCards);
						currentPlayer = player;
						return true;
					}
				}
			}

		}
		System.out.println("[DEBUG] cannot play");
		return false;
	}
	
//~~~~~~~~~~~~~~~~~~Private Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	private void setLastPlayedCards(ArrayList<Card> playedCards) {
		lastPlayedCards.clear();
		for(Card card: playedCards){
			lastPlayedCards.add(card);
		}
	}
	/**
	 * checks to see what type of card combiniation that player plays
	 * i.e. pair, triple, straight, or bomb
	 * @param playedCards
	 * @return
	 */
	private String findPlayType(ArrayList<Card> playedCards){
		String type = null;
		if(playedCards.size() == 1)
			type = "single";
		else if(playedCards.size() ==2){
			if (playedCards.get(0).getValue() == playedCards.get(1).getValue())
				type = "pair";
		}
		else if (playedCards.size()==3){
			System.out.println("[DEBUG] size of cards is 3, checking if straight or triple");
			if ((playedCards.get(0).getValue() == playedCards.get(1).getValue())
					&& (playedCards.get(0).getValue() == playedCards.get(2).getValue())){
				System.out.println("[DEBUG] is triple");
				type = "triple";
			}
			else if(isStraight(playedCards)){
				System.out.println("[DEBUG] is Straight");
				type = "straight";
			}
		}
		else{
			if (isStraight(playedCards))
				type = "straight";
			else if (isBomb(playedCards))
				type = "bomb";
		}	
		
		return type;
	}
	private boolean isStraight(ArrayList<Card> cards){
		int i=1;
		boolean isCont = true;
		while (i<cards.size() && isCont){
			if (cards.get(i).getValue()-1 != cards.get(i-1).getValue()){
				System.out.println("[DEBUG] current card value is :" + cards.get(i).getValue() + "  previous card was : " + (cards.get(i-1).getValue()-1));
				isCont=false;
			}
			i++;
			System.out.println("i = " + i + " && cont = " + isCont);
		}
		return isCont;
	}
	private boolean isBomb(ArrayList<Card> cards){
		int currentValue;
		if (cards.size()==4){
			if((cards.get(0).getValue() == cards.get(1).getValue())
					&& (cards.get(1).getValue() == cards.get(2).getValue())
					&& (cards.get(2).getValue() == cards.get(3).getValue()))
					return true;
		}
		else if(cards.size() ==6)
			if(cards.get(0).getValue() == cards.get(1).getValue()){
					currentValue= cards.get(0).getValue();
					if(cards.get(2).getValue() == currentValue+1 &&
							cards.get(3).getValue() == currentValue+1){
						if(cards.get(4).getValue() == currentValue+2 &&
								cards.get(5).getValue() == currentValue+2)
							return true;
					}
						
			}
		return false;
	}
}
