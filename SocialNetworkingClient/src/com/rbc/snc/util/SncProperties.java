package com.rbc.snc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//import com.rbc.user.exceptions.UserException;

public class SncProperties {

	static final Properties prop = new Properties();
	InputStream inputStream;
	static String propFileName = "user.properties";

	public SncProperties() {

	}

	/**
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getPropertyValue(String key) throws IOException {

		try {
			inputStream = getClass().getClassLoader().getResourceAsStream("\\resources\\user.properties");
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				//throw new UserException("user.properties FileNotFound!. Please check the filename..", 4001);
			}
		//	String strProp = prop.getProperty(key);
			return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
