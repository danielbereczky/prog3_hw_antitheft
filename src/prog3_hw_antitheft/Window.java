package prog3_hw_antitheft;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Window extends JFrame{
	private static final long serialVersionUID = 4601973850611220034L;
	JButton leftDoorBtn = new JButton("Left Door");
	JButton rightDoorBtn = new JButton("Right Door");
	JButton hoodbtn = new JButton("Hood");
	Window(){
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
		JLabel inputTitleLabel = new JLabel("Set inputs",SwingConstants.CENTER);
		
		//Key
		JRadioButton keyOff = new JRadioButton("LOCK");
		JRadioButton keyAcc = new JRadioButton("ON/ACC");
		JRadioButton keyStart = new JRadioButton("START");
		
		ButtonGroup keyButtons = new ButtonGroup();
		keyButtons.add(keyOff);
		keyButtons.add(keyAcc);
		keyButtons.add(keyStart);
		
		inputTitleLabel.setOpaque(true);
		inputPanel.setBackground(Color.DARK_GRAY);
		inputPanel.setLayout(new GridLayout(0,1));
		inputPanel.setBounds(440,0,200,300);
		
		//adding elements to the input panel
		inputPanel.add(inputTitleLabel);
		inputPanel.add(leftDoorBtn);
		inputPanel.add(rightDoorBtn);
		inputPanel.add(hoodbtn);
		
		//status panel
		JPanel statusPanel = new JPanel();
		JLabel statusTitleLabel = new JLabel("System Status:",SwingConstants.CENTER);
		JLabel enginerunningLabel = new JLabel("Engine",SwingConstants.CENTER);
		JLabel engineFeedBackLabel = new JLabel("n/a",SwingConstants.CENTER);
		JLabel masterStatus = new JLabel("ACTIVE",SwingConstants.CENTER);
		JLabel leftDoorTitleLabel = new JLabel("Left door",SwingConstants.CENTER);
		JLabel leftDoorStatus = new JLabel("n/a",SwingConstants.CENTER);
		JLabel rightDoorTitleLabel = new JLabel("Right door",SwingConstants.CENTER);
		JLabel rightDoorStatus = new JLabel("n/a",SwingConstants.CENTER);
		JLabel hoodTitleLabel = new JLabel("Hood",SwingConstants.CENTER);
		JLabel hoodStatus = new JLabel("n/a",SwingConstants.CENTER);
		JLabel alarmStatusTitleLabel = new JLabel("Alarm Siren",SwingConstants.CENTER);
		JLabel alarmStatus = new JLabel("n/a",SwingConstants.CENTER);
		
		
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
}