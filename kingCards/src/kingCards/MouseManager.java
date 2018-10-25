package kingCards;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	private int mouseMovedX, mouseMovedY;
	private int x,y;
	private boolean drawn;
	public boolean button1,button3,clicked,notclicked,dragged;
	
	public MouseManager(){
		button1 = false;
		button3 = false;
		drawn = false;
		clicked = false;
		notclicked = false;
		dragged=false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getMouseMovedX(){
		return mouseMovedX;
	}
	
	public int getMouseMovedY(){
		return mouseMovedY;
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1){
			clicked = true;
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			notclicked = true;
		}

	}

	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		x=mouseMovedX;
		y=mouseMovedY;

	}

	public void mouseReleased(MouseEvent e) {
		clicked = false;
		notclicked=false;
	}

	public void mouseDragged(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
		dragged=true;
	}

	public void mouseMoved(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
	}

	
	
}
