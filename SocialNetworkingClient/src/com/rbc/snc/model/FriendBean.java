package com.rbc.snc.model;



import java.io.Serializable;

import lombok.Data;

/**
 * @author DRBCG28
 *
 */
@Data
public class FriendBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String friendid;
	private String status;
	
	
	/*
	public static void main(String[] args) throws JsonProcessingException {
		FriendBean ben = new FriendBean();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(ben));
	}*/

}

