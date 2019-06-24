package com.rbc.snc.service;

import java.io.IOException;
import java.util.List;

import com.rbc.snc.Exception.SncException;
import com.rbc.snc.dao.UserDao;
import com.rbc.snc.dao.UserDaoImpl;
import com.rbc.snc.model.UserD;
import com.rbc.snc.util.FieldType;
import com.rbc.snc.util.SncConstants;
import com.rbc.snc.util.SncProperties;
import com.rbc.snc.util.StatusType;
import com.rbc.snc.validator.SncValidator;
import com.rbc.snc.validator.UserFieldValidate;

public class UserServiceImpl implements UserService {

	UserFieldValidate userValidate = new UserFieldValidate();
	SncProperties prop = new SncProperties();
	UserDao userDao = new UserDaoImpl();
	SncValidator validator = new SncValidator();
	StatusType status = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.UserService#getAllUsersProfile()
	 */
	@Override
	public List<UserD> getAllUsersProfile() throws SncException {
		try {
			List<UserD> userProfileList = userDao.getAllUsersProfile();
			if (null == userProfileList || 0 == userProfileList.size()) {

				throw new SncException(SncConstants.USER_PROFILE_EMPTY);
			}
		} catch (Exception e) {
			throw new SncException("Error Occurred While fetching list of user profiles : "+e.getMessage());
		}
		return userDao.getAllUsersProfile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.UserService#getUserProfile(int)
	 */
	@Override
	public UserD getUserProfile(int userId) throws SncException {
		UserD user = null;
		try {
			user = new UserD();
			user = userDao.getUserProfile(userId);
			if (user == null) {
				throw new SncException(SncConstants.USER_ID_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new SncException(SncConstants.IO_OPERATION_FAILED + e.getMessage());
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.UserService#addUser(com.rbc.snc.model.UserD)
	 */
	@Override
	public StatusType addUser(UserD user) throws IOException {
		try {
			List<FieldType> fieldList = validator.validateRequiredUserFields(user);
			status = new StatusType();
			if (null != fieldList && fieldList.size() != 0) {
				status.setFieldList(fieldList);
				return status;
			} else {
				int result = userDao.addUser(user);
				if (result == 1) {
					status.setStatusCode(SncConstants.STATUS_CODE_SUCCESS);
					status.setStatusDesc(SncConstants.SUCCESS);
					return status;
				} else if (result == 0) {
					status.setStatusCode(SncConstants.STATUS_CODE1);
					status.setStatusDesc(SncConstants.USER_EXITS);
					return status;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// propertyString = prop.getPropertyValue("db.addFailed");
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbc.snc.service.UserService#updateUser(com.rbc.snc.model.UserD)
	 */
	@Override
	public StatusType updateUser(UserD user) throws SncException {
		List<FieldType> fieldList = validator.validateRequiredUserFields(user);
		status = new StatusType();
		if (null != fieldList && fieldList.size() != 0) {
			status.setFieldList(fieldList);
			return status;
		} else {
			try{
			int result = userDao.updateUser(user);
			if (result == 1) {
				status.setStatusCode(SncConstants.STATUS_CODE_SUCCESS);
				status.setStatusDesc(SncConstants.SUCCESS);
				return status;
			}
			}catch(Exception e){
				throw new SncException("Error Occured While Updating user"+e.getMessage());
			}
		}
		return status;
	}

	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveUserList(List<UserD> userList) {
		// TODO Auto-generated method stub

	}

}
