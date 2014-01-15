import DATA_OBJECTS.*;
import java.util.ArrayList;



public class Game {
	private Player[] players;
	private CurrentPlayedCards stack;
	private int playersRemaining;
	private int currentPlayer;
	public Game(){
		currentPlayer = -1;
		playersRemaining = 4;
		stack = new CurrentPlayedCards();
		ArrayList<Card> hand1 = new ArrayList<Card>();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		ArrayList<Card> hand3 = new ArrayList<Card>();
		ArrayList<Card> hand4 = new ArrayList<Card>();
		Deck deck = new Deck();
		Card [] cards = deck.getDeck();
		
		for(int i=0; i<cards.length; i+=4){
			hand1.add(cards[i]);
			hand2.add(cards[i+1]);
			hand3.add(cards[i+2]);
			hand4.add(cards[i+3]);
		}
		players = new Player[4];
		players[0] = new Player("Player1", hand1);
		players[1]= new Player("Player2", hand2);
		players[2]= new Player("Player3", hand3);
		players[3] = new Player("Player4", hand4);
		
		for(int i=0; i<4; i++){
			if (players[i].isStarts()){
				currentPlayer =i;
				System.out.println("Starter: "+ currentPlayer);
			}
		}
		//playGame();
	}

	public int currentPlayer(){
		return currentPlayer;
	}
	public Player getCurrentPlayer(){
		return players[currentPlayer];
	}
	public Player getPlayer(int i){
		return players[i];
	}
	public int getRemainingPlayers(){
		return playersRemaining;
	}
	public boolean pass(){
		players[currentPlayer].pass();
		return updatePlayer();
	}

	public ArrayList<Card> getCardStack(){
		return stack.getLastPlayedCards();
	}
	/**
	 * updates current player to next possible player
	 * if player has passed, it wont update him to be
	 * current player.
	 * @return
	 * returns true if  updated player is a new player
	 * returns false if updated player is same as before
	 */
	private boolean updatePlayer() {
		boolean playerUpdated = false;
		int nextPlayer = currentPlayer;
		int i=0;
		int j = 0;
		while(!playerUpdated && i<playersRemaining){
			nextPlayer = (nextPlayer+1)%playersRemaining;
			
			if(!players[nextPlayer].hasPassed()){
				currentPlayer = nextPlayer;
				playerUpdated = true;
			}
			else {
				j++;
			}
			i++;
		} 
		if (j == (playersRemaining-1)){
			for (j=0; j<playersRemaining; j++){
				players[j].pass(false);
			}
			currentPlayer = (currentPlayer + 1)%playersRemaining;
			playerUpdated = true;
		}
		System.out.println("[DEBUG] i = " + i + " j= " + j +" playerUpdated = " + playerUpdated + "nextPlayer = " + nextPlayer);
		return playerUpdated;
	}
	/**
	 * once a player plays all of his cards, he is removed from the game
	 * and players remaining is decremented
	 * @param player
	 */
	private void removePlayer(Player player){
		Player temp=null;
		int i=0;
		while (i<4 && temp == null){
			if(players[i] == player)
				temp = player;
			i++;
		}
		i--;
		if (temp!=null){
			System.out.println(player.getName() + " is out of cards");
			while(i<3){
				players[i] = players[i+1];
				i++;
			}
			players[i]=temp;
		}
		playersRemaining--;
		
	}
	public boolean playHand(ArrayList<Card> playedCards){
		boolean played;
		played=stack.addCards(playedCards, players[currentPlayer]);
		if (played){
			if(!players[currentPlayer].hasMoreCards()){
				removePlayer(players[currentPlayer]);
			}
			System.out.println(updatePlayer());
		}
		return played;
	}

}

