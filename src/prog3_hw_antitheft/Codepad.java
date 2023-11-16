package prog3_hw_antitheft;

public class Codepad {
	private int code;
	private boolean numPadState = false;
	
	public boolean enterCode(int inpCode) {
		return code == inpCode? true:false;
	}
	public boolean setCode(int oldCode, int newCode) {
		if(oldCode != code) {
			return false;
		}
		code = newCode;
		return true;
	}
	public boolean getNumpadState() {
		return numPadState;
	}
}
