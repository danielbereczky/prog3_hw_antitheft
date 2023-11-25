package prog3_hw_antitheft;

import java.io.Serializable;

public class Switch implements Serializable{
	private static final long serialVersionUID = 7000668541991691495L;
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
