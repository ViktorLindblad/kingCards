package Cards;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Card {

	public static final int WIDTH=32,HEIGHT=64;
	
	abstract int getAttack();
	abstract int getDefencePower();
	abstract int getActionPointCost();
	abstract int getCardID();
	abstract int getCost();
	abstract Rectangle getRectangle();
	abstract void playCard();
	abstract void discardCard();
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void setX(int x);
	public abstract void setY(int y);
	public abstract void setLocation(int x, int y);
	public abstract void setMarketPlace(boolean b);
	public abstract boolean getMarketPlace();
	
}
