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

import com.rbc.snc.model.UserD;
import com.rbc.snc.util.SncConstants;

/**
 * @author DRBCG28
 *
 */
public class UserDaoImpl implements UserDao {

	/**
	 * @return This method returns all the users data available in the file in
	 *         List format.
	 */
	@SuppressWarnings("unchecked")
	public List<UserD> getAllUsersProfile() {

		List<UserD> userList = null;
		try {
			File file = new File(SncConstants.FILE_PATH_USER);
			if (!file.exists()) {
				UserD user = new UserD();
				user.setUserId(1);
				/*user.setFirst_name("Jhon");
				user.setLast_name("J");*/
				user.setName("Jhon");
				user.setEmail("jhon@rbc.com");
				user.setGender("Male");
				user.setPhone("90000000001");
				userList = new ArrayList<UserD>();
				userList.add(user);
				saveUserList(userList);
			} else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				userList = (List<UserD>) ois.readObject();
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * @param id
	 * @return This returns a particular user data based on parameter received
	 *         from UserService
	 */
	public UserD getUserProfile(int id) {

		List<UserD> userList = getAllUsersProfile();

		for (UserD user : userList) {
			if (user.getUserId() == id) {
				return user;
			}
		}
		return null;
	}

	/**
	 * @param adUser
	 * @return This method receives user profile data to add to the file. This
	 *         method checks whether the user profile data is already exists in
	 *         file, if user profile data is not available in the file then this
	 *         method will add else it throws an exception
	 */
	public int addUser(UserD adUser) {
		List<UserD> userList = getAllUsersProfile();
		boolean userExists = false;
		for (UserD user : userList) {
			if (user.getUserId() == adUser.getUserId()) {
				userExists = true;
				break;
			}
		}
		if (!userExists) {
			userList.add(adUser);
			saveUserList(userList);
			return 1;
		}
		return 0;
	}

	/**
	 * @param updUser
	 * @return This method
	 */
	public int updateUser(UserD updUser) {
		List<UserD> userList = getAllUsersProfile();

		for (UserD user : userList) {
			if (user.getUserId() == updUser.getUserId()) {
				int index = userList.indexOf(user);
				userList.set(index, updUser);
				saveUserList(userList);
				return 1;
			}
		}
		return 0;
	}

	/**
	 * @param id
	 * @return
	 */
	public int deleteUser(int id) {
		List<UserD> userList = getAllUsersProfile();

		for (UserD user : userList) {
			if (user.getUserId() == id) {
				int index = userList.indexOf(user);
				userList.remove(index);
				saveUserList(userList);
				return 1;
			}
		}
		return 0;
	}

	/**
	 * @param userList
	 */
	public void saveUserList(List<UserD> userList) {
		try {
			File file = new File(SncConstants.FILE_PATH_USER);
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
