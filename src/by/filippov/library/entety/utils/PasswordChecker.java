package by.filippov.library.entety.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class PasswordChecker {
	private static final Logger LOG = Logger.getLogger(PasswordChecker.class);

	public static String encodePassword(String password) {
		String passwordMD5 = null;
		try {
			passwordMD5 = new String(MessageDigest.getInstance("MD5").digest(
					password.getBytes()));
		} catch (NoSuchAlgorithmException e1) {
			LOG.error("Very unexpected exception");
		}
		return passwordMD5;
	}
}
