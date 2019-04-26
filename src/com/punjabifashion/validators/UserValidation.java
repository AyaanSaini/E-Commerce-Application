package com.punjabifashion.validators;

import com.punjabifashion.beans.User;

public class UserValidation {
	String isValid = "";
	String passwordRegex = "[^A-Za-z0-9]{8,15}";
	/*Pattern UserNameattern = Pattern.compile(usernameRegex);
	Pattern passwordPattern = Pattern.compile(passwordRegex);*/
	public String validateUser(User user){
		if(
				user.getUsername()== null||
				user.getEmail()== null||
				user.getPassword()== null||
				user.getQuestion1()== null||
				user.getQuestion2()== null||
				user.getAnswer1()== null||
				user.getAnswer2()== null
		){
			isValid = "Please fill all Fields";
			System.out.println("Please fill all Fields");
		}else if(
				user.getUsername().equals("")||
				user.getEmail().equals("")||
				user.getPassword().equals("")||
				user.getQuestion1().equals("")||
				user.getQuestion2().equals("")||
				user.getAnswer1().equals("")||
				user.getAnswer2().equals("")
		){
			isValid = "Please fill all Fields";
			System.out.println("Please fill all Fields");
		}
		else if(user.getUsername().length() <8 && user.getUsername().length() > 12 ){
			isValid = "Please provide a correct username\n(Must contains at least 8 and max 12 charater)";
			System.out.println("username is not fine");
		}
		else if(user.getPassword().matches(passwordRegex)){
			isValid = "Please provide a correct password\n(Must contains at least 8 character and max 15, 1 capital character, 1 number and 1 small character)";
			System.out.println("password does not match");
		}
		else {
			isValid = "user is fine";
		}
		return isValid;
	}

}
