package com.rbc.snc.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.dao.FriendDao;
import com.rbc.snc.dao.FriendDaoImpl;
import com.rbc.snc.model.FriendBean;

import lombok.extern.java.Log;

/**This class performs all service level social network activities. 
 * @author DRBCG28
 *
 */
@Log
public class FriendServiceImpl implements FriendService {

	FriendDao friendDao = new FriendDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rbc.snc.service.FriendService#makeFriendRequest(com.rbc.snc.model.
	 * FriendBean)
	 */
	@Override
	public String makeFriendRequest(FriendBean friend) throws SncException, ClassNotFoundException {
		log.info(this.getClass() + ">>>makeFriendRequest()");
		log.info("requestString..." + friend.toString());
		String status = null;
		try {
			if (null != friend) {
				status = friendDao.makeFriendRequest(friend);
			}
		} catch (FileNotFoundException e) {
			log.info("File Not Found!" + e.getMessage());
			throw new SncException("File Not Found!" + e.getMessage());
		} catch (IOException e) {
			log.info("I/O operation failed while adding the new friend" + e.getMessage());
			throw new SncException("I/O operation failed while adding the new friend" + e.getMessage());
		}
		log.info("responseString..." + status);
		log.info(this.getClass() + "<<<makeFriendRequest()");
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.FriendService#getPendingApprovalFrndRqstList()
	 */
	@Override
	public List<FriendBean> getPendingApprovalFrndRqstList() throws SncException {
		log.info(this.getClass() + ">>>getPendingApprovalFrndRqstList()");
		List<FriendBean> listOfPndgRqst = null;
		try {
			listOfPndgRqst = friendDao.getPendingApprovalFrndRqstList();
			if (null == listOfPndgRqst || listOfPndgRqst.size() == 0) {
				throw new SncException("No Pending Approval Friend Request Found" + listOfPndgRqst.size());
			}
		} catch (FileNotFoundException f) {
			log.info("File Not Found!" + f.getMessage());
			throw new SncException("File Not Found!" + f.getMessage());
		} catch (ClassNotFoundException c) {
			log.info("Error Occured while loading the class" + c.getMessage());
			throw new SncException("Error Occured while loading the class" + c.getMessage());
		} catch (IOException io) {
			log.info("I/O opeartion failed while fetching list of penging approval requests" + io.getMessage());
			throw new SncException(
					"I/O opeartion failed while fetching list of penging approval requests" + io.getMessage());
		}
		log.info(this.getClass() + "<<<getPendingApprovalFrndRqstList()");
		return listOfPndgRqst;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rbc.snc.service.FriendService#acceptOrRejectFriedRequest(com.rbc.snc.
	 * model.FriendBean)
	 */
	@Override
	public String acceptOrRejectFriedRequest(FriendBean friendBean) throws SncException {
		String status = null;
		if (null != friendBean) {
			try {
				status = friendDao.acceptOrRejectFriedRequest(friendBean);

			} catch (FileNotFoundException f) {
				log.info("File Not Found!" + f.getMessage());
				throw new SncException("File Not Found!" + f.getMessage());
			} catch (IOException io) {
				log.info("Error Occured while loading the class" + io.getMessage());
				throw new SncException(
						"I/O opeartion failed while Accepting/Rejecting friend request" + io.getMessage());
			} catch (ClassNotFoundException c) {
				log.info("Error Occured while loading the class" + c.getMessage());
				throw new SncException("Error Occured while loading the class" + c.getMessage());
			}

		}

		return status;
	}

}
