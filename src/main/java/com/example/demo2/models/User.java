package com.example.demo2.models;


public class User {
	private String FullName;
	private String Email;
	private String Password;
	private byte[] profilepic;
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public byte[] getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(byte[] profilepic) {
		this.profilepic = profilepic;
	}
	
	
}
