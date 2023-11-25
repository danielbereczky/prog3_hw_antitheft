package prog3_hw_antitheft;

import java.io.Serializable;

public class Engine implements Serializable{
	private static final long serialVersionUID = -4793174854522421161L;
	private boolean isRunning = false;
	public void start(){
		if(!isRunning){
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
}
