package com.rbc.snc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.rbc.snc.model.FriendBean;
import com.rbc.snc.util.SncConstants;

import lombok.extern.java.Log;

/**
 * @author DRBCG28
 *
 */
@Log
public class FriendDaoImpl implements FriendDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rbc.snc.dao.FriendDao#makeFriendRequest(com.rbc.snc.model.FriendBean)
	 */
	@Override
	public String makeFriendRequest(FriendBean friend)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		log.info(this.getClass() + ">>> makeFriendRequest()");
		String status = null;
		if (null != friend) {
			List<FriendBean> friendRequest = getAllFriendRequests();
			friendRequest.add(friend);
			addNewFrndRqstList(friendRequest);
			status = "200";
			log.info("addNewFrndRqstList- status : " + status);
		}
		log.info(this.getClass() + "<<< makeFriendRequest()");
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.dao.FriendDao#getPendingApprovalFrndRqstList()
	 */
	@Override
	public List<FriendBean> getPendingApprovalFrndRqstList()
			throws FileNotFoundException, ClassNotFoundException, IOException {
		log.info(this.getClass() + ">>> getPendingApprovalFrndRqstList()");
		List<FriendBean> listOfPendingAprlRq = null;
		List<FriendBean> listOfFrndRqst = getAllFriendRequests();
		log.info("listOfFrndRqst...." + listOfFrndRqst);
		if (null != listOfFrndRqst && listOfFrndRqst.size() > 0) {
			listOfPendingAprlRq = new ArrayList<FriendBean>();
			for (FriendBean bean : listOfFrndRqst) {
				if (bean.getStatus().equalsIgnoreCase("P")) {
					listOfPendingAprlRq.add(bean);
				}
				log.info("listOfPendingAprlRq-Size ::" + listOfPendingAprlRq.size());
			}
		}
		log.info(this.getClass() + "<<< getPendingApprovalFrndRqstList()");
		return listOfPendingAprlRq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rbc.snc.dao.FriendDao#acceptOrRejectFriedRequest(com.rbc.snc.model.
	 * FriendBean)
	 */
	@Override
	public String acceptOrRejectFriedRequest(FriendBean friendBean)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		if (null != friendBean) {
			if (friendBean.getStatus().equalsIgnoreCase(SncConstants.FRIEND_ACCEPT)
					|| friendBean.getStatus().equalsIgnoreCase(SncConstants.FRIEND_REJECT)) {
				List<FriendBean> listOfFrndRqst = getAllFriendRequests();
				for (FriendBean friend : listOfFrndRqst) {
					if (friend.getFriendid().equals(friendBean.getFriendid())
							&& friend.getUserid().equals(friendBean.getUserid())
							&& friend.getStatus().equalsIgnoreCase(SncConstants.FRIEND_PENDING)) {
						friend.setStatus(friendBean.getStatus());
						int index = listOfFrndRqst.indexOf(friend);
						listOfFrndRqst.set(index, friend);
						addNewFrndRqstList(listOfFrndRqst);
					}
				}
			}
		}

		return friendBean.getStatus().equalsIgnoreCase(SncConstants.FRIEND_ACCEPT_MSG) ? SncConstants.FRIEND_ACCEPT_MSG
				: SncConstants.FRIEND_REJECT_MSG;

	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private List<FriendBean> getAllFriendRequests() throws FileNotFoundException, IOException, ClassNotFoundException {

		List<FriendBean> friendRequestList = null;
		File file = new File(SncConstants.FILE_PATH_FRIEND);
		if (!file.exists()) {
			FriendBean friendRequest = new FriendBean();
			friendRequest.setFriendid("1");
			friendRequest.setUserid("2");
			friendRequest.setStatus("P");
			friendRequestList = new ArrayList<FriendBean>();
			friendRequestList.add(friendRequest);
			addNewFrndRqstList(friendRequestList);
		} else {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			friendRequestList = (List<FriendBean>) ois.readObject();
			ois.close();
			fis.close();
		}
		return friendRequestList;
	}

	/**
	 * @param friendRequest
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void addNewFrndRqstList(List<FriendBean> friendRequest) throws FileNotFoundException, IOException {

		try {
			File file = new File(SncConstants.FILE_PATH_FRIEND);
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(friendRequest);
			oos.flush();
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
