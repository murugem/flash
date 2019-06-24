package com.rbc.snc.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserD sender;
	private UserD recipient;
	private String body;

	public static void main(String[] args) throws JsonProcessingException {
		  FriendBean message = new FriendBean(); 
		  ObjectMapper mapper = new ObjectMapper();
		  System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString
		  (message));
		  
		  }
		 
	
}


 