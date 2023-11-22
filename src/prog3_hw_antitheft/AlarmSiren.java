package prog3_hw_antitheft;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class AlarmSiren {
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
