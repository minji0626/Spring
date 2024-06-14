package kr.spring.ch11;

import java.util.Map;

public class ProtocolHandlerFactory {
	// Proeprty
	private Map<String, Object> map;

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "ProtocolHandlerFactory [map=" + map + "]";
	}
	
	
}
