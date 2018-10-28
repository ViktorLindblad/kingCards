package kingCards;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	
	public static final int MENU_STATE=0,GAME_STATE=1,OPTION_STATE=2;
	
	private Display display;
	private int seconds, ticks,timer,width,height;
	private boolean running,restart,setUp;
	private Thread thread;
	private Handler handler;
	private KeyManager keyManager;
	private MouseManager mm;
	private BufferStrategy bs;
	private King redKing,blueKing;
	private Graphics g;
	private Picking picking;
	
	public Game(int width, int height){
		keyManager = new KeyManager();
		mm = new MouseManager();
		setUp = false;
		this.width = width;
		this.height = height;
		start();
	}
	
	public MouseManager getMouseManager(){
		return mm;
	}
	
	public Display getDisplay(){
		return display;
	}
	
	public void run() {
		
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps ;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		ticks = 0;
		seconds = 0;
		timer = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now-lastTime;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				delta--;
				ticks++;
			}
			if(timer>= 1000000000){
				seconds++;
				ticks=0;
				timer=0;
			}
		}
		
		stop();
		while(restart){
			start();
		}
		
	}
	
	public synchronized void start(){
		if(running){
			return;
		}
		else{
		running=true;
		restart=false;
		
		thread = new Thread(this);
		thread.start(); //Calls run();
		}
		
	}
	
	public synchronized void stop(){
		if(!running){
			return;
		}
		else{
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void init(){
		Assets.init();
		
		display = new Display(width,height);
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addMouseListener(mm);
		display.getCanvas().addMouseMotionListener(mm);
		handler = new Handler(this);
		handler.setState(Handler.PICKING);
		redKing = new King(100,(width/2)-82,0,handler,1);
		blueKing = new King(100,(width/2)-82,height-164,handler,0);
		
		picking = new Picking(redKing,blueKing,handler);
		handler.setPicking(picking);
		
	}
	
	
	private void update(){
		keyManager.tick();
		mm.tick();
		if(handler.getGameState()==Handler.PICKING){
			 picking.tick();
		} else {

			redKing.tick();
			blueKing.tick();
			if(redKing.getReady()&&blueKing.getReady()){
				redKing.getSpecialMoves();
				blueKing.getSpecialMoves();
				redKing.takeHit(blueKing.getKingAttackPower());
				blueKing.takeHit(redKing.getKingAttackPower());
				redKing.setUpForNewRound();
				blueKing.setUpForNewRound();
			}
		}
		render();
	}
	
	//Draw things to screen
	private void render(){
		if(!setUp){
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null){
			if(display.getCanvas()==null){
				return;
			}
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		
		
		g = bs.getDrawGraphics();
		
		//clear screen
		
		g.clearRect(0, 0, width, height);
		
		if(handler.getGameState()==Handler.PICKING){
			picking.render(g);
		} else {
			redKing.render(g);
			blueKing.render(g);
		}
		//End drawing
		bs.show();
		g.dispose();
		}

		
	}
	
	public King getRedKing() {return redKing;}
	public King getBlueKing() {return blueKing;}
}
