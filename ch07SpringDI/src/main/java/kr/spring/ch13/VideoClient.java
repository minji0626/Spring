package kr.spring.ch13;

import java.util.Set;

public class VideoClient {
	// property
	private Set<Integer> subSet;

	public void setSubSet(Set<Integer> subSet) {
		this.subSet = subSet;
	}

	@Override
	public String toString() {
		return "VideoClient [subSet=" + subSet + "]";
	}
	
	
}
