package Cards;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import kingCards.Handler;
import kingCards.King;

public class DefensiveCard extends Card{

	private int attackPower,defencePower,APCost,cardID,cardCost,x,y;
	private Rectangle cardRectangle;
	private King king;
	private Handler handler;
	private boolean market,special;
	
	public DefensiveCard(int attackPower,int defencePower,int APCost,int cardID,int cardCost,King king,Handler handler){
		this.APCost = APCost;
		this.attackPower = attackPower;
		this.cardCost = cardCost;
		this.cardID = cardID;
		this.defencePower = defencePower;
		cardRectangle = new Rectangle(0,0,WIDTH,HEIGHT);
		this.king = king;
		this.handler = handler;
		special = false;
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
	
	public void tick(){
		
		if(handler.getMouseManager().clicked&&
				cardRectangle.contains(new Point(handler.getMouseManager().getX(),
				handler.getMouseManager().getY()))){
			
			playCard();
		}
	}

	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.drawRect(cardRectangle.x, cardRectangle.y, 32, 64);
	}

	public void setMarketPlace(boolean b) {
		market = b;
		
	}



		

}


