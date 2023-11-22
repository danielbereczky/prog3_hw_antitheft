package prog3_hw_antitheft;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class CountdownTimer {
    private int duration;
    private Timer timer;
    private boolean running;

    public CountdownTimer(int d,final AlarmSiren alarm, final JLabel alarmStatus) {
        duration = d;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	running = true;
                duration--;
                if (duration <= 0) {
                	running = false;
                    timer.stop();
                    alarm.setSiren(true);
                    alarmStatus.setText(alarm.getStatusString());
                }
            }
        });
    }
    
    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    public boolean isRunning(){
    	return running;
    }
}