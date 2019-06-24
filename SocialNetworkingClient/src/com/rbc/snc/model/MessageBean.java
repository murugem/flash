package com.rbc.snc.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageId;
    private String date;
    private int senderId;
    private int recipientId;
    private String message;
    private long likes;

}
