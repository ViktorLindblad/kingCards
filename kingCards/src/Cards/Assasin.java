package Cards;


import kingCards.Handler;
import kingCards.King;

public class Assasin extends Card{

	public Assasin(int attackPower,int defencePower,int APCost,int cardID,int cardCost,King king,Handler handler){
		super(attackPower, defencePower, APCost, cardID, cardCost, king, handler);
		special = true;
	}
	public Assasin(Assasin a,King king,Handler handler){
		super(a.getAttack(), a.getDefencePower(), a.getActionPointCost(), a.getCardID(), a.getCost(), king, handler);
		special = true;
	}
	
	public void buyCard(){
		if(cardCost<=king.getCoins()){
			
			king.addCoins(-cardCost);
			king.addCardToDiscardedPile(new Assasin(this,king,handler));
			System.out.println(king.getDiscardedCards().size());
		}
	}
	
	public void selectCard(){

		System.out.println("Card selected"+king.getSelectedCards().size());
		king.getSelectedCards().add(new Assasin(this,king,handler));
		handler.getPicking().turnDone();
	}
	

	public void specialMove(){
		if(king.getColor()==King.RED) {
			int dp = handler.getBlueKing().getKingDefencePower();
			handler.getBlueKing().addDefencePower(-dp);
			dp=dp/2;
			handler.getBlueKing().addDefencePower(dp);
		} else {
			int dp = handler.getRedKing().getKingDefencePower();
			handler.getRedKing().addDefencePower(-dp);
			dp=dp/2;
			handler.getRedKing().addDefencePower(dp);
		}
	}
}
