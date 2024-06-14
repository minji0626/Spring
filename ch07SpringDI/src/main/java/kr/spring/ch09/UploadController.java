package kr.spring.ch09;

public class UploadController {
	private long timeout;
	private ImageSender image;
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public void setImage(ImageSender image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "UploadController [timeout=" + timeout + ", image=" + image + "]";
	}
	
	
}
