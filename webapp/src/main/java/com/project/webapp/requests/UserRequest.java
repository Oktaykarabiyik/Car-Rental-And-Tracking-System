package com.project.webapp.requests;

import lombok.Data;

@Data
public class UserRequest {
String userName;
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
String password;
}
