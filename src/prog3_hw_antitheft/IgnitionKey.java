package prog3_hw_antitheft;

public class IgnitionKey {
	public enum keyState{
		OFF,
		ON,
		ACC,
		START
	}
	private keyState ignStatus;
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
