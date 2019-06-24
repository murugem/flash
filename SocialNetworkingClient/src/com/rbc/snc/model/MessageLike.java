package com.rbc.snc.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessageLike implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageId;
	private long like;
}
