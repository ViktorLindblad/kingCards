package kingCards;


public class Timer {

	private float alarm,tempAlarm;
	private int alarmInt;
	private float start,second=1000000000;
	private float time,lastTime;
	private boolean alert;
	
	public Timer(){
		this(0);
	}
	
	public Timer(float alarm){
		if(alarm!=0){
			this.alarm = alarm*1000000000;
			this.tempAlarm = alarm*1000000000;
			start = System.nanoTime();
			alert=false;
		} else {
			start = System.nanoTime();
			alert=false;
		}
		lastTime = System.nanoTime();
	}
	
	public Timer(int alarm){
		if(alarm!=0){
			this.alarmInt = alarm;
			this.alarm = (float)alarm*1000000000;
			start = System.nanoTime();
			alert=false;
		} else {
			start = System.nanoTime();
			alert=false;
		}
		lastTime = System.nanoTime();
	}
	
	
	public void tick(){
		time = System.nanoTime();
		if(time-lastTime>=second){
			alarmInt--;
			lastTime=System.nanoTime();
		}
		if((time-start)>=alarm){
			System.out.println((time-start)+" "+alarm);
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
	
	public String getCountDownTimer(){
		return Integer.toString(alarmInt);
	}
}
