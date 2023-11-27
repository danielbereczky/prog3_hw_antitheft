package prog3_hw_antitheft;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//TODO : OUTSORCING LOGIC TO A SEPARATE JAVA

public class Window extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4601973850611220034L;
	public boolean isActive = true;
	
	//buttons for inputs
	JRadioButton leftDoorBtn ;
	JRadioButton rightDoorBtn;
	JRadioButton hoodBtn;
	//key
	JRadioButton keyOff;
	JRadioButton keyAcc;
	JRadioButton keyStart;
	ButtonGroup keyButtons;
	//panels
	JPanel statusPanel;
	JPanel IOPanel;
	JPanel inputPanel;
	JPanel keyPanel;
	JPanel armPanel;
	JPanel codePanel;
	JPanel hintPanel;
	JPanel helpPanel;
	JPanel codeChangePanel;
	//feedback labels
	JLabel statusTitleLabel;
	JLabel enginerunningLabel;
	JLabel engineFeedBackLabel;
	JLabel masterStatus;
	JLabel leftDoorTitleLabel;
	JLabel leftDoorStatus;
	JLabel rightDoorTitleLabel;
	JLabel rightDoorStatus;
	JLabel hoodTitleLabel;
	JLabel hoodStatus;
	JLabel alarmStatusTitleLabel;
	JLabel alarmStatus;
	JLabel keyStatus;
	JLabel keyStatusTitleLabel;
	CountdownTimer timer;
	EngineTimer engTimer;
	
	JLabel hintText;
	
	JTextField codeField;

	//components of the system
	Switch hoodSwitch = new Switch();
	Switch leftDoorSwitch = new Switch();
	Switch rightDoorSwitch = new Switch();
	IgnitionKey key = new IgnitionKey();
	AlarmSiren siren = new AlarmSiren();
	Engine carEngine = new Engine();
	
	static String code;
	String lastEnteredCode;
	Window(){
		try{
			readCode();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		//making and setting up or GUI window
		this.setSize(640,480);
		this.setTitle("SATs-Simple Anti-Theft system");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);

		//setting up panels
		setStatusPanel();
		setIOPanel();
		setInputPanel();
		setKeyPanel();
		setArmPanel();
		setCodePanel();
		setHintPanel();
		setHelpPanel();
		codeChangePanel();
		//adding the panels to the frame
		this.add(inputPanel);
		this.add(statusPanel);
		this.add(keyPanel);
		this.add(codePanel);
		this.add(hintPanel);
		this.add(IOPanel);
		this.add(helpPanel);
		this.add(armPanel);
		this.add(codeChangePanel);
		//only set the JFrame visible after all elements are added
		this.setVisible(true);
	}
	public void setStatusPanel(){
		//status panel
		statusPanel = new JPanel();
		statusTitleLabel = new JLabel("System Status:",SwingConstants.CENTER);
		enginerunningLabel = new JLabel("Engine",SwingConstants.CENTER);
		engineFeedBackLabel = new JLabel(carEngine.getStatusString(),SwingConstants.CENTER);
		masterStatus = new JLabel("ARMED",SwingConstants.CENTER);
		leftDoorTitleLabel = new JLabel("Left door",SwingConstants.CENTER);
		leftDoorStatus = new JLabel(leftDoorSwitch.getStatusString(),SwingConstants.CENTER);
		rightDoorTitleLabel = new JLabel("Right door",SwingConstants.CENTER);
		rightDoorStatus = new JLabel(rightDoorSwitch.getStatusString(),SwingConstants.CENTER);
		hoodTitleLabel = new JLabel("Hood",SwingConstants.CENTER);
		hoodStatus = new JLabel(hoodSwitch.getStatusString(),SwingConstants.CENTER);
		alarmStatusTitleLabel = new JLabel("Alarm Siren",SwingConstants.CENTER);
		alarmStatus = new JLabel(siren.getStatusString(),SwingConstants.CENTER);
		keyStatusTitleLabel = new JLabel("Ignition key",SwingConstants.CENTER);
		keyStatus = new JLabel("LOCK",SwingConstants.CENTER);
		
		
		masterStatus.setOpaque(true);
		statusTitleLabel.setOpaque(true);
		enginerunningLabel.setOpaque(true);
		engineFeedBackLabel.setOpaque(true);
		leftDoorTitleLabel.setOpaque(true);
		leftDoorStatus.setOpaque(true);
		rightDoorTitleLabel.setOpaque(true);
		rightDoorStatus.setOpaque(true);
		hoodTitleLabel.setOpaque(true);
		hoodStatus.setOpaque(true);
		alarmStatusTitleLabel.setOpaque(true);
		alarmStatus.setOpaque(true);
		keyStatusTitleLabel.setOpaque(true);
		keyStatus.setOpaque(true);
		
		
		statusPanel.setBackground(Color.DARK_GRAY);
		statusPanel.setLayout(new GridLayout(7,2));
		statusPanel.setBounds(0,0,200,400);
		//status labels
		statusPanel.add(statusTitleLabel);
		statusPanel.add(masterStatus);
		statusPanel.add(enginerunningLabel);
		statusPanel.add(engineFeedBackLabel);
		statusPanel.add(leftDoorTitleLabel);
		statusPanel.add(leftDoorStatus);
		statusPanel.add(rightDoorTitleLabel);
		statusPanel.add(rightDoorStatus);
		statusPanel.add(hoodTitleLabel);
		statusPanel.add(hoodStatus);
		statusPanel.add(alarmStatusTitleLabel);
		statusPanel.add(alarmStatus);
		statusPanel.add(keyStatusTitleLabel);
		statusPanel.add(keyStatus);
	}
	public void setIOPanel(){
		//IO panel
		IOPanel = new JPanel();
		JLabel IOLabel = new JLabel("Save/Load state",SwingConstants.CENTER);
		IOLabel.setOpaque(true);
		IOPanel.setBackground(Color.DARK_GRAY);
		IOPanel.setLayout(new GridLayout(0,1));
		IOPanel.setBounds(440,300,200,100);
		IOPanel.add(IOLabel);
		
		JButton loadBtn = new JButton("Load");
		JButton writeBtn = new JButton("Save");
		
		loadBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					read();
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		writeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				write();
			}
		});
		IOPanel.add(writeBtn);
		IOPanel.add(loadBtn);
	}
	public void setInputPanel(){
		//input panel
		inputPanel = new JPanel();
		JLabel inputTitleLabel = new JLabel("Openable inputs:",SwingConstants.CENTER);
		//setting up the input panel for doors, and hood
		inputTitleLabel.setOpaque(true);
		inputPanel.setBackground(Color.DARK_GRAY);
		inputPanel.setLayout(new GridLayout(0,1));
		inputPanel.setBounds(440,0,200,200);
		leftDoorBtn = new JRadioButton("Left Door");
		rightDoorBtn = new JRadioButton("Right Door");
		hoodBtn = new JRadioButton("Hood");
		
		ActionListener hoodListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if the hood is open
				if(e.getSource()==hoodBtn){
					//if the switch is open, we close it, if its not, we open it (Note: switches are closed by default)
					if(hoodSwitch.getSwitchState()) {
						hoodSwitch.setSwitchState(false);
						hoodStatus.setText(hoodSwitch.getStatusString());
					}
					else {
						hoodSwitch.setSwitchState(true);
						hoodStatus.setText(hoodSwitch.getStatusString());
					}
				}
				checkAlarm();
			}
		};
		hoodBtn.addActionListener(hoodListener);
		
		ActionListener lDoorListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==leftDoorBtn) {
					if(leftDoorSwitch.getSwitchState()) {
						leftDoorSwitch.setSwitchState(false);
						leftDoorStatus.setText(leftDoorSwitch.getStatusString());
					}
					else
						leftDoorSwitch.setSwitchState(true);
						leftDoorStatus.setText(leftDoorSwitch.getStatusString());
				}
				checkAlarm();
			}
		};
		leftDoorBtn.addActionListener(lDoorListener);
		ActionListener rDoorListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==rightDoorBtn) {
					if(rightDoorSwitch.getSwitchState()) {
						rightDoorSwitch.setSwitchState(false);
						rightDoorStatus.setText(rightDoorSwitch.getStatusString());
					}
					else
						rightDoorSwitch.setSwitchState(true);
						rightDoorStatus.setText(rightDoorSwitch.getStatusString());
				}
				checkAlarm();
			}
		};
		rightDoorBtn.addActionListener(rDoorListener);
		//adding elements to the input panel
		inputPanel.add(inputTitleLabel);
		inputPanel.add(leftDoorBtn);
		inputPanel.add(rightDoorBtn);
		inputPanel.add(hoodBtn);
	}
	public void setArmPanel() {
		//arming panel	
		armPanel = new JPanel();
		JButton armBtn = new JButton("Arm alarm system");
		armPanel.setBackground(Color.DARK_GRAY);
		armPanel.setLayout(new GridLayout(0,1));
		armPanel.setBounds(440,250,200,40);
		armBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(armable()){
					isActive = true;
					masterStatus.setText("ARMED");
					hintText.setText("System successfully armed");
				}
				else{
					hintText.setText("The system cannot be armed now");
				}
			}
		});
		armPanel.add(armBtn);
	}
	public void setKeyPanel(){
		//key panel
		
				//Key
				keyOff = new JRadioButton("LOCK");
				keyAcc = new JRadioButton("ON/ACC");
				keyStart = new JRadioButton("START");
				
				ActionListener keyListener = new ActionListener() {
					public void actionPerformed(ActionEvent e){
						if(isActive){
							//if the key is touched with an armed system, we give an instant alarm
							timer = new CountdownTimer(0,siren,alarmStatus);
							siren.setSiren(true);
							alarmStatus.setText(siren.getStatusString());
							hintText.setText("You turned the key while the system was armed. Enter the code to disarm the system!");
						}
						//in real life, the ignition cylinder has a spring in it, which returns the key to "ACC" from "START" in order to avoid cranking while the engine is running. This behavior is coded here
						if(e.getSource()==keyOff) {
							keyStatus.setText("LOCK");
							key.setState(IgnitionKey.keyState.OFF);
							if(carEngine.getStatus()){
								carEngine.stop();
								engineFeedBackLabel.setText(carEngine.getStatusString());
								hintText.setText("The engine is now off");
							}
						}
						else if(e.getSource()==keyAcc) {
							keyStatus.setText("ACC");
							key.setState(IgnitionKey.keyState.ACC);
						}
						else if(e.getSource()==keyStart) {
							keyStatus.setText("START");
							key.setState(IgnitionKey.keyState.START);
							//if the alarm is not armed and the engine is not running, we can start it up
							if(!isActive && !carEngine.getStatus() && carEngine.getStartable()){
								carEngine.start();
								engineFeedBackLabel.setText(carEngine.getStatusString());
								hintText.setText("The engine is now started");
								key.setState(IgnitionKey.keyState.ACC);
								keyStatus.setText("ACC");
								keyAcc.setSelected(true);
							}
							else if(carEngine.getStatus()){
								hintText.setText("Don't crank while the engine is running!");
								key.setState(IgnitionKey.keyState.ACC);
								keyStatus.setText("ACC");
								keyAcc.setSelected(true);
							}
							else{
								key.setState(IgnitionKey.keyState.ACC);
								keyStatus.setText("ACC");
								keyAcc.setSelected(true);
							}
						}	
					}
				};
				
				//we set the key to LOCK by default
				keyOff.setSelected(true);
				//setting up the key panel
				keyButtons = new ButtonGroup();
				keyButtons.add(keyOff);
				keyButtons.add(keyAcc);
				keyButtons.add(keyStart);
				//adding the actionListener for keys
				keyOff.addActionListener(keyListener);
				keyAcc.addActionListener(keyListener);
				keyStart.addActionListener(keyListener);
				
				keyPanel = new JPanel();
				JLabel keyTitleLabel = new JLabel("Set key to:",SwingConstants.CENTER);
				keyTitleLabel.setOpaque(true);
				keyPanel.setLayout(new GridLayout(4,1));
				keyPanel.setBounds(220,0,200,100);
				keyPanel.setBackground(Color.DARK_GRAY);
				keyPanel.add(keyTitleLabel);
				keyPanel.add(keyOff);
				keyPanel.add(keyAcc);
				keyPanel.add(keyStart);
	}
	public void setCodePanel() {
		//code panel
				codePanel = new JPanel();
				codeField = new JPasswordField();
				codeField.setColumns(10);
				//engTimer = new EngineTimer(15,carEngine);
				JButton submitButton = new JButton("OK");
				submitButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						//if the doors are closed, we can't enter a code because its simply not possible in real life
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
				});
				
				codePanel.add(new JLabel("Enter code"));
				codePanel.add(codeField);
				codePanel.add(submitButton);
				codePanel.setBounds(220,140,200,60);
	}
	public void setHintPanel() {
		//hint panel
		hintPanel = new JPanel();
		hintPanel.setLayout(new FlowLayout());
		JLabel hintLabel = new JLabel("HINT: ");
		hintText = new JLabel("The car is locked, the system is armed. Try opening a door first!");
		hintPanel.add(hintLabel);
		hintPanel.add(hintText);
		hintPanel.setBounds(0, 410,650, 30);
		
	}
	public void setHelpPanel() {
		//help panel		
		helpPanel = new JPanel();
		JButton helpBtn = new JButton("Instructions");
		helpPanel.setBackground(Color.DARK_GRAY);
		helpPanel.setLayout(new GridLayout(0,1));
		helpPanel.setBounds(440,200,200,40);
		helpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				Manual usrMan = new Manual();
			}
		});
		helpPanel.add(helpBtn);
	}
	public void codeChangePanel() {
		//code change panel
		codeChangePanel = new JPanel();
		codeChangePanel.setBackground(Color.DARK_GRAY);
		codeChangePanel.setBounds(240,365,170,40);
		JButton changeCode = new JButton("Change system code");
		
		changeCode.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        final NewCode newc = new NewCode(code);
		        // Wait for the NewCode window to be disposed before updating the code
		        newc.addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosed(WindowEvent e) {
		                code = newc.getUpdatedCode();
		                writeCode();
		            }
		        });
		    }
		});
		codeChangePanel.add(changeCode);
	}

	public boolean armable(){
		if(!isActive && !carEngine.getStatus() && !hoodSwitch.getSwitchState() && !leftDoorSwitch.getSwitchState() && !rightDoorSwitch.getSwitchState() && key.getState()==IgnitionKey.keyState.OFF){
			return true;
		}
		return false;
	}
	public void actionPerformed(ActionEvent e) {
		//empty
	}
	//logic for triggering the alarm
	public void checkAlarm() {
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
	public void write(){
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
	public void read()throws ClassNotFoundException{
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
					keyOff.setSelected(true);
					keyStatus.setText("OFF");
					}
				else if(key.getState() == IgnitionKey.keyState.ACC){
					 keyAcc.setSelected(true);
					 keyStatus.setText("ACC");
				}
				else if(key.getState() == IgnitionKey.keyState.START){
					keyStart.setSelected(true);
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
}