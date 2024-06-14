package kr.spring.ch12;

import java.util.Properties;

public class BookClient {
	
	// Property
	private Properties prop;

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	@Override
	public String toString() {
		return "BookClient [prop=" + prop + "]";
	}
	
	
}
