package com.rbc.snc.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.model.FriendBean;

public interface FriendService {
	
	String makeFriendRequest(FriendBean friend) throws SncException, ClassNotFoundException;

	List<FriendBean> getPendingApprovalFrndRqstList() throws SncException; 

	String acceptOrRejectFriedRequest(FriendBean friendBean) throws SncException;
}
