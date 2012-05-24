import javax.swing.ImageIcon;


public class Card implements Comparable{

	private int value;
	private String name;
	private String suit;
	private int suitValue;
	private boolean threespades;
	private ImageIcon image;
	//Constructor
	public Card (int value, String name, String suit, int suitValue){
		System.out.println("[DBUG] Value = " + value);
		this.value=value;
		this.name=name;
		this.suit=suit;
		this.suitValue=suitValue;
		
		if (name.equals("3") && suit.equals("Spade"))
			threespades=true;
		else
			threespades=false;
		
		image = new ImageIcon("cards/" + name + suit.toLowerCase().charAt(0)+ ".gif");
		
	}
	
	/**Getters**/
	public ImageIcon getImage(){
		return image;
	}
	public int getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	public String getSuit() {
		return suit;
	}
	public int getSuitValue(){
		return suitValue;
	}
	
	public void printCard(){
		System.out.println("Card: " + name + " Suit: " + suit );
	}
	public boolean starter(){
		return threespades;
	}
	@Override
	public int compareTo(Object card) {
		Card inCard= (Card) card;
		if(value>inCard.getValue())
			return 1;
		
		else if(inCard.getValue()>value)
			return -1;
		else{
			if(suitValue>inCard.suitValue)
				return 1;
			else
				return -1;
		}
			
	}

}
