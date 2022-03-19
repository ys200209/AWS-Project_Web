package user;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private Long userNumber;
	private String userID;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail;
	//private String userSecurity;
	private Date userJoin;
	
	public Long getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(Long userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/*
	public String getUserSecurity() {
		return userSecurity;
	}
	public void setUserSecurity(String userSecurity) {
		this.userSecurity = userSecurity;
	}
	*/
	public Date getUserJoin() {
		return userJoin;
	}
	public void setUserJoin(Date userJoin) {
		this.userJoin = userJoin;
	}
}
