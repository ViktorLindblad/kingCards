package kingCards;


public class Timer {

	private float alarm;
	private float start;
	private float time;
	private boolean alert;
	
	public Timer(){
		this(0);
	}
	
	public Timer(float alarm){
		if(alarm!=0){
			this.alarm = alarm*1000000000;
			start = System.nanoTime();
			alert=false;
		} else {
			start = System.nanoTime();
			alert=false;
		}
	}
	
	public void tick(){
		time = System.nanoTime();
		
		if(time-start>=alarm){
			alarm();
		}
	}
	
	/**
	 * Set alarm in seconds
	 */
	public void setNewAlarm(float f){
		this.alarm = f*1000000000;
		start = System.nanoTime();
		alert = false;
	}
	
	private void alarm(){
		alert=true;
	}
	
	public boolean getAlert(){
		return alert;
	}
	
	public float getFloatTime(){
		return ((time-start)/1000000000);
	}
	
	public String getCurrentTime(){
		
		return Float.toString((time-start)/1000000000);
	}
}
