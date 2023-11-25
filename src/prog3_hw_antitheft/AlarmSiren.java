package prog3_hw_antitheft;

import java.io.Serializable;

public class AlarmSiren implements Serializable{
	private static final long serialVersionUID = 2581134307456467085L;
	boolean isOn;
	AlarmSiren(){
		isOn = false;
	}
	
	public void setSiren(boolean inp) {
		isOn = inp;
	}
	public boolean getStatus(){
		return isOn;
	}
	public String getStatusString() {
		return isOn? "Playing Sound": "Not active"; 
	}
}
