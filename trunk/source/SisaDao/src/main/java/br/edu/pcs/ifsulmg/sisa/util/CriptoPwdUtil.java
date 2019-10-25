/*
* File:	     CriptoPwdUtil.java
* Creation date:
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

import br.edu.pcs.ifsulmg.sisa.util.LogSISA;

/**
 * The Class CriptoPwdUtil.
 *
 */
public class CriptoPwdUtil {
	
	
	private CriptoPwdUtil() {}
	
	/**
	 * Cripto pwd.
	 *
	 * @param pwd the pwd
	 * @return the string
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static String criptoPwd(String pwd) throws NoSuchAlgorithmException{
		pwd+=pwd;
        MessageDigest md = MessageDigest.getInstance("sha");
        byte[] hashGerado = null;
        String stringpwd = "";
        
		try {
			hashGerado = md.digest(pwd.getBytes("UTF-8"));
			stringpwd = new String (Base64.encodeBase64(hashGerado));
		} catch (UnsupportedEncodingException e) {
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}

        return stringpwd;
	}
	
	/**
	 * Verify pwd.
	 *
	 * @param pwdInput the pwd input
	 * @param pwdBd the pwd bd
	 * @return true, if successful
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static boolean verifyPwd(String pwdInput, String pwdBd) throws NoSuchAlgorithmException{
		pwdInput = criptoPwd(pwdInput);
		return pwdInput.equalsIgnoreCase(pwdBd);
	}
}
