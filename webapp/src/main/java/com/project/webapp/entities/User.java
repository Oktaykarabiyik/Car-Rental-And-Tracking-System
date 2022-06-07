package com.project.webapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity //bu class database e maplenecek demek
@Table(name="user")//table name i veriyoruz aynı isimle olmak zorunda değil
@Data///user classının fet ve set lerin otomatık generete yapacak
public class User {
@Id
Long id;

String userName;
String password;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
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

}
