package prog3_hw_antitheft;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
public class Window extends JFrame{
	private static final long serialVersionUID = 4601973850611220034L;
	JButton leftDoorBtn = new JButton("Left Door");
	JButton rightDoorBtn = new JButton("Right Door");
	JButton hoodbtn = new JButton("Hood");
	Window(){
		//making and setting up or GUI window
		this.setSize(1920,1080);
	
		this.setTitle("SATs-Simple Anti-Theft system");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		//styling
		this.getContentPane().setBackground(Color.DARK_GRAY);
		JPanel inputPanel = new JPanel();
		JLabel inputTitleLabel = new JLabel("Toggle openable inputs",SwingConstants.CENTER);
		inputTitleLabel.setOpaque(true);
		inputPanel.setBackground(Color.DARK_GRAY);
		inputPanel.setLayout(new GridLayout(2,1));
		inputPanel.setBounds(900,0,400,60);
		
		//adding elements to the input panel
		inputPanel.add(inputTitleLabel);
		inputPanel.add(leftDoorBtn);
		inputPanel.add(rightDoorBtn);
		inputPanel.add(hoodbtn);
		this.add(inputPanel);
		//ALWAYS KEEP THIS AT THE END!!!
		this.setVisible(true);
	}
}