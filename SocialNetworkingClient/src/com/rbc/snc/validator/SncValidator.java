package com.rbc.snc.validator;

import java.util.ArrayList;
import java.util.List;

import com.rbc.snc.model.UserD;
import com.rbc.snc.util.CommonUtil;
import com.rbc.snc.util.FieldType;
import com.rbc.snc.util.SncConstants;

public class SncValidator {

	public List<FieldType> validateRequiredUserFields(UserD user) {
		FieldType fieldType = null;
		List<FieldType> fieldList = new ArrayList<FieldType>();
		if (null != user) {
			fieldType = new FieldType();
			int status = CommonUtil.validatUserId(user);
			if (status == 2) {
				fieldType.setFieldName(SncConstants.USER_ID);
				fieldType.setErrorDesc(SncConstants.USER_ID_ZERO);
				if (null != fieldType) {
					fieldList.add(fieldType);
				}
			} else if (status == 9) {
				fieldType.setFieldName(SncConstants.USER_ID);
				fieldType.setErrorDesc(SncConstants.USER_ID_OUTOF_RANGE);
				if (null != fieldType) {
					fieldList.add(fieldType);
				}
			}
		}

		if (null != user && null != user.getEmail()) {
			if (!CommonUtil.isValidEmailAddress(user.getEmail())) {
				fieldType = new FieldType();
				fieldType.setFieldName(SncConstants.EMAIL_ID);
				fieldType.setErrorDesc(SncConstants.EMAIL_ADDRESS_ERROR);
				if (null != fieldType) {
					fieldList.add(fieldType);
				}
			}
		}

		if (null != user && !CommonUtil.chkGender(user.getGender())) {
			fieldType = new FieldType();
			fieldType.setFieldName(SncConstants.SEX);
			fieldType.setErrorDesc(SncConstants.GENDER_ERROR);
			if (null != fieldType) {
				fieldList.add(fieldType);
			}
		}
		if (null != user && !CommonUtil.isValidatePhoneNumber(user.getPhone())) {
			fieldType = new FieldType();
			fieldType.setFieldName(SncConstants.PHONE);
			fieldType.setErrorDesc(SncConstants.PHONE_NUMBER_ERROR);
			if (null != fieldType) {
				fieldList.add(fieldType);
			}
		}
		return fieldList;
	}

	public List<FieldType> validateRequiredMessageFields() {
		return null;

	}

	public List<FieldType> validateRequiredFriendFields() {
		return null;

	}
}
