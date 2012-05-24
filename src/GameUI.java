import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class GameUI  {
	JFrame frame;
	ArrayList<JCheckBox> checkboxes;
	JPanel buttonPanel;
	JPanel cardPanel = new JPanel();
	JPanel playerPanel;
	JButton playHand = new JButton("Play Hand");
	JButton pass = new JButton("Pass");
	Game game;
	JLabel text ;
	JLabel text2 ;
	JLabel text3 ;
	JPanel cardStack;
	public GameUI(){
		buttonPanel = new JPanel();
		playerPanel = new JPanel();
		game = new Game();
		frame = new JFrame();
		checkboxes = new ArrayList<JCheckBox>();	
		cardStack = new JPanel();
		text = new JLabel();
		text2= new JLabel("",JLabel.CENTER);
		text3 = new JLabel();
		playHand.addActionListener(new ActionListener(){
			ArrayList<Card> playCards = new ArrayList<Card>();
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playCards.clear();
				// TODO Auto-generated method stub
				for (JCheckBox check : checkboxes){
					
					if (check.isSelected()){
						
						playCards.add(game.getCurrentPlayer().getCards().get(checkboxes.indexOf(check)));
					}
				}
				for (Card card : playCards){
					card.printCard();
				}
				if (game.playHand(playCards)){
					update(frame.getContentPane());
					
				}
				//game.currentPlayer = (game.currentPlayer+1)%game.getRemainingPlayers();
					
				//}
				
			}
			
		});
		pass.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (game.pass())
					update(frame.getContentPane());
			}
			
		});

		buttonPanel.add(pass);
		buttonPanel.add(playHand);
		update(frame.getContentPane());
		frame.setVisible(true);
		
		frame.setBounds(100, 100, 1500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 
		 
	}
	public void update(Container pane){
		pane.removeAll();
		//pane.add(buttonPanel, BorderLayout.PAGE_START);
		playerPanel.removeAll();
		playerPanel.add(buttonPanel);
		cardPanel = new JPanel();
		checkboxes.clear();
		for (Card card : game.getCurrentPlayer().getCards()){
			checkboxes.add(new JCheckBox(card.getImage()));
			checkboxes.get(checkboxes.size()-1).setBorderPainted(true);
			cardPanel.add(checkboxes.get(checkboxes.size()-1));
			UIManager.put("checkboxes.get(checkboxes.size()-1).focus",Color.RED);
		}
		
		playerPanel.add(cardPanel);
		text.setText("<html><body>Player: " + game.getPlayer((game.currentPlayer()+1)%4).getName() + "<br>Cards Remaining:" + game.getPlayer((game.currentPlayer()+1)%4).getCards().size() + "<br>Passed? " +game.getPlayer((game.currentPlayer()+1)%4).hasPassed() + "</body></html>");
		text2.setText("Player: " + game.getPlayer((game.currentPlayer()+2)%4).getName() + " Cards Remaining:" + game.getPlayer((game.currentPlayer()+2)%4).getCards().size() + "Passed? " +game.getPlayer((game.currentPlayer()+2)%4).hasPassed());
		text3.setText("<html><body>Player: " + game.getPlayer((game.currentPlayer()+3)%4).getName() + "<br>Cards Remaining:" + game.getPlayer((game.currentPlayer()+3)%4).getCards().size() + "<br>Passed? " +game.getPlayer((game.currentPlayer()+3)%4).hasPassed()+ "</body></html>");
		
		cardStack.removeAll(); 
		for(Card card : game.getCardStack()){
			cardStack.add(new JLabel (card.getImage(), JLabel.CENTER));
		}
		pane.add(cardStack, BorderLayout.CENTER);
		pane.add(playerPanel, BorderLayout.PAGE_START);
		pane.add(text, BorderLayout.LINE_START);
		pane.add(text2, BorderLayout.PAGE_END);
		pane.add(text3, BorderLayout.LINE_END);
		frame.repaint();
		frame.validate();
	}
	public static void main(String args[]){
		GameUI game = new GameUI();
	}
}
