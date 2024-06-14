package kr.spring.ch17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SystemMonitor2 {
	
//	property
	@Autowired
//	@Qualifier를 이요한 자동 설정 제한
	@Qualifier("main")
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}

	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
}
