package com.rbc.snc.util;

import java.util.UUID;

/*
import java.io.Closeable;
import java.util.Deque;

import com.rbc.snc.Exception.GeneratorException;
*/
/**
 * Generate short, unique IDs.
 */
public class IDGenerator {

	public static void main(String[] args) {
		idGenerate();
	}

	public static String idGenerate() {
		// generate random UUIDs
		UUID uid = UUID.randomUUID();
		String uqId = ((String.valueOf(uid)).substring((String.valueOf(uid).lastIndexOf("-")) + 1));
		System.out.println(uqId);
		return uqId;
	}

}