package kingCards;

public class Handler {

	private Game game;
	private int state;
	private MouseManager mm;
	
	public Handler(Game game){
		this.game = game;
		this.mm = game.getMouseManager();
	}
	
	public int getGameState(){
		return state;
	}
	
	public MouseManager getMouseManager(){
		return mm;
	}
	
	public Display getDisplay(){
		return game.getDisplay();
	}
	
	public int getWidth(){
		return game.getDisplay().getWidth();
	}

	public int getHeight() {
		return game.getDisplay().getHeight();
	}
	
}
