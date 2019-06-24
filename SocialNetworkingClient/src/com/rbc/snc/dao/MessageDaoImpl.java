package com.rbc.snc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.model.Message;
import com.rbc.snc.model.MessageBean;
import com.rbc.snc.model.MessageLike;
import com.rbc.snc.model.ShareMessage;
import com.rbc.snc.util.IDGenerator;
import com.rbc.snc.util.SncConstants;

public class MessageDaoImpl implements MessageDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.dao.MessageDao#addNewMessage(java.util.List)
	 */
	@Override
	public String addNewMessage(Message messages) throws IOException, ClassNotFoundException {
		   String statusCode = null;
			MessageBean message = new MessageBean();
			message.setMessageId(IDGenerator.idGenerate());
			message.setSenderId(messages.getSender().getUserId());
			message.setRecipientId(messages.getRecipient().getUserId());
			message.setMessage(messages.getBody());
			message.setDate(String.valueOf(LocalDateTime.now()));
			List<MessageBean> messageList = getAllMessages();
			messageList.add(message);
			saveNewMessageList(messageList);
			statusCode = "200";
		
		return statusCode;
	}

	/**Performs I/O operations
	 * @param clientMsg
	 * @throws IOException
	 */
	public void saveNewMessageList(List<MessageBean> clientMsg) throws IOException {
		
		//String filePath = "C://Users//DRBCG28//workspacePIP//SocialNetworkingClient//Messages.txt";
		File file = new File(SncConstants.FILE_PATH_MESSAGE);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(clientMsg);
		oos.flush();
		oos.close();
		fos.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.dao.MessageDao#retriveMessage(int)
	 */
	@Override
	public MessageBean getMessage(String messageId) throws ClassNotFoundException, IOException  {
		List<MessageBean> msgBeans = getAllMessages();
		MessageBean messageBean = null;
		for (MessageBean message : msgBeans) {
			if (message.getMessageId().equals(messageId)) {
				return  message;
			}
		}
		return messageBean;

	}

	/**
	 * @param userId
	 * @return
	 * @throws SncException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public List<MessageBean> getAllMessages() throws IOException, ClassNotFoundException {
		//String filePath = "C://Users//DRBCG28//workspacePIP//SocialNetworkingClient//Messages.txt";
		// List<MessageBean> messageList = null;
		List<MessageBean> allMsgList = null;
			File file = new File(SncConstants.FILE_PATH_MESSAGE);
			if (!file.exists() || file.length() == 0) {
				     List<MessageBean> bean = new ArrayList<MessageBean>();
                      MessageBean message = new MessageBean();
                      message.setMessageId(IDGenerator.idGenerate());
                      message.setSenderId(1);
                      message.setRecipientId(2);
                      message.setMessage("Hi ....");
                      message.setDate(String.valueOf(LocalDateTime.now()));
                      bean.add(message);
                      saveNewMessageList(bean);
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				allMsgList = new ArrayList<MessageBean>();
				List<MessageBean> messageList = (List<MessageBean>) ois.readObject();
				ois.close();
				if (messageList != null) {
					allMsgList.addAll(messageList);
				}
			}
		
		return allMsgList;
	}

	/* (non-Javadoc)
	 * @see com.rbc.snc.dao.MessageDao#likeMessage(com.rbc.snc.model.MessageLike)
	 */
	@Override
	public String likeMessage(MessageLike msg)throws IOException,ClassNotFoundException {
		String status = "";
		if (null != msg) {
			try {
				List<MessageBean> msgBean = new ArrayList<MessageBean>();
				MessageBean message = getMessage(msg.getMessageId());
				message.setLikes(message.getLikes() + msg.getLike());
				msgBean.add(message);
				saveNewMessageList(msgBean);
				status = "200";
			} catch (Exception e) {

			}
		}
		return status;
	}

	/* (non-Javadoc)
	 * @see com.rbc.snc.dao.MessageDao#shareMessage(com.rbc.snc.model.ShareMessage)
	 */
	@Override
	public String shareMessage(ShareMessage messag) {
		try {
			List<MessageBean> msgBeans = new ArrayList<MessageBean>();
			MessageBean message = getMessage(messag.getMessageId());
			if (null != message && messag.getSenderId() == message.getRecipientId()) {
				message.setSenderId(messag.getSenderId());
				message.setRecipientId(messag.getRecipientId());
				msgBeans.add(message);
				saveNewMessageList(msgBeans);
			}
		} catch (Exception e) {

		}
		return "Shared Your Post";
	}

	@Override
	public void retrieveNumbOfLiksOfMsg() {
		// TODO Auto-generated method stub

	}

}
