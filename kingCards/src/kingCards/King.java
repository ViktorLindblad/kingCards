package kingCards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import Cards.Assasin;
import Cards.Card;
import Cards.DefensiveCard;
import Cards.SoldierCard;

public class King {
	
	public static final int RED=1,BLUE=0;

	private int HP,defencePower,attackPower,actionPoints,x,y,color,width,height,coins,buyPoints;
	private ArrayList<Card> discardedCards,cardsToPlay,cardsOnHand,selectedCards;
	private Stack<Card> removeFromHandQueue;
	private Handler handler;
	private boolean newRound,readyForNewRound;
	private Rectangle hitBox;
	private MarketPlace marketPlace;
	
	public King(int HP,int x,int y,Handler handler,int color){
		
		this.HP = HP;
		this.color = color;
		defencePower = 0;
		attackPower = 0;
		buyPoints = 1;
		hitBox = new Rectangle(x,y,132,132);
		actionPoints = 3;
		removeFromHandQueue = new Stack<Card>();
		this.handler = handler;
		this.x = x;
		this.y = y;
		width = 164;
		this.coins = 0;
		height = 164;
		newRound = true;
		readyForNewRound = false;
		discardedCards = new ArrayList<Card>();
		cardsToPlay = new ArrayList<Card>();
		cardsOnHand = new ArrayList<Card>();
		selectedCards = new ArrayList<Card>();
		addRegularCards();
		
		ArrayList<Card> marketCard = new ArrayList<Card>();
		marketCard.add(new Assasin(0,0,1,0,0,this,handler));
		
		marketPlace = new MarketPlace(marketCard,this);

	}
	
	private void addRegularCards(){
		for(int i=0; i<10; i++){
			if(i<5){
				cardsToPlay.add(new SoldierCard(4,0,1,0,0,this,handler));
			} else {
				cardsToPlay.add(new DefensiveCard(0,4,1,0,0,this,handler));
			}
		}
		Collections.shuffle(cardsToPlay);
	}
	
	public int getHP(){return HP;}
	public int getKingAttackPower(){return attackPower;}
	public int getKingDefencePower(){return defencePower;}
	public int getKingActionPoints(){return actionPoints;}
	public ArrayList<Card> getDiscardedCards(){return discardedCards;}
	public ArrayList<Card> getDeckCards(){return cardsToPlay;}
	public ArrayList<Card> getCardsOnHand(){return cardsOnHand;}
	public ArrayList<Card> getSelectedCards(){return selectedCards;}
	public boolean getReady(){return readyForNewRound;}
	public int getCoins() {return coins;}
	public int getWindowWidth(){return handler.getWidth();}
	public int getWindowHeight(){return handler.getHeight();}
	public int getBuyPoints(){return buyPoints;}
	public int getColor() {return color;}
	
	public void addBuyPoints(int bp){
		buyPoints-=bp;
	}
	
	public void addCoins(int coins){
		this.coins+=coins;
	}
	
	public void addCardToDiscardedPile(Card card){
		removeFromHandQueue.add(card);
		discardedCards.add(card);
	}
	public void addCardToHandPile(Card card){
		cardsOnHand.add(card);
	}
	public void addCardToDeckPile(Card card){
		cardsToPlay.add(card);
	}
	
	public void removeActionPoint(int ap){
		actionPoints-=ap;
	}
	
	public void addDefencePower(int DP){
		defencePower += DP;
	}
	
	public void addAttackPower(int AP){
		attackPower += AP;
	}
	
	public void setUpForNewRound(){
		attackPower = 0;
		defencePower = 0;
		actionPoints = 3;
		buyPoints = 1;
		Stack<Card> s = new Stack<Card>();
		for(Card card:cardsOnHand){
			s.push(card);
			
		}
		while(!s.isEmpty()){
			Card card = s.pop();
			cardsOnHand.remove(card);
			discardedCards.add(card);
		}
		newRound = true;
		coins++;
	}
	
	public void takeHit(int AP){
		if(defencePower>=AP){
			defencePower-=AP;
		} else {
			AP-=defencePower;
			HP-=AP;
		}
	}

	private void shuffleDeck(){
		System.out.println("deck is empty, shuffle new deck");
		Stack<Card> s = new Stack<Card>();
		for(Card card:discardedCards){
			s.push(card);
		}
		while(!s.isEmpty()){
			Card card = s.pop();
			cardsToPlay.add(card);
			discardedCards.remove(card);
		}
		Collections.shuffle(cardsToPlay);
		System.out.println(cardsToPlay.size());
	}
	
	private void drawCardsFromPile(){
		System.out.println("new cards");
		Stack<Card> s = new Stack<Card>();
		int counter= 0;
		
		if(cardsToPlay.size()<5){
			if(!cardsToPlay.isEmpty()){
				for(Card card:cardsToPlay){
					s.push(card);
					counter++;
				}
				shuffleDeck();
			} else {
				shuffleDeck();
			}
		}
		
		for(Card card:cardsToPlay){
			
			if(counter<5){
				s.push(card);
			} else {
				
			}
			counter++;
		}
		
		while(!s.isEmpty()){
			Card c  = s.pop();
			cardsToPlay.remove(c);
			cardsOnHand.add(c);
		}
	}
	


	public void tick() {
		if(handler.getGameState()==Handler.GAME){
			while(!removeFromHandQueue.isEmpty()){
				cardsOnHand.remove(removeFromHandQueue.pop());
			}
			if(newRound){
				readyForNewRound = false;
				newRound = false;
				drawCardsFromPile();
				int x = (handler.getWidth()/2)-((Card.WIDTH*4)+Card.WIDTH/2);
				for(Card card:cardsOnHand){
					card.setX(x);
					if(color==1){
						card.setY(height+Card.HEIGHT/2);
						card.setLocation(x,height+Card.HEIGHT/2);
					} else {
						card.setY(handler.getHeight()-(height+Card.HEIGHT+card.HEIGHT/2));
						card.setLocation(x,handler.getHeight()-(height+Card.HEIGHT+Card.HEIGHT/2));
					}
					
					x+=Card.WIDTH*2;
				}
			}
			marketPlace.tick();
			for(Card card:cardsOnHand){
				card.tick();
			}
			
			if(handler.getMouseManager().clicked&&
					hitBox.contains(new Point(handler.getMouseManager().getX(),handler.getMouseManager().getY()))&&actionPoints!=3){
				readyForNewRound = true;
			}
		} 
	}
	

	public void render(Graphics g) {
		if(handler.getGameState()==Handler.GAME){
			marketPlace.render(g);
			for(Card card:cardsOnHand){
				card.render(g);
			}
			if(color==1){
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLUE);
			}
			g.fillRect(x, y, width, height);
			g.setColor(Color.WHITE);
			g.drawString("AttackPower: "+attackPower, x, y+12);
			g.drawString("DefencePower: "+defencePower, x, y+24);
			g.drawString("ActionPoints: "+actionPoints, x, y+36);
			g.drawString("Health: "+HP, x, y+48);
			g.drawString("Coins: "+coins, x, y+60);
		}
	}



}
