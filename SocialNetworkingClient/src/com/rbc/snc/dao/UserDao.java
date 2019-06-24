package com.rbc.snc.dao;

import java.util.List;

import com.rbc.snc.model.UserD;

public interface UserDao {

	public List<UserD> getAllUsersProfile();

	public UserD getUserProfile(int id);

	public int addUser(UserD adUser);

	public int updateUser(UserD updUser);

	public int deleteUser(int id);

	public void saveUserList(List<UserD> userList);
}
