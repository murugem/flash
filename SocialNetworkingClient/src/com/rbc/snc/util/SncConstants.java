package com.rbc.snc.util;

public class SncConstants {

	// User contants
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "name";
	public static final String EMAIL_ID = "email";
	public static final String SEX = "sex";
	public static final String PHONE = "phone";
	public static final String USER_ID_FRMT = "User Id must be numeric and cant be zero";
	public static final String USER_ID_NOT_FOUND = "User is not available in DB, please check the data";
	public static final String USER_ID_OUTOF_RANGE = "ID is out of range.. cannot be greater than 500";
	public static final String DATA_NOT_VALID = "Data is not valid - Please check the data again;";
	public static final String USER_EXITS = "User already exist in DB, please check the data again";
	public static final String SUCCESS = "User Profile Successfully Created";
	public static final String ADD_FAILED = "Adding Failed - Please check the data again";
	public static final String PHONE_NUMBER_ERROR = "phone number must be 10 digits";
	public static final String EMAIL_ADDRESS_ERROR = "Email must be in valid format - abc@rbc.com";
	public static final String USER_ID_ZERO = "ID cannot be Zero, please re-check the ID";
	public static final String GENDER_ERROR = "Gender cannot be null or empty";
	public static final String STATUS_CODE_SUCCESS = "200";
	public static final String STATUS_DESC_UPDATE = "Updated Successfully";
	public static final String USER_PROFILE_EMPTY = "No User Profile Found!";
	public static final String IO_OPERATION_FAILED = "Error Occured while performing In/Out put operations reason: ";
	
	//User already exists in DB
	public static final String STATUS_CODE1 = "1001";
	//Success
	public static final String SUCCESS_CODE = "200";
	//Adding failed 
	public static final String ERROR_CODE = "300";
	
	
	
//	File Info
	public static final String FILE_PATH_USER = "C://Users//DRBCG28//workspacePIP//SocialNetworkingClient//Clients.txt";
	public static final String FILE_PATH_MESSAGE = "C://Users//DRBCG28//workspacePIP//SocialNetworkingClient//Messages.txt";
	public static final String FILE_PATH_FRIEND = "C://Users//DRBCG28//workspacePIP//SocialNetworkingClient//friend3.txt";
	
	
	//Message Constants
	public static final String ADD_MESSAGE_SUCCESS = "Message posted successfully";
	public static final String ADD_MESSAGE_FAIL = "Failed to add message";
	public static final String BLANK_MESSAGE = "Blank Message, please enter message";
	public static final String LIKE_MESSAGE = "You Likes Message";
	
	//Friend Constants
	public static final String ADD_FRIEND_SUCCESS = "You Sent Friend Request successfully";
	public static final String ADD_FRIEND_FAIL = "Failed to Sent Friend Request";
	public static final String FRIEND_ACCEPT_MSG = "Accepted Your Friend request";
	public static final String FRIEND_REJECT_MSG = "Rejected Your Friend Request";
	public static final String FRIEND_REJECT = "R";
	public static final String FRIEND_ACCEPT = "A";
	public static final String FRIEND_PENDING = "P";
	
	
}
