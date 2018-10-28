package kingCards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import Cards.Assasin;
import Cards.Barricade;
import Cards.Card;
import Cards.Priest;

public class Picking {
	
	private ArrayList<Card> redCards,blueCards;
	private King redKing,blueKing;
	private Handler handler;
	private Rectangle done;
	private Timer timer;
	private int counter,turnCounter,animate;
	private boolean myTurn,alarm;
	
	public Picking(King redKing,King blueKing, Handler handler){
		this.handler = handler;
		this.redKing = redKing;
		this.blueKing = blueKing;
		turnCounter++;
		this.redCards = new ArrayList<Card>();
		this.blueCards = new ArrayList<Card>();
		redCards.add(new Assasin(0,0,2,2,2,redKing,handler));
		redCards.add(new Priest(0,0,1,3,5,redKing,handler));
		redCards.add(new Barricade(0,0,1,4,2,redKing,handler));
		alarm = true;
		blueCards.add(new Assasin(0,0,2,2,2,blueKing,handler));
		blueCards.add(new Priest(0,0,1,3,5,blueKing,handler));
		blueCards.add(new Barricade(0,0,1,4,2,blueKing,handler));
		
		myTurn = true;
		int x=Card.WIDTH,y=Card.HEIGHT*2,counter=0;
		done = new Rectangle(handler.getWidth()/2-Card.HEIGHT/2, handler.getHeight()-Card.HEIGHT, Card.HEIGHT, Card.WIDTH);
		for(Card card:redCards){
			if(counter>=10){
				y+=Card.HEIGHT*2;
			}
			card.setLocation(x, y);
			counter++;
			x+=Card.WIDTH*2;
		}
		x=Card.WIDTH;y=Card.HEIGHT*2;counter=0;
		for(Card card:blueCards){
			if(counter>=10){
				y+=Card.HEIGHT*2;
			}
			card.setLocation(x, y);
			counter++;
			x+=Card.WIDTH*2;
		}
		timer = new Timer(45);
	}
	
	public void turnDone(){
		turnCounter++;
		System.out.println("new turn number: "+turnCounter);
		myTurn = !myTurn;
		if(turnCounter>6){
			System.out.println("5 cards have been picked each, starting the game");
			redKing.setUpMarket();
			blueKing.setUpMarket();
			handler.setState(Handler.GAME);
		}
		animate = 120;
		alarm = true;
	}
	
	private void setTimer(){
		timer = new Timer(45);
	}
	
	private void random(){
		if(myTurn){
			int index = redCards.size()-1;
			Random random = new Random();
			redKing.getSelectedCards().add(new Card(redCards.get(random.nextInt(index)),redKing,handler));
		} else {
			int index = blueCards.size()-1;
			Random random = new Random();
			blueKing.getSelectedCards().add(new Card(blueCards.get(random.nextInt(index)),blueKing,handler));
		}
		turnDone();
	}
	
	public void tick(){
		animate--;
		if(animate<0){
			if(alarm){
				alarm= false;
				setTimer();
			}
			timer.tick();
			if(timer.getAlert()){
				random();
			}
			if(myTurn){
				for(Card card:redCards){
					card.tick();
				}
			} else {
				for(Card card:blueCards){
					card.tick();
				}
			}
			if(handler.getMouseManager().clicked&&done.contains(new Point(handler.getMouseManager().getX(),
																		handler.getMouseManager().getY()))){
				handler.setState(Handler.GAME);
			}
		} else {
			//ANIMATION HERE
		}

		
	}
	
	public void render(Graphics g){
		if(myTurn){
			g.drawString("Reds turn", handler.getWidth()/2,Card.HEIGHT+Card.HEIGHT/2);
			for(Card card:redCards){
				card.render(g);
			}

		} else {
			g.drawString("Blues turn", handler.getWidth()/2,Card.HEIGHT+Card.HEIGHT/2);
			for(Card card:blueCards){
				card.render(g);
			}
		}

		g.drawString(timer.getCountDownTimer(), handler.getWidth()/2,Card.HEIGHT);
		g.setColor(Color.GREEN);
		g.fillRect(done.x,done.y,done.width,done.height);
	}
	
}
