package Cards;

import kingCards.Handler;
import kingCards.King;

public class Priest extends Card{

	public Priest(int attackPower,int defencePower,int APCost,int cardID,int cardCost,King king,Handler handler){
		super(attackPower, defencePower, APCost, cardID, cardCost, king, handler);
		special = true;
	}
	
	public void specialMove(){
		king.takeHit(-10);
		
	}
}
