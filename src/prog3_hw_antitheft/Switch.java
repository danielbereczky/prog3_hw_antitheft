package prog3_hw_antitheft;

public class Switch {
	private boolean isOpen;
	//setters and getters
	Switch(){
		isOpen = false;
	}
	boolean getSwitchState(){
		return isOpen;
	}
	void setSwitchState(boolean inp) {
		isOpen = inp;
	}
	String getStatusString() {
		return isOpen? "Open" : "Closed";
	}
}
