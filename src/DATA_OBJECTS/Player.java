package DATA_OBJECTS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Player {
	

	private String name;
	private ArrayList <Card> cards;
	private boolean starts;
	private boolean stillPlaying;
	private boolean hasPassed;
	
	public Player (String name, ArrayList<Card> cards){
		this.name=name;
		this.cards=cards;
		hasPassed = false;
		Collections.sort(this.cards);
		stillPlaying = true;
		if (cards.get(0).getName().equals("3") && cards.get(0).getSuit().equals("Spade")){
			starts = true;
		}
		printHand();
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public boolean isStarts() {
		return starts;
	}
	public boolean isStillPlaying() {
		return stillPlaying;
	}
	public boolean hasMoreCards(){
		if (cards.isEmpty()){
			stillPlaying=false;
			return false;
		}
		return true;
	}
	public boolean hasPassed(){
		return hasPassed;
	}
	public boolean pass(boolean pass){
		hasPassed = pass;
		return hasPassed;
	}
	public void pass(){
		hasPassed = true;
	}
	
	public void removeCards(ArrayList<Card> removedCards){
		for(int i=0; i<removedCards.size(); i++){
			cards.remove(removedCards.get(i));
		}
		if (cards.isEmpty())
			stillPlaying = false;
	}
	public void printHand(){
		System.out.println("~~~" + name + "~~~" );
		for(int i=0; i<cards.size(); i++){
			System.out.println(i +". "+ " Card: " + cards.get(i).getName() + " Suit: " + cards.get(i).getSuit());
			
		}
		System.out.println();
	}
	
}