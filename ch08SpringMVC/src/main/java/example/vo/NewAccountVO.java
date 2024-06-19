package example.vo;

import org.springframework.web.multipart.MultipartFile;

public class NewAccountVO {
	private String id;
	private String name;
	private String passwd;
	private String passwdcheck;
	private MultipartFile profileimage;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswdcheck() {
		return passwdcheck;
	}
	public void setPasswdcheck(String passwdcheck) {
		this.passwdcheck = passwdcheck;
	}
	public MultipartFile getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(MultipartFile profileimage) {
		this.profileimage = profileimage;
	}
	@Override
	public String toString() {
		return "NewAccountVO [id=" + id + ", name=" + name + ", passwd=" + passwd + ", passwdcheck=" + passwdcheck
				+ ", profileimage=" + profileimage + "]";
	}
	
	

}
