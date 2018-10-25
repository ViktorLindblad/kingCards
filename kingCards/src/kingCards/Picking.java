package kingCards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import Cards.Assasin;
import Cards.Card;
import Cards.Priest;

public class Picking {
	
	private ArrayList<Card> redCards,blueCards;
	private King redKing,blueKing;
	private Handler handler;
	private Rectangle done;
	private Timer timer;
	private int counter;
	private boolean myTurn;
	
	public Picking(King redKing,King blueKing, Handler handler){
		this.handler = handler;
		this.redKing = redKing;
		this.blueKing = blueKing;
		
		this.redCards = new ArrayList<Card>();
		this.blueCards = new ArrayList<Card>();
		redCards.add(new Assasin(0,0,2,2,2,redKing,handler));
		redCards.add(new Priest(0,0,1,3,5,redKing,handler));
		
		blueCards.add(new Assasin(0,0,2,2,2,blueKing,handler));
		blueCards.add(new Priest(0,0,1,3,5,blueKing,handler));
		
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
		System.out.println("new turn");
		setTimer();
		myTurn = !myTurn;
	}
	
	private void setTimer(){
		timer = new Timer(45);
	}
	
	public void tick(){
		timer.tick();
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
		
	}
	
	public void render(Graphics g){
		if(myTurn){
			g.drawString("Reds turn", handler.getWidth()/2-timer.getCurrentTime().length(),Card.HEIGHT+Card.HEIGHT/2);
			for(Card card:redCards){
				card.render(g);
			}

		} else {
			g.drawString("Blues turn", handler.getWidth()/2-timer.getCurrentTime().length(),Card.HEIGHT+Card.HEIGHT/2);
			for(Card card:blueCards){
				card.render(g);
			}
		}

		g.drawString(timer.getCurrentTime(), handler.getWidth()/2-timer.getCurrentTime().length(),Card.HEIGHT);
		g.setColor(Color.GREEN);
		g.fillRect(done.x,done.y,done.width,done.height);
	}
	
}
