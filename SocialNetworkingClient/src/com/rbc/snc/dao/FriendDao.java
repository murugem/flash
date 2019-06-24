package com.rbc.snc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.rbc.snc.model.FriendBean;

public interface FriendDao {

	String makeFriendRequest(FriendBean friend) throws FileNotFoundException, IOException, ClassNotFoundException ;

	List<FriendBean> getPendingApprovalFrndRqstList() throws FileNotFoundException, ClassNotFoundException, IOException;

	String acceptOrRejectFriedRequest(FriendBean friendBean) throws FileNotFoundException, IOException, ClassNotFoundException;
}
