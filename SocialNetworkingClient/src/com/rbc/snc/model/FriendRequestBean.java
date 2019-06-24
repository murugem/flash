package com.rbc.snc.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class FriendRequestBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String friendid;
	private String status;
	private int pendingApproval;
}
