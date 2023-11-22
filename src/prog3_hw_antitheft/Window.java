package prog3_hw_antitheft;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//todo and ideas: configurable driver side, simple graphics, user manual

public class Window extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4601973850611220034L;
	public boolean isActive = true;
	public boolean engineRunning = false;
	//buttons for inputs
	JRadioButton leftDoorBtn ;
	JRadioButton rightDoorBtn;
	JRadioButton hoodBtn;
	//key
	JRadioButton keyOff;
	JRadioButton keyAcc;
	JRadioButton keyStart;
	ButtonGroup keyButtons;
	//status components
	JPanel statusPanel;
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
	
	JLabel hintText;
	
	JTextField codeField;

	//components of the system
	Switch hoodSwitch = new Switch();
	Switch leftDoorSwitch = new Switch();
	Switch rightDoorSwitch = new Switch();
	
	IgnitionKey key = new IgnitionKey();
	AlarmSiren siren = new AlarmSiren();
	
	String code = "asd";
	String lastEnteredCode;
	Window(){
		
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
		//making and setting up or GUI window
		this.setSize(640,480);
	
		this.setTitle("SATs-Simple Anti-Theft system");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		//styling
		this.getContentPane().setBackground(Color.DARK_GRAY);
		
		//input panel
		JPanel inputPanel = new JPanel();
		JLabel inputTitleLabel = new JLabel("Openable inputs:",SwingConstants.CENTER);
		
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
				if(e.getSource()==keyOff) {
					keyStatus.setText("LOCK");
					key.setState(IgnitionKey.keyState.OFF);
				}
				else if(e.getSource()==keyAcc) {
					keyStatus.setText("ACC");
					key.setState(IgnitionKey.keyState.ACC);
				}
				else if(e.getSource()==keyStart) {
					keyStatus.setText("START");
					key.setState(IgnitionKey.keyState.START);
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
		//setting up the input panel for doors, and hood
		inputTitleLabel.setOpaque(true);
		inputPanel.setBackground(Color.DARK_GRAY);
		inputPanel.setLayout(new GridLayout(0,1));
		inputPanel.setBounds(440,0,200,200);
		
		//adding elements to the input panel
		inputPanel.add(inputTitleLabel);
		inputPanel.add(leftDoorBtn);
		inputPanel.add(rightDoorBtn);
		inputPanel.add(hoodBtn);
		
		//status panel
		statusPanel = new JPanel();
		statusTitleLabel = new JLabel("System Status:",SwingConstants.CENTER);
		enginerunningLabel = new JLabel("Engine",SwingConstants.CENTER);
		engineFeedBackLabel = new JLabel("OFF",SwingConstants.CENTER);
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
		//key panel
		
		JPanel keyPanel = new JPanel();
		JLabel keyTitleLabel = new JLabel("Set key to:",SwingConstants.CENTER);
		keyTitleLabel.setOpaque(true);
		keyPanel.setLayout(new GridLayout(4,1));
		keyPanel.setBounds(220,0,200,100);
		keyPanel.setBackground(Color.DARK_GRAY);
		keyPanel.add(keyTitleLabel);
		keyPanel.add(keyOff);
		keyPanel.add(keyAcc);
		keyPanel.add(keyStart);
		
		//code panel
		JPanel codePanel = new JPanel();
		codeField = new JTextField();
		codeField.setColumns(10);
		JButton submitButton = new JButton("OK");
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				lastEnteredCode = codeField.getText();
				System.out.println(lastEnteredCode);
				//if the siren is already running or the timer is counting down
				if((siren.getStatus() || timer.isRunning()) &&  lastEnteredCode.equals(code)){
					//the system is disarmed
					timer.stop();
					siren.setSiren(false);
					alarmStatus.setText(siren.getStatusString());
					isActive = false;
					masterStatus.setText("DISARMED");
					hintText.setText("System disarmed. You can go ahead and start the engine.");
				}
			}
		});
		
		codePanel.add(new JLabel("Enter code"));
		codePanel.add(codeField);
		codePanel.add(submitButton);
		codePanel.setBounds(220,140,200,60);
		
		//hint panel
		JPanel hintPanel = new JPanel();
		hintPanel.setLayout(new FlowLayout());
		JLabel hintLabel = new JLabel("HINT: ");
		hintText = new JLabel("The car is locked, the system is armed. Try opening a door first!");
		hintPanel.add(hintLabel);
		hintPanel.add(hintText);
		hintPanel.setBounds(0, 410,650, 30);
		
		//adding the panels to the frame
		this.add(inputPanel);
		this.add(statusPanel);
		this.add(keyPanel);
		this.add(codePanel);
		this.add(hintPanel);
		//only set the JFrame visible after all elements are added
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		//empty
	}
	//logic functions
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
				timer = new CountdownTimer(3,siren,alarmStatus);
				timer.start();
			}
		}
	}
}