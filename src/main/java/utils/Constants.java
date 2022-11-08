package utils;

public class Constants {
	public static final String REGISTER_EXIST_EMAIL = "This email is already been used";
	public static final String REGISTER_FAIL_MESSAGE = "Register user fail";
	public static final String EMPTY_FEEDBACK_MESSAGE = "This field is required";
	public static final String EMPTY_LOGIN_MESSAGE = "Please fill all required fields";
	public static final String LOGIN_FAIL_MESSAGE = "User name or password is incorrect";
	public static final String USERMAIL_FEEDBACK_MESSAGE = "User mail not match the pattern";
	public static final String PASSWORD_FEEDBACK_MESSAGE = "Password not match the pattern";
	public static final String REPASSWORD_FEEDBACK_MESSAGE = "Re-password not match the password";
	public static final String PHONE_FEEDBACK_MESSAGE = "Phone not match the pattern";
	public static final String DISCOUNT_FEEDBACK_MESSAGE = "Discout not match the pattern";
	
	public static final String EMAIL_REGEX = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
	public static final String PASSWORD_REGEX = "[a-zA-Z0-9_!@#$%^&*]{3,}";
	public static final String PHONE_REGEX = "^\\d+$";
	public static final String DISCOUNT_REGEX = "^[A-Z0-9]*$";
}
