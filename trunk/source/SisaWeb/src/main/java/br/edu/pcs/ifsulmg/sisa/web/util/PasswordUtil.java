/*
* File:	     PasswordUtil.java
* Creation date: 04/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import br.edu.pcs.ifsulmg.sisa.util.PasswordEncryptUtil;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;

/**
 * The Class PasswordUtil.
 */
public class PasswordUtil {

	/** The Constant GENERATED_PASSWORD_LENGTH. */
	private final static int GENERATED_PASSWORD_LENGTH = 8;
	
	/**
	 * Gets the random password.
	 *
	 * @return the random password
	 */
	public static String getRandomPassword(){
		
		String characters = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		char[] password = new char[GENERATED_PASSWORD_LENGTH];				
		
		for (int i = 0; i < GENERATED_PASSWORD_LENGTH; i++) {
			password[i] += characters.charAt(random.nextInt(characters.length()));
		}		

		return new String(password);
	}
	
	/**
	 * Encrypt password.
	 *
	 * @param plainPassword the plain password
	 * @return the encrypted password
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static String encryptPassword(String plainPassword) throws NoSuchAlgorithmException	{

		try {
			return PasswordEncryptUtil.encriptyPassword(plainPassword);
		} catch (NoSuchAlgorithmException e) {			
			LogSISA.log.error( e.getMessage(), e);
			e.printStackTrace();
			throw e;
		}
	}
}
