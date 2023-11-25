package prog3_hw_antitheft;

public class Engine {
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
