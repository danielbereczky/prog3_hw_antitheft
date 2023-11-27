package prog3_hw_antitheft;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.swing.JLabel;

import org.junit.Test;

public class Tests {
	//1
	@Test
	public void runEngineTest() {
		Engine testEngine = new Engine();
		testEngine.setStartable(true);
		testEngine.start();
		
		assertTrue("engine start test",testEngine.getStatus());
		
	}
	//2
	@Test
	public void stopEngineTest() { 
		Engine testEngine = new Engine();
		testEngine.setStartable(true);
		testEngine.start();
		testEngine.stop();
		
		assertFalse("engine stop test", testEngine.getStatus());
		
	}
	//3
	@Test
	public void alarmTest() {
		AlarmSiren siren = new AlarmSiren();
		siren.setSiren(true);
		
		assertTrue("siren test",siren.getStatus());
		
	}
	//4
	@Test
	public void switchTest() {
		Switch testSwitch = new Switch();
		testSwitch.setSwitchState(true);
		
		assertTrue("switch test",testSwitch.getSwitchState());
	}
	//5
	@Test
	public void keyTest() {
		IgnitionKey testKey = new IgnitionKey();
		
		testKey.setState(IgnitionKey.keyState.ACC);
		assertTrue("key test",testKey.getState()==IgnitionKey.keyState.ACC);
	}
	//6
	@Test
	public void enableKeyTest(){
		IgnitionKey testKey = new IgnitionKey();
		testKey.setEnabled(true);
		assertTrue("enabling key test",testKey.getIsEnabled());
	}
	//7
	@Test
	public void codeSetTest() {
		Logic testLogic = new Logic();
		testLogic.setCode("test");
		assertEquals("setting up codes",testLogic.getCode(),"test");
	}
	//8
	@Test
	public void armablewithOpenDoorsTest() {
		JLabel l1 = new JLabel();
		Logic testLogic = new Logic();
		testLogic.toggleHood(l1);
		assertFalse("system arming test with open doors",testLogic.armable());
	}
	//9
	@Test
	public void tamperTest() {
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		Logic testLogic = new Logic();
		testLogic.keyTamperCheck(l1, l2);
		assertEquals(testLogic.getSirenStatus(),"Playing Sound");
	}
	//10
	@Test
	public void armingTest(){
		Logic testLogic = new Logic();
		assertFalse("testing that we can't arm an already armed system",testLogic.armable());
	}
	
}
