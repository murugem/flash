package com.rbc.snc.util;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class StatusType implements Serializable {
	
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		protected String statusCode;
	    protected String error;
	    protected String statusDesc;
	    protected List<FieldType> fieldList;
	
}
