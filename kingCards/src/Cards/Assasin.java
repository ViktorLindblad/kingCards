package Cards;

import java.awt.Color;
import java.awt.Graphics;

import kingCards.Handler;
import kingCards.King;

public class Assasin extends Card{

	public Assasin(int attackPower,int defencePower,int APCost,int cardID,int cardCost,King king,Handler handler){
		super(attackPower, defencePower, APCost, cardID, cardCost, king, handler);
		special = true;
	}
	
	public void specialMove(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.ORANGE);
		g.drawRect(cardRectangle.x, cardRectangle.y, WIDTH, HEIGHT);
	}
	
}
