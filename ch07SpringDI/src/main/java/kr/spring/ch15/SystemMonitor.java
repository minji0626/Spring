package kr.spring.ch15;

public class SystemMonitor {
	
	private PhoneCall call;

	public void setCall(PhoneCall call) {
		this.call = call;
	}

	@Override
	public String toString() {
		return "SystemMonitor [call=" + call + "]";
	}
	
	
}
