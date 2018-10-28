package kingCards;

import java.awt.Canvas;
import java.awt.Dimension;
import java.lang.reflect.WildcardType;

import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	private int width,height;
	
	public Display(int width, int height){
		frame = new JFrame();
		this.width = width;
		this.height  =height;
		frame.setSize(width,height);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		


		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
	
		
		frame.add(canvas);
		frame.pack();

		canvas.createBufferStrategy(3);
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	
	public static void main(String[] args){
		Game game = new Game(1080,680);
	}
}
