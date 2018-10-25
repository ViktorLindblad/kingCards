package kingCards;

public class Handler {

	public static final int PICKING=0, GAME=1,MENU=2;
	
	private Game game;
	private int state;
	private MouseManager mm;
	private Picking picking;
	
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
	
	public void setState(int state) {
		this.state = state;
		
	}
	
	public Picking getPicking(){
		return picking;
	}
	
	public void setPicking(Picking p){
		picking = p;
	}
	
}
