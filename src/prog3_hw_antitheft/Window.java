package prog3_hw_antitheft;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;


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
	JComboBox keyBox;
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
	String keySelected;
	
	JLabel hintText;
	
	JPasswordField codeField;

	Logic sysLogic;

	Window(){
		sysLogic = new Logic();
		try{
			sysLogic.readCode();
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
		engineFeedBackLabel = new JLabel(sysLogic.getEngineStatus(),SwingConstants.CENTER);
		masterStatus = new JLabel("ARMED",SwingConstants.CENTER);
		leftDoorTitleLabel = new JLabel("Left door",SwingConstants.CENTER);
		leftDoorStatus = new JLabel(sysLogic.getLDoorStatus(),SwingConstants.CENTER);
		rightDoorTitleLabel = new JLabel("Right door",SwingConstants.CENTER);
		rightDoorStatus = new JLabel(sysLogic.getRDoorStatus(),SwingConstants.CENTER);
		hoodTitleLabel = new JLabel("Hood",SwingConstants.CENTER);
		hoodStatus = new JLabel(sysLogic.getHoodStatus(),SwingConstants.CENTER);
		alarmStatusTitleLabel = new JLabel("Alarm Siren",SwingConstants.CENTER);
		alarmStatus = new JLabel(sysLogic.getSirenStatus(),SwingConstants.CENTER);
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
					sysLogic.read(masterStatus, hoodStatus, leftDoorStatus, rightDoorStatus, keyStatus, alarmStatus, engineFeedBackLabel, hintText, hoodBtn, leftDoorBtn, rightDoorBtn, keyBox);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		writeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sysLogic.write(hintText);
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
					sysLogic.toggleHood(hoodStatus);
				}
				sysLogic.checkAlarm(alarmStatus,hintText);
			}
		};
		hoodBtn.addActionListener(hoodListener);
		
		ActionListener lDoorListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==leftDoorBtn) {
					sysLogic.toggleLeftDoor(leftDoorStatus);
				}
				sysLogic.checkAlarm(alarmStatus,hintText);
			}
		};
		leftDoorBtn.addActionListener(lDoorListener);
		ActionListener rDoorListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==rightDoorBtn) {
					sysLogic.toggleRightDoor(rightDoorStatus);
				}
				sysLogic.checkAlarm(alarmStatus,hintText);
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
				sysLogic.arm(masterStatus, hintText);
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
				String[] keyStates = {"OFF","ON/ACC","START"};
				keyBox = new JComboBox(keyStates);
				ActionListener keyBoxListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//in real life, the ignition cylinder has a spring in it, which returns the key to "ACC" from "START" in order to avoid cranking while the engine is running. This behavior is coded here
						sysLogic.keyTamperCheck(alarmStatus, hintText);
						if(e.getSource()==keyBox){
							keySelected = (String) keyBox.getSelectedItem();
						}
						switch(keySelected){
							case("OFF"):
								sysLogic.keyOff(keyStatus, engineFeedBackLabel, hintText);
								break;
							case("ON/ACC"):
								sysLogic.keyAcc(keyStatus);
								break;
							case("START"):
								sysLogic.keyStart(keyStatus,engineFeedBackLabel,hintText);
								keyBox.setSelectedIndex(1);
								break;
						}
					}
				};
				keyBox.setSelectedIndex(0);
				keyBox.addActionListener(keyBoxListener);
				
				
				keyPanel = new JPanel();
				JLabel keyTitleLabel = new JLabel("Set key to:",SwingConstants.CENTER);
				keyTitleLabel.setOpaque(true);
				keyPanel.setLayout(new GridLayout(4,1));
				keyPanel.setBounds(220,0,200,100);
				keyPanel.setBackground(Color.DARK_GRAY);
				keyPanel.add(keyTitleLabel);
				keyPanel.add(keyBox);
	}
	public void setCodePanel() {
		//code panel
				codePanel = new JPanel();
				codeField = new JPasswordField();
				codeField.setColumns(10);
				JButton submitButton = new JButton("OK");
				submitButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						sysLogic.enterCode(codeField, alarmStatus, masterStatus, hintText);
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
		hintText = new JLabel("The car is locked, and the alarm system is armed. Try opening a door first!");
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
		        final NewCode newc = new NewCode(sysLogic.getCode());
		        // Wait for the NewCode window to be disposed before updating the code
		        newc.addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosed(WindowEvent e) {
		                sysLogic.setCode(newc.getUpdatedCode());
		                sysLogic.writeCode();
		            }
		        });
		    }
		});
		codeChangePanel.add(changeCode);
	}


	public void actionPerformed(ActionEvent e) {
		//empty
	}
}