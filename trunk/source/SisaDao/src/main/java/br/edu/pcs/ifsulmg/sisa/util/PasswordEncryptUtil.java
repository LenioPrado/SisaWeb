/*
* File:	     PasswordEncryptUtil.java
* Creation date: 04/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

import br.edu.pcs.ifsulmg.sisa.util.LogSISA;

/**
 * The Class PasswordEncryptUtil.
 *
 */
public class PasswordEncryptUtil {
		
	private PasswordEncryptUtil() {}

	/**
	 * Encripty password.
	 *
	 * @param password the password
	 * @return the string
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static String encriptyPassword(String password) throws NoSuchAlgorithmException{
		password+=password;
        MessageDigest digest = MessageDigest.getInstance("sha");
        byte[] hash = null;
        String newPassword = "";
        
		try {
			hash = digest.digest(password.getBytes("UTF-8"));
			newPassword = new String (Base64.encodeBase64(hash));
		} catch (UnsupportedEncodingException e) {
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}

        return newPassword;
	}
	
	/**
	 * Password match test.
	 *
	 * @param providedValue the provided value
	 * @param correctValue the correct value
	 * @return true, if successful
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static boolean passwordMatchTest(String providedValue, String correctValue) throws NoSuchAlgorithmException{
		providedValue = encriptyPassword(providedValue);
		return providedValue.equalsIgnoreCase(correctValue);
	}
}