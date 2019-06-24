package com.rbc.snc.dao;

import java.io.IOException;
import java.util.List;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.model.Message;
import com.rbc.snc.model.MessageBean;
import com.rbc.snc.model.MessageLike;
import com.rbc.snc.model.ShareMessage;

public interface MessageDao {
	String addNewMessage(Message message) throws IOException, ClassNotFoundException;

	public List<MessageBean> getAllMessages() throws IOException, ClassNotFoundException;

	public MessageBean getMessage(String messageId) throws ClassNotFoundException, IOException;

	public String likeMessage(MessageLike msg) throws IOException, ClassNotFoundException;

	public String shareMessage(ShareMessage msg);

	public void retrieveNumbOfLiksOfMsg();

}
