package com.rbc.snc.util;

import java.io.Serializable;

import lombok.Data;

@Data
public class FieldType implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String fieldName;
	 protected String errorDesc;
}
