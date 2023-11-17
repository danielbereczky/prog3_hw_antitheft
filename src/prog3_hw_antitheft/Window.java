package prog3_hw_antitheft;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

//todo and ideas: configurable driver side, simple graphics, user manual

public class Window extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4601973850611220034L;
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

	
	//components of the system
	Switch hoodSwitch = new Switch();
	Switch leftDoorSwitch = new Switch();
	Switch rightDoorSwitch = new Switch();
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
				if(e.getSource()==keyOff) {
					System.out.println("The key is OFF");
					keyStatus.setText("LOCK");
				}
				else if(e.getSource()==keyAcc) {
					System.out.println("The key is in ACC pos");
					keyStatus.setText("ACC");
				}
				else if(e.getSource()==keyStart) {
					System.out.println("Cranking...");
					keyStatus.setText("START");
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
		engineFeedBackLabel = new JLabel("n/a",SwingConstants.CENTER);
		masterStatus = new JLabel("ACTIVE",SwingConstants.CENTER);
		leftDoorTitleLabel = new JLabel("Left door",SwingConstants.CENTER);
		leftDoorStatus = new JLabel(leftDoorSwitch.getStatusString(),SwingConstants.CENTER);
		rightDoorTitleLabel = new JLabel("Right door",SwingConstants.CENTER);
		rightDoorStatus = new JLabel(rightDoorSwitch.getStatusString(),SwingConstants.CENTER);
		hoodTitleLabel = new JLabel("Hood",SwingConstants.CENTER);
		hoodStatus = new JLabel(hoodSwitch.getStatusString(),SwingConstants.CENTER);
		alarmStatusTitleLabel = new JLabel("Alarm Siren",SwingConstants.CENTER);
		alarmStatus = new JLabel("n/a",SwingConstants.CENTER);
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
		
		//adding the panels to the frame
		this.add(inputPanel);
		this.add(statusPanel);
		this.add(keyPanel);
		//ALWAYS KEEP THIS AT THE END!!!
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		//empty
	}
}