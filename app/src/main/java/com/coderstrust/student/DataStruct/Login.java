package com.coderstrust.student.DataStruct;

public class Login {
	String student_id = "" ;
    String auth_key = "";

	public Login(String student_id, String auth_key){
		this.student_id = student_id ;
		this.auth_key = auth_key ;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getAuth_key() {
		return auth_key;
	}

	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
}
