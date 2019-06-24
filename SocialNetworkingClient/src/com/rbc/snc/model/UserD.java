package com.rbc.snc.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rbc.snc.util.Gender;

import lombok.Data;

@Data
public class UserD implements Serializable {

	public static final long serialVersionUID = 1L;

	/*public enum Sex {
        MALE, FEMALE
    }*/
	
	private int userId;
	private  String name;
	private  String email;
	private  String gender;
	private  String phone;

	/**
	 * This is a default constructor
	 */
	public UserD() { 
	}

	/*public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper map = new ObjectMapper();
		UserD user = new UserD();
		String json = map.writeValueAsString(user);
		System.out.println(json);
	}*/
}
