package Cards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import kingCards.Handler;
import kingCards.King;

public class Assasin extends Card{

	private int attackPower,defencePower,APCost,cardID,cardCost,x,y,frameCounter;
	private Rectangle cardRectangle;
	private King king;
	private Handler handler;
	private boolean market,special;
	
	public Assasin(int attackPower,int defencePower,int APCost,int cardID,int cardCost,King king,Handler handler){
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
	
	public Assasin(Assasin assasin,King king,Handler handler){
		this.APCost = getActionPointCost();
		this.attackPower = getAttack();
		this.cardCost = getCost();
		this.cardID = getCardID();
		this.defencePower = assasin.getDefencePower();
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
			king.addCardToDiscardedPile(new Assasin(this,king,handler));
		}
	}
	
	public void tick(){
		frameCounter++;
		if(handler.getMouseManager().clicked&&
				cardRectangle.contains(new Point(handler.getMouseManager().getX(),
				handler.getMouseManager().getY()))){
			if(market&&frameCounter>120){
				System.out.println("buyCard");
				buyCard();
				frameCounter=0;
			} else if(!market){
				playCard();
			}
		}
	}
	

	
	public void render(Graphics g){
		g.setColor(Color.ORANGE);
		g.drawRect(cardRectangle.x, cardRectangle.y, WIDTH, HEIGHT);
	}
	
	public void setMarketPlace(boolean b) {
		market = b;
		
	}

}
