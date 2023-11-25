package prog3_hw_antitheft;

import java.io.Serializable;

public class IgnitionKey implements Serializable{
	private static final long serialVersionUID = -8121539382114570081L;
	public enum keyState{
		OFF,
		ACC,
		START
	}
	
	private keyState ignStatus = keyState.OFF;
	private boolean isEnabled;
	
	public keyState getState() {
		return ignStatus;
	}
	public void setState(keyState stat) {
		ignStatus = stat;
	}
	public boolean getIsEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean inp) {
		isEnabled = inp;
	}
	
}
