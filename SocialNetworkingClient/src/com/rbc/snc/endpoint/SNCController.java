package com.rbc.snc.endpoint;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.dao.MessageDao;
import com.rbc.snc.dao.MessageDaoImpl;
import com.rbc.snc.model.FriendBean;
import com.rbc.snc.model.Message;
import com.rbc.snc.model.MessageBean;
import com.rbc.snc.model.MessageLike;
import com.rbc.snc.model.ShareMessage;
import com.rbc.snc.model.UserD;
import com.rbc.snc.service.FriendService;
import com.rbc.snc.service.FriendServiceImpl;
import com.rbc.snc.service.MessageService;
import com.rbc.snc.service.MessageServiceImpl;
import com.rbc.snc.service.UserService;
import com.rbc.snc.service.UserServiceImpl;
import com.rbc.snc.util.SncConstants;
import com.rbc.snc.util.StatusType;

import lombok.extern.java.Log;

//import com.rbc.user.exceptions.UserException;

/**
 * This class will perform all social networking activities according to user
 * actions.
 * 
 * @author DRBCG28
 *
 */
@Log
@Path("/sncServices")
public class SNCController {

	UserService userService = new UserServiceImpl();
	MessageService messageService = new MessageServiceImpl();
	FriendService friendService = new FriendServiceImpl();

	/**
	 * This method is used to create new user profile.
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws SncException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/userProfile/create")
	public StatusType createUserProfile(UserD user) throws IOException, SncException {
		log.info(this.getClass() + ">>> createUserProfile(()");
		log.info("user-requestString : " + user.toString());
		StatusType response = null;
		if (null != user) {
			try {
				response = userService.addUser(user);
			} catch (IOException e) {
				throw new SncException("I/O opeartion failed while creating new user profile" + e.getMessage());
			} catch (Exception e) {
				throw new SncException("Something went wrong while creating new user profile" + e.getMessage());
			}
		}
		log.info("createUserProfile-Response :" + response.toString());
		log.info(this.getClass() + "<<< createUserProfile(()");
		return response;

	}

	/**
	 * This method is used to update user profile as per the user Id.
	 * 
	 * @param user
	 * @return String status
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/userProfile/update")
	public StatusType updateUserProfile(UserD user) {
		log.info(this.getClass() + " >>> updateUserProfile()");
		log.info("User-requestString: " + user.toString());
		StatusType status = null;
		if (null != user) {
			try {
				status = userService.updateUser(user);
			} catch (SncException e) {
				log.info("Error occurred in updateUserProfile()----" + e.getMessage());
				e.printStackTrace();
			} catch (Throwable t) {
				log.info("Something went wrong while updating user profile" + t.getMessage());
			}
		}
		log.info("responseString : " + status.toString());
		log.info(this.getClass() + " <<< updateUserProfile()");
		return status;

	}

	/**
	 * This method is used to view single user profile as per the userId.
	 * 
	 * @return user profile : UserD
	 * @throws IOException
	 * @throws SncException
	 */
	@GET
	@Path("/userProfile/{UserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserD viewUserProfile(@PathParam("UserId") int userId) throws IOException, SncException {
		log.info(this.getClass() + " >>>viewUserProfile()");
		log.info("request String- userId...." + userId);
		UserD user = null;
		try {
			if (0 != userId) {

				user = userService.getUserProfile(userId);
			}
		} catch (SncException e) {
			log.info("Error occurred in viewUserProfile()----" + e.getMessage());
		} catch (Throwable t) {
			throw new SncException("Something went wrong in viewUserProfile() " + t.getMessage());
		}
		log.info("responseString : " + user.toString());
		log.info(this.getClass() + "<<<viewUserProfile()");
		return user;
	}

	/**
	 * This method is used to fetch list of user profiles.
	 * 
	 * @return user profiles
	 * @throws IOException
	 * @throws SncException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userProfiles")
	public List<UserD> viewAllUserProfile() throws IOException, SncException {
		log.info(this.getClass() + " >>>viewAllUserProfile()");
		List<UserD> userProfile = null;
		try {
			userProfile = userService.getAllUsersProfile();
		} catch (SncException e) {
			log.info("Error occured while fetching listof user profiles" + e.getMessage());
			e.getStackTrace();
		} catch (Throwable t) {
			log.info("Something went wrong while fetching listof user profiles" + t.getMessage());
			throw new SncException("Something went wrong while fetching listof user profiles" + t.getMessage());
		}
		log.info("responseString : " + userProfile);
		log.info(this.getClass() + "<<<viewAllUserProfile()");
		return userProfile;
	}

	/**
	 * This method is used to post a new message.
	 * 
	 * @param message
	 * @return String status.
	 * @throws SncException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Message/addNew")
	public StatusType addNewMessage(Message message) throws SncException {
		log.info(this.getClass() + " >>>addNewMessage()");
		log.info("request String- message...." + message.toString());
		String status = null;
		StatusType statusType = null;
		try {
			statusType = new StatusType();
			status = messageService.addNewMessage(message);
			if (status.equals(SncConstants.SUCCESS_CODE)) {
				statusType.setStatusCode((SncConstants.SUCCESS_CODE));
				statusType.setStatusDesc(SncConstants.ADD_MESSAGE_SUCCESS);
			} else {
				statusType.setStatusCode((SncConstants.ERROR_CODE));
				statusType.setStatusDesc(SncConstants.ADD_MESSAGE_FAIL);
			}

		} catch (SncException e) {
			log.info("Error Occured : " + e.getMessage());
			e.getStackTrace();
		} catch (IOException e) {
			log.info("I/O Operation failed while adding new message" + e.getMessage());
			throw new SncException("I/O Operation failed while adding new message" + e.getMessage());
		} catch (Throwable t) {
			log.info("Something went wrong while adding new message :" + t.getMessage());
			throw new SncException("Something went wrong while adding new message  :" + t.getMessage());
		}
		log.info(this.getClass() + " <<<addNewMessage()");
		log.info("response String- message...." + statusType.toString());
		return statusType;

	}

	/**
	 * This method is used to retrieve the list of messages of user.
	 * 
	 * @param userId
	 * @return messageList : List<MessageBean>
	 * @throws SncException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Messages")
	public List<MessageBean> retrieveMessages() throws SncException {
		log.info(this.getClass() + " >>>retrieveMessages()");
		List<MessageBean> messageList = null;
		try {
			messageList = messageService.getAllMessages();
			if (null == messageList || messageList.size() == 0) {
				throw new SncException("No Messages Found");
			}
		} catch (SncException e) {
			log.info("Error occurred : " + e.getMessage());
			e.getStackTrace();
		} catch (Throwable t) {
			throw new SncException("Something went wrong while fetching list of messages " + t.getMessage());
		}
		log.info(this.getClass() + " <<<retrieveMessages()");
		log.info("response String- message...." + messageList);
		return messageList;

	}

	/**
	 * This method is used to retrieve single message of user.
	 * 
	 * @param messageId
	 * @return message: MessageBean
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Message/{messageId}")
	public MessageBean retrieveMessage(@PathParam("messageId") String messageId) {
		log.info(this.getClass() + " >>>retrieveMessage()");
		log.info("request String- messageId...." + messageId);
		MessageBean message = null;
		try {
			message = messageService.getMessage(messageId);
		} catch (SncException e) {
			log.info("Error Occured :" + e.getMessage());
			e.getMessage();
		} catch (Throwable t) {
			log.info("Somethig went wrong while fetching messaage" + t.getMessage());
		}
		log.info(this.getClass() + " <<<retrieveMessage()");
		log.info("response String- message...." + message.toString());
		return message;

	}

	/**
	 * This method is used to add new friend.
	 * 
	 * @param friend
	 * @return String status
	 * @throws SncException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Friends/request")
	public StatusType makeFriendRequest(FriendBean friend) throws SncException {
		log.info(this.getClass() + " >>>makeFriendRequest()");
		log.info("requestString- friend...." + friend.toString());
		String status = null;
		StatusType statusType = null;
		try {
			if (null != friend) {
				statusType = new StatusType();
				status = friendService.makeFriendRequest(friend);
				if (null != status && status.equals(SncConstants.SUCCESS_CODE)) {
					statusType.setStatusCode(SncConstants.SUCCESS_CODE);
					statusType.setStatusDesc(SncConstants.ADD_FRIEND_SUCCESS);
				} else {
					statusType.setStatusCode(SncConstants.ERROR_CODE);
					statusType.setStatusDesc(SncConstants.ADD_FRIEND_FAIL);
				}
			}
		} catch (SncException e) {
			log.info("Error Occured " + e.getMessage());
			e.getStackTrace();
		} catch (Throwable t) {
			log.info("Something Went Wrong while sending new friend request" + t.getMessage());
			throw new SncException("Something Went Wrong while sending new friend request" + t.getMessage());
		}
		log.info(this.getClass() + " <<<makeFriendRequest()");
		log.info("responseString- friend...." + status.toString());
		return statusType;

	}

	/**
	 * This method is used to accept or reject the friend request.
	 * 
	 * @param friendBean
	 * @return status
	 * @throws SncException
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Friends/acceptOrreject")
	public String acceptOrRejectFriedRequest(FriendBean friendBean) throws SncException {
		log.info(this.getClass() + " >>>acceptOrRejectFriedRequest()");
		log.info("requestString- friendBean...." + friendBean.toString());
		String status = null;
		if (null != friendBean) {
			try {
				status = friendService.acceptOrRejectFriedRequest(friendBean);

			} catch (SncException e) {
				log.info("Error Occured " + e.getMessage());
			} catch (Throwable t) {
				log.info("Something Went Wrong while acceptOrRejectFriedRequestt" + t.getMessage());
				throw new SncException("Something Went Wrong while acceptOrRejectFriedRequestt" + t.getMessage());
			}
		}
		log.info(this.getClass() + " <<<acceptOrRejectFriedRequest()");
		log.info("responseString- friendBean...." + status.toString());

		return status;

	}

	/**
	 * This method is used to fetch the list of friend request pending approval
	 * 
	 * @return listOfPndgRqst
	 * @throws SncException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Friends/pendingApproval")
	public List<FriendBean> getPendingApprovalFrndRqstList() throws SncException {
		log.info(this.getClass() + " >>>getPendingApprovalFrndRqstList()");
		List<FriendBean> listOfPndgRqst = null;
		try {
			listOfPndgRqst = friendService.getPendingApprovalFrndRqstList();
		} catch (SncException e) {
			log.info("Error Occured " + e.getMessage());
			e.getStackTrace();
		} catch (Throwable t) {
			log.info("Something went wrong while fetching list of pending approval friend request" + t.getStackTrace());
			throw new SncException(
					"Something went wrong while fetching list of pending approval friend request" + t.getStackTrace());
		}
		log.info(this.getClass() + " <<<getPendingApprovalFrndRqstList()");
		log.info("response String- friendBean...." + listOfPndgRqst);
		return listOfPndgRqst;

	}

	/**
	 * This method will perform likes message functionality as per the MessageId
	 * 
	 * @param msg
	 * @return status
	 * @throws SncException 
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/messages/like")
	public String likeMessage(MessageLike msg) throws SncException {
		log.info(this.getClass() + " >>>likeMessage()");
		log.info("request String- msg...." + msg.toString());
		String status = null;
		StatusType statusType = null;
		try {
			status = messageService.likeMessage(msg);
			if(null != status && status.equals(SncConstants.SUCCESS_CODE)){
				statusType = new StatusType();
				if (null != status && status.equals(SncConstants.SUCCESS_CODE)) {
					statusType.setStatusCode(SncConstants.SUCCESS_CODE);
					statusType.setStatusDesc(SncConstants.LIKE_MESSAGE);
				}
			}
		} catch (SncException e) {
			log.info("Error Occured " + e.getMessage());
			e.getStackTrace();
		}catch(Throwable t){
			log.info("Something went wrong while likes message" + t.getStackTrace());
			throw new SncException(
					"Something went wrong while while likes message" + t.getStackTrace());
		}
		log.info(this.getClass() + " <<<likeMessage()");
		log.info("response String- msg...." + status.toString());
		return status;

	}

	/**
	 * This method will perform share message functionality according to
	 * messageId
	 * 
	 * @param msg
	 * @return status
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/messages/share")
	public String shareMessage(ShareMessage msg) {
		log.info(this.getClass() + " >>>shareMessage()");
		log.info("request String- msg...." + msg.toString());
		String status = null;
		try {
			status = messageService.shareMessage(msg);
		} catch (Exception e) {

		}
		log.info(this.getClass() + " <<<likeMessage()");
		log.info("response String- msg...." + status.toString());
		return status;

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/messages/likes")
	public void retrieveNumbOfLiksOfMsg() {
		MessageDao messageDao = null;
		try {
			messageDao = new MessageDaoImpl();
			messageDao.retrieveNumbOfLiksOfMsg();
		} catch (Exception e) {

		}
	}
	
}
