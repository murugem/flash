package com.rbc.snc.service;

import java.io.IOException;
import java.util.List;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.model.UserD;
import com.rbc.snc.util.StatusType;

public interface UserService {
	public List<UserD> getAllUsersProfile() throws SncException;
	public UserD getUserProfile(int id) throws SncException;
	public StatusType addUser(UserD adUser) throws IOException; 
	public StatusType updateUser(UserD updUser) throws SncException;
	public int deleteUser(int id);
	public void saveUserList(List<UserD> userList);
}
