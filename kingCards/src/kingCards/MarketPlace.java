package kingCards;

import java.awt.Graphics;
import java.util.ArrayList;

import Cards.Card;

public class MarketPlace {

	
	private ArrayList<Card> cards;
	
	public MarketPlace(ArrayList<Card> cards, King king){
		this.cards = cards;

		int y = 0;
		for(Card card:cards){
			card.setMarketPlace(true);
			if(king.getColor() == king.RED){
				card.setLocation(Card.WIDTH, Card.HEIGHT+y);
			} else {
				card.setLocation(king.getWindowWidth()-(Card.WIDTH)*2, Card.HEIGHT+y);
			}
			y+=card.HEIGHT+card.HEIGHT/2;
		}
	}
	
	
	public void tick(){
		for(Card card:cards){
			card.tick();
		}
	}
	
	public void render(Graphics g){
		for(Card card:cards){
			card.render(g);
		}
	}
	
}
