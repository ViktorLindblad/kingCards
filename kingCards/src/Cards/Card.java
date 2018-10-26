package Cards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import kingCards.Handler;
import kingCards.King;

public class Card {

	public static final int WIDTH=64,HEIGHT=128,ASSASSIN=2,PRIEST=3;

	protected int attackPower,defencePower,APCost,cardID,cardCost,x,y,frameCounter;
	protected Rectangle cardRectangle;
	protected King king;
	protected Handler handler;
	protected boolean market,special;
	
	
	public Card(int attackPower,int defencePower,int APCost,int cardID,int cardCost,King king,Handler handler){
		this.APCost = APCost;
		this.attackPower = attackPower;
		this.cardCost = cardCost;
		this.cardID = cardID;
		this.defencePower = defencePower;
		cardRectangle = new Rectangle(0,0,WIDTH,HEIGHT);
		this.king = king;
		this.handler = handler;
		special = true;
	}
	
	public Card(Card card,King king,Handler handler){
		this.APCost = card.getActionPointCost();
		this.attackPower = card.getAttack();
		this.cardCost = card.getCost();
		this.cardID = card.getCardID();
		this.defencePower = card.getDefencePower();
		cardRectangle = new Rectangle(0,0,WIDTH,HEIGHT);
		this.king = king;
		this.handler = handler;
	}

	public int getAttack() {return attackPower;}
	public int getDefencePower() {return defencePower;}
	public int getActionPointCost() {return APCost;}
	public int getCardID() {return cardID;}
	public int getCost() {return cardCost;}
	public Rectangle getRectangle() {return cardRectangle;}
	public boolean getMarketPlace() {return market;}
	public boolean haveSpecialMove(){return special;}
	public int getX(){return x;}
	public int getY(){return y;}
	
	public void playCard() {
		if(king.getKingActionPoints()>=APCost){
			king.addAttackPower(attackPower);
			king.addDefencePower(defencePower);
			king.removeActionPoint(APCost);
			king.addCardToDiscardedPile(this);
		} else {
			toExpensive();
		}
	}
	
	private void toExpensive(){
		
	}
	
	public void discardCard() {
		king.addCardToDiscardedPile(this);
	}
	
	public void setLocation(int x, int y){
		cardRectangle.setLocation(x,y);
	}
	
	public void setX(int x){
		this.x = x;
		
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	private void buyCard(){
		if(cardCost<=king.getCoins()){
			
			king.addCoins(-cardCost);
			king.addCardToDiscardedPile(new Card(this,king,handler));
			System.out.println(king.getDiscardedCards().size());
		}
	}
	
	public void selectCard(){

		System.out.println("Card selected"+king.getSelectedCards().size());
		king.getSelectedCards().add(new Card(this,king,handler));
		handler.getPicking().turnDone();
	}
	
	public void tick(){
		System.out.println(king.getSelectedCards().size());
		frameCounter++;
		System.out.println(handler.getMouseManager().clicked +" "+handler.getMouseManager().getX() +" "+handler.getMouseManager().getY());
		if(handler.getMouseManager().clicked&&
				cardRectangle.contains(new Point(handler.getMouseManager().getX(),
				handler.getMouseManager().getY()))&&frameCounter>120){
			
			frameCounter=0;

			if(handler.getGameState()==Handler.PICKING){
				System.out.println("selecting");
				selectCard();
			} else if(market&&handler.getGameState()==Handler.GAME){
				System.out.println("buyCard");
				buyCard();
				
			} else if(!market&&handler.getGameState()==Handler.GAME){
				playCard();
			} 
			
		}
	}
	
	public void specialMove(){
		
	}
	
	public void render(Graphics g){
		if(cardID==ASSASSIN){
			g.setColor(Color.ORANGE);
		} else if(cardID==PRIEST){
			g.setColor(Color.blue);
		}
		g.drawRect(cardRectangle.x, cardRectangle.y, WIDTH, HEIGHT);
	}
	
	public void setMarketPlace(boolean b) {
		market = b;
		
	}

	
}
