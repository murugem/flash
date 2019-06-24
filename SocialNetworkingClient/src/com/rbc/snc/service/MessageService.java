package com.rbc.snc.service;

import java.io.IOException;
import java.util.List;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.model.Message;
import com.rbc.snc.model.MessageBean;
import com.rbc.snc.model.MessageLike;
import com.rbc.snc.model.ShareMessage;

public interface MessageService {
	
	String addNewMessage(Message message) throws SncException, IOException, ClassNotFoundException;

	public List<MessageBean> getAllMessages() throws SncException;

	public MessageBean getMessage(String messageId) throws SncException;

	public String likeMessage(MessageLike msg) throws SncException;

	public String shareMessage(ShareMessage msg);

	public void retrieveNumbOfLiksOfMsg();
}
