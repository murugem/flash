package com.rbc.snc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rbc.snc.model.UserD;

/** This class performs all validations for requested data. 
 * @author DRBCG28
 *
 */
public class CommonUtil {

	/**
	 * Check whether the given phone number is valid or not.
	 * 
	 * @param phoneNo
	 * @return true /false
	 */
	public static boolean isValidatePhoneNumber(String phoneNo) {

		if (phoneNo.matches("\\d{10}")) {
			return true;
		}
		return false;

	}

	/**
	 * Validate email address
	 * 
	 * @param emailAddress
	 * @return matcher.matches() : true/false
	 */
	public static boolean isValidEmailAddress(String emailAddress) {
		String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = emailAddress;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();

	}

	/**
	 * Validate user field
	 * 
	 * @param user
	 * @return validatorResult
	 */
	public static int validatUserId(UserD user) {

		int validatorResult = 0;
		int id = user.getUserId();

		if (id == (int) id) {
			validatorResult = 1;
		}
		if (id == 0) {
			validatorResult = 2;
		} else if (id > 500) {
			validatorResult = 9;
		}

		return validatorResult;

	}

	/**
	 * Check  gender whether it is MALE /FEMALE.
	 * 
	 * @param gender
	 * @return
	 */
	public static boolean chkGender(String gender) {
		// Match match = Regex.Match(gender, @"^M(ale)?$|^F(emale)?$");
		String expression = "^(?:m|M|male|Male|f|F|female|Female)$";
		CharSequence inputStr = gender;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();
	}
}

/*
 * public static void main(String[] args) {
 * 
 * String phoneNo = "1214567892"; if (phoneNo.matches("\\d{10}")) {
 * System.out.println("valid PhonemNumb"); }
 */