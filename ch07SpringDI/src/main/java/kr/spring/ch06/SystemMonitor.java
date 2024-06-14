package kr.spring.ch06;

public class SystemMonitor {
	private long periodTime;
	private SmsSender sender;
	
	// long 타입에는 숫자, sender에는 객체 전달
	public SystemMonitor(long periodTime, SmsSender sender) {
		this.periodTime = periodTime;
		this.sender = sender;
	}
	
	@Override
	public String toString() {
		return "SystemMonitor [periodTime=" + periodTime + ", sender=" + sender + "]";
	}

}
