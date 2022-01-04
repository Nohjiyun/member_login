package dto;

public class MemberDto {
	// 번호
	private int num;
	// 이름
	private String name;
	// 아이디
	private String userid;
	// 비밀번호
	private String userpw;
	// 전공
	private String major;
	// 이메일
	private String email;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String uerpw) {
		this.userpw = uerpw;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
