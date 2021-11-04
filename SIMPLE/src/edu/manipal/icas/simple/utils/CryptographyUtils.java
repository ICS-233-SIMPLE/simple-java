package edu.manipal.icas.simple.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class that provides cryptography methods to hash passwords.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public final class CryptographyUtils {
	private CryptographyUtils() {
	}

	/**
	 * Hashes a string input using the SHA-256 hash algorithm.
	 * 
	 * @param string text to be hashed
	 * @return hashed string
	 */
	public static String hashString(String string) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
			return new String(hash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
