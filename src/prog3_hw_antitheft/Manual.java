package prog3_hw_antitheft;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Manual extends JFrame{

	private static final long serialVersionUID = 12L;
	Manual(){
		this.setSize(640,480);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JPanel manualText = new JPanel();
		manualText.setLayout(new FlowLayout());
		JLabel textLabel = new JLabel();
		textLabel.setText(instructions);
		manualText.add(textLabel);
		this.add(manualText);
		this.setVisible(true);
	}
	//text for the manual
	String instructions = new String("<html>INSTRUCTIONS<BR>By default, the doors and the hood are locked. If you open one of the door, you have to enter the access code within 10 seconds, otherwise the alarm starts playing until the correct code is entered<BR>If you open the hood while the alarm is armed, the alarm will start instantly.<BR>After disarming the alarm system by entering the correct code, you can start the engine.<BR>In real life, the ignition key has a spring, which returns the key from START to ACC<BR>In order to arm the system, both doors and the hood has to be closed, and the key has to be in the OFF position");
}
