package Cards;


import java.awt.Color;
import java.awt.Graphics;
import kingCards.Handler;
import kingCards.King;

public class DefensiveCard extends Card{

	public DefensiveCard(int attackPower,int defencePower,int APCost,int cardID,int cardCost,King king,Handler handler){
		super(attackPower, defencePower, APCost, cardID, cardCost, king, handler);
		special = false;
	}
	
	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.drawRect(cardRectangle.x, cardRectangle.y, WIDTH, HEIGHT);
	}
}


