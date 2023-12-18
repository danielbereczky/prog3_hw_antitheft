package prog3_hw_antitheft;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

//this class serves as the driver for the whole system
public class Logic {
	//boolean for storing the state of the alarm system
	public boolean isActive = true;
	//components of the system
	Switch hoodSwitch = new Switch();
	Switch leftDoorSwitch = new Switch();
	Switch rightDoorSwitch = new Switch();
	IgnitionKey key = new IgnitionKey();
	AlarmSiren siren = new AlarmSiren();
	Engine carEngine = new Engine();
	//this line doesn't do anything
	CountdownTimer timer;
	EngineTimer engTimer;
	
	static String code;
	String lastEnteredCode;
	//driving the app
	public static void main(String args[]){
		Window sysWindow = new Window();
	}
	//code setters and getters
	public String getCode() {
		return code;
	}
	public void setCode(String newc) {
		code = newc;
	}
	
	//this method checks whether the alarm system can be armed
	public boolean armable(){
		if(!isActive && !carEngine.getStatus() && !hoodSwitch.getSwitchState() && !leftDoorSwitch.getSwitchState() && !rightDoorSwitch.getSwitchState() && key.getState()==IgnitionKey.keyState.OFF){
			return true;
		}
		return false;
	}
	//system components status getters
	public String getHoodStatus(){
		return hoodSwitch.getStatusString();
	}
	public String getRDoorStatus() {
		return rightDoorSwitch.getStatusString();
	}
	public String getLDoorStatus() {
		return leftDoorSwitch.getStatusString();
	}
	public String getSirenStatus() {
		return siren.getStatusString();
	}
	public String getEngineStatus(){
		return carEngine.getStatusString();
	}
	//functions for toggling inputs
	public void toggleLeftDoor(JLabel DoorIndicator){
		
		if(leftDoorSwitch.getSwitchState()) {
			leftDoorSwitch.setSwitchState(false);
			DoorIndicator.setText(leftDoorSwitch.getStatusString());
		}
		else
			leftDoorSwitch.setSwitchState(true);
			DoorIndicator.setText(leftDoorSwitch.getStatusString());
	}
	public void toggleRightDoor(JLabel DoorIndicator){
		
		if(rightDoorSwitch.getSwitchState()) {
			rightDoorSwitch.setSwitchState(false);
			DoorIndicator.setText(rightDoorSwitch.getStatusString());
		}
		else
			rightDoorSwitch.setSwitchState(true);
			DoorIndicator.setText(rightDoorSwitch.getStatusString());
	}
	public void toggleHood(JLabel HoodIndicator) {
		//if the switch is open, we close it, if its not, we open it (Note: switches are closed by default)
		if(hoodSwitch.getSwitchState()) {
			hoodSwitch.setSwitchState(false);
			HoodIndicator.setText(hoodSwitch.getStatusString());
		}
		else {
			hoodSwitch.setSwitchState(true);
			HoodIndicator.setText(hoodSwitch.getStatusString());
		}
	}
	//system control logic functions
	public void arm(JLabel masterStat, JLabel hint) {
		if(armable()){
			isActive = true;
			masterStat.setText("ARMED");
			hint.setText("System successfully armed");
		}
		else{
			hint.setText("The system cannot be armed now");
		}
	}
	public void keyTamperCheck(JLabel alarmStatus, JLabel hintText) {
		if(isActive){
			//if the key is touched with an armed system, we give an instant alarm
			timer = new CountdownTimer(0,siren,alarmStatus);
			siren.setSiren(true);
			alarmStatus.setText(siren.getStatusString());
			hintText.setText("You turned the key while the system was armed. Enter the code to disarm the system!");
		}
	}
	//handling key inputs
	public void keyOff(JLabel keyStatus, JLabel engineFeedBackLabel, JLabel hintText) {
		keyStatus.setText("LOCK");
		key.setState(IgnitionKey.keyState.OFF);
		if(carEngine.getStatus()){
			carEngine.stop();
			engineFeedBackLabel.setText(carEngine.getStatusString());
			hintText.setText("The engine is now off");
		}
	}
	public void keyAcc(JLabel keyStatus) {
		keyStatus.setText("ACC");
		key.setState(IgnitionKey.keyState.ACC);
	}
	public void keyStart(JLabel keyStatus,JLabel engineFeedBackLabel, JLabel hintText) {
		keyStatus.setText("START");
		key.setState(IgnitionKey.keyState.START);
		//if the alarm is not armed and the engine is not running, we can start it up
		if(!isActive && !carEngine.getStatus() && carEngine.getStartable()){
			carEngine.start();
			engineFeedBackLabel.setText(carEngine.getStatusString());
			hintText.setText("The engine is now started");
			key.setState(IgnitionKey.keyState.ACC);
			keyStatus.setText("ACC");
			
		}
		else if(carEngine.getStatus()){
			hintText.setText("Don't crank while the engine is running!");
			key.setState(IgnitionKey.keyState.ACC);
			keyStatus.setText("ACC");	
		}
		else{
			key.setState(IgnitionKey.keyState.ACC);
			keyStatus.setText("ACC");
		}
	}
	public void enterCode(JPasswordField codeField, JLabel alarmStatus,JLabel masterStatus,JLabel hintText) {
		//if the doors are closed, we can't enter a code because the panel is physically inaccessible
		lastEnteredCode = codeField.getText();
		codeField.setText("");
		System.out.println(lastEnteredCode);
		//if the siren is already running or the timer is counting down
		if((siren.getStatus() || (timer != null && timer.isRunning())) &&  lastEnteredCode.equals(code)){
			//disarming the system
			timer.stop();
			siren.setSiren(false);
			alarmStatus.setText(siren.getStatusString());
			isActive = false;
			masterStatus.setText("DISARMED");
			hintText.setText("System disarmed. You can go ahead and start the engine in 15 seconds");
			carEngine.setStartable(true);
			engTimer = new EngineTimer(15,carEngine,hintText);
			engTimer.start();
		}	
	}
	//this function is ran whenever there is an input which can trigger the alarm
	public void checkAlarm(JLabel alarmStatus, JLabel hintText) {
		//when the system is active, we need to check for triggering conditions
		if(isActive){
			if(hoodSwitch.getSwitchState()) {
				//workaround, we have an instant alarm in case the hood is opened
				timer = new CountdownTimer(0,siren,alarmStatus);
				siren.setSiren(true);
				alarmStatus.setText(siren.getStatusString());
				hintText.setText("You opened the hood while the system was armed. Enter the code to disarm the system!");
			}
			//if we opened any of the doors while the system is active
			else if(leftDoorSwitch.getSwitchState() || rightDoorSwitch.getSwitchState()) {
				hintText.setText("You opened a door. Enter the code in 30 seconds to disarm the system!");
				//countdown
				timer = new CountdownTimer(30,siren,alarmStatus);
				timer.start();
			}
		}
	}
	
	//functions for serialization
	public void write(JLabel hintText){
		try{
			//initializing the output data stream
			FileOutputStream fileOut = new FileOutputStream("alarm.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			//writing the objects to the file
			out.writeObject(isActive);
			out.writeObject(lastEnteredCode);
			out.writeObject(hoodSwitch);
			out.writeObject(leftDoorSwitch);
			out.writeObject(rightDoorSwitch);
			out.writeObject(key);
			out.writeObject(siren);
			out.writeObject(carEngine);
			out.close();
			hintText.setText("System state saved");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void read(JLabel masterStatus, JLabel hoodStatus, JLabel leftDoorStatus,JLabel rightDoorStatus, JLabel keyStatus, JLabel alarmStatus, JLabel engineFeedBackLabel, JLabel hintText, JRadioButton hoodBtn, JRadioButton leftDoorBtn, JRadioButton rightDoorBtn,JComboBox keyBox)throws ClassNotFoundException{
		//checking whether the file to read from exists
		Path inpPath = Paths.get("alarm.ser");
		if(Files.exists(inpPath)){
			//if the file exists
			try{
				//initializing the data stream
				FileInputStream fileIn = new FileInputStream("alarm.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				//reading objects and setting feedback
				isActive = (Boolean) in.readObject();
				if(isActive){
					masterStatus.setText("ARMED");
				}
				else{
					 masterStatus.setText("DISARMED");
				}
				lastEnteredCode = (String) in.readObject();
				hoodSwitch = (Switch) in.readObject();
				hoodStatus.setText(hoodSwitch.getStatusString());
				if(hoodSwitch.getSwitchState()){
					hoodBtn.setSelected(true);
				}
				leftDoorSwitch = (Switch) in.readObject();
				if(leftDoorSwitch.getSwitchState()){
					leftDoorBtn.setSelected(true);
				}
				leftDoorStatus.setText(leftDoorSwitch.getStatusString());
				rightDoorSwitch = (Switch) in.readObject();
				if(rightDoorSwitch.getSwitchState()){
					rightDoorBtn.setSelected(true);
				}
				rightDoorStatus.setText(rightDoorSwitch.getStatusString());
				
				key = (IgnitionKey) in.readObject();
				if(key.getState() == IgnitionKey.keyState.OFF){
					keyBox.setSelectedIndex(0);
					keyStatus.setText("OFF");
					}
				else if(key.getState() == IgnitionKey.keyState.ACC){
					 keyBox.setSelectedIndex(1);
					 keyStatus.setText("ACC");
				}
				else if(key.getState() == IgnitionKey.keyState.START){
					keyBox.setSelectedIndex(2);
					keyStatus.setText("START");
				}
				siren = (AlarmSiren) in.readObject();
				alarmStatus.setText(siren.getStatusString());
				carEngine = (Engine) in.readObject();
				engineFeedBackLabel.setText(carEngine.getStatusString());
				in.close();
				//giving feedback to the user
				hintText.setText("System state loaded");
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else hintText.setText("No save found!");	
	}
	public void readCode() throws ClassNotFoundException{
		Path inPath = Paths.get("code.cod");
		if(Files.exists(inPath)) {
			try {
				FileInputStream fin = new FileInputStream("code.cod");
				ObjectInputStream in = new ObjectInputStream(fin);
				code = (String) in.readObject();
				in.close();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void writeCode(){
		try{
			FileOutputStream fout = new FileOutputStream("code.cod");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(code);
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
