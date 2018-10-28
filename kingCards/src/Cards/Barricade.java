package Cards;

import kingCards.Handler;
import kingCards.King;

public class Barricade extends Card {

	public Barricade(int attackPower, int defencePower, int APCost, int cardID, int cardCost, King king,
			Handler handler) {
		super(attackPower, defencePower, APCost, cardID, cardCost, king, handler);
		special = true;
	}
	
	public Barricade(Barricade a,King king,Handler handler){
		super(a.getAttack(), a.getDefencePower(), a.getActionPointCost(), a.getCardID(), a.getCost(), king, handler);
		special = true;
	}
	
	public void buyCard(){
		if(cardCost<=king.getCoins()){
			
			king.addCoins(-cardCost);
			king.addCardToDiscardedPile(new Barricade(this,king,handler));
			System.out.println(king.getDiscardedCards().size()+"barricade");
		}
	}
	
	public void selectCard(){

		System.out.println("Card selected inside Barricade"+king.getSelectedCards().size());
		king.getSelectedCards().add(new Barricade(this,king,handler));
		handler.getPicking().turnDone();
	}
	
	public void specialMove() {
		king.addDefencePower(king.getKingDefencePower());
	}

}
