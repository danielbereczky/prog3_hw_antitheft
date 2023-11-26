package prog3_hw_antitheft;

import java.io.Serializable;

public class Engine implements Serializable{
	private static final long serialVersionUID = -4793174854522421161L;
	private boolean isRunning = false;
	private boolean isStartable = false;
	public void start(){
		if(!isRunning && isStartable){
			isRunning = true;
		}
	}
	public void stop(){
		if(isRunning){
			isRunning = false;
		}
	}
	public String getStatusString(){
		return isRunning? "Running" : "Off";
	}
	public boolean getStatus(){
		return isRunning;
	}
	public boolean getStartable() {
		return isStartable;
	}
	public void setStartable(Boolean inp){
		isStartable = inp;
	}
}
