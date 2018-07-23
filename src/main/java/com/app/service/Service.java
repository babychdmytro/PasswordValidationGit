package com.app.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Service {

    public static final String PASSWORD_LENGTH_ERR = "Password must be between 5 and 12 characters long";
    public static final String PASSWORD_CASE_ERR = "Password must only contain lower case letters";
    public static final String LETTER_AND_DIGIT_ERR = "Password must have both a letter and a digit";
    public static final String PASSWORD_SEQUENCE_ERR = "Password must not contain any sequence of characters immediately followed by the same sequence";
	
	public Set<String> validatePassword(String pwd) {
    	
        Set<String> errorsList = new HashSet<String>();
        Matcher match;
        
        //Validating password length
        if (pwd.length() < 5 || pwd.length() > 12) {
        	errorsList.add(PASSWORD_LENGTH_ERR);
        }
        
        //Checking if password has only lower case letters
        match = Pattern.compile("[A-Z]").matcher(pwd);
        if (match.find()) {
        	errorsList.add(PASSWORD_CASE_ERR);
        }
        
        //Checking if password has both letters and digits
        match = Pattern.compile("[a-z]+\\d+|\\d+[a-z]+").matcher(pwd);
        if (!match.find()) {
        	errorsList.add(LETTER_AND_DIGIT_ERR);
        }
        
        //Checking password for sequence characters
        match = Pattern.compile("(\\w{2,})\\1").matcher(pwd);
        if (match.find()) {
        	errorsList.add(PASSWORD_SEQUENCE_ERR);
        }
        
        return errorsList;
    }
	
}
