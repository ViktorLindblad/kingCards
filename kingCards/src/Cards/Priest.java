package Cards;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import kingCards.Handler;
import kingCards.King;

public class Priest extends Card{

	private int attackPower,defencePower,APCost,cardID,cardCost,x,y,frameCounter;
	private Rectangle cardRectangle;
	private King king;
	private Handler handler;
	private boolean market,special;
	
	public Priest(int attackPower,int defencePower,int APCost,int cardID,int cardCost,King king,Handler handler){
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
	
	public Priest(Priest priest,King king,Handler handler){
		this.APCost = priest.getActionPointCost();
		this.attackPower = priest.getAttack();
		this.cardCost = priest.getCost();
		this.cardID = priest.getCardID();
		this.defencePower = priest.getDefencePower();
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
			king.addCardToDiscardedPile(new Priest(this,king,handler));
			System.out.println(king.getDiscardedCards().size());
		}
	}
	
	private void selectCard(){
		king.getSelectedCards().add(new Priest(this,king,handler));
		handler.getPicking().turnDone();
	}
	
	public void tick(){
		frameCounter++;
		if(handler.getMouseManager().clicked&&
				cardRectangle.contains(new Point(handler.getMouseManager().getX(),
				handler.getMouseManager().getY()))&&frameCounter>120){
			frameCounter=0;
			
			if(handler.getGameState()==Handler.MENU){
				selectCard();
			} else if(market&&handler.getGameState()==Handler.GAME){
				System.out.println("buyCard");
				buyCard();
				
			} else if(!market&&handler.getGameState()==Handler.GAME){
				playCard();
			} 
			
		}
	}
	

	
	public void render(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(cardRectangle.x, cardRectangle.y, WIDTH, HEIGHT);
	}
	
	public void setMarketPlace(boolean b) {
		market = b;
		
	}

	public void specialMove(){
		king.takeHit(-10);
	}
}
