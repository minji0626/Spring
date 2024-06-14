package kr.spring.ch10;

import java.util.List;

public class PerformanceMonitor {
	
	// Property
	private List<Double> deviations;
										// 설정해서 주입해주면 된다.

	public void setDeviations(List<Double> deviations) {
		this.deviations = deviations;
	}

	@Override
	public String toString() {
		return "PerformanceMonitor [deviations=" + deviations + "]";
	}
	
	
}
