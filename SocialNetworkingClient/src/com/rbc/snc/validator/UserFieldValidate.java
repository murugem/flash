package com.rbc.snc.validator;

import com.rbc.snc.model.UserD;

/**
 * @author DRBCG28
 *
 */
public class UserFieldValidate {

	/**
	 * @param user
	 * @return
	 */
	public int validateUserFields(UserD user) {

		int validatorResult = 0;
		int id = user.getUserId();
		//String profession = user.getProfession();

		if (id == (int) id) {
			validatorResult = 1;
		}
		if (id == 0) {
			validatorResult = 2;
		} else if (id > 500) {
			validatorResult = 9;
		}

		return validatorResult;
	}

}
