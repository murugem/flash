package com.rbc.snc.service;

import java.io.IOException;
import java.util.List;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.dao.MessageDao;
import com.rbc.snc.dao.MessageDaoImpl;
import com.rbc.snc.model.Message;
import com.rbc.snc.model.MessageBean;
import com.rbc.snc.model.MessageLike;
import com.rbc.snc.model.ShareMessage;
import com.rbc.snc.util.SncConstants;

import lombok.extern.java.Log;

@Log
public class MessageServiceImpl implements MessageService {

	MessageDao messageDao = new MessageDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.MessageService#addNewMessage(com.rbc.snc.model.
	 * Message)
	 */
	@Override
	public String addNewMessage(Message message) throws SncException, IOException, ClassNotFoundException {
		log.info(this.getClass() + ">>> addNewMessage()");
		String status = null;
			if (null != message) {
				messageDao.addNewMessage(message);
			} else {
				log.info("AddNewMessage : Error : "+ SncConstants.BLANK_MESSAGE);
				throw new SncException(SncConstants.BLANK_MESSAGE);
			}
		log.info(this.getClass() + "<<< addNewMessage()");
		return status;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.MessageService#getAllMessages()
	 */
	@Override
	public List<MessageBean> getAllMessages() throws SncException {
		log.info(this.getClass() + ">>> getAllMessages()");
		List<MessageBean> messageList = null;
		try {
			messageList = messageDao.getAllMessages();
		} catch (IOException | ClassNotFoundException e) {
			log.info("I/O operation failed/other issue  while retrieving message " + e.getMessage());
			throw new SncException(
					"I/O operation failed/other issue  while retrieving list of messages " + e.getMessage());
		}
		log.info(this.getClass() + "<<< getAllMessages()");
		return messageList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.MessageService#getMessage(java.lang.String)
	 */
	@Override
	public MessageBean getMessage(String messageId) throws SncException {
		log.info(this.getClass() + ">>> getMessage()");
		MessageBean message = null;
		try {
			message = messageDao.getMessage(messageId);
		} catch (IOException | ClassNotFoundException e) {
			log.info("I/O operation failed/other issue  while retrieving message " + e.getMessage());
			throw new SncException("I/O operation failed/other issue  while retrieving message " + e.getMessage());
		}
		log.info(this.getClass() + "<<< getMessage()");
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.MessageService#likeMessage(com.rbc.snc.model.
	 * MessageLike)
	 */
	@Override
	public String likeMessage(MessageLike msg) throws SncException {
		String status = null;
		try {
			status = messageDao.likeMessage(msg);
		} catch (IOException | ClassNotFoundException e) {
			log.info("I/O operation failed/other issue  while retrieving message " + e.getMessage());
			throw new SncException("I/O operation failed/other issue  while retrieving message " + e.getMessage());
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.MessageService#shareMessage(com.rbc.snc.model.
	 * ShareMessage)
	 */
	@Override
	public String shareMessage(ShareMessage msg) {
		String status = null;
		try {
			status = messageDao.shareMessage(msg);
		} catch (Exception e) {

		}

		return status;
	}

	@Override
	public void retrieveNumbOfLiksOfMsg() {
		try {
			messageDao.retrieveNumbOfLiksOfMsg();
		} catch (Exception e) {

		}
	}

}
