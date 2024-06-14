package kr.spring.ch18;

public class Camera {
	private int number;

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Camera [number=" + number + "]";
	}
	
}
