/*
* File:	     MessagesUtil.java
* Creation date: 04/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * The Class MessagesUtil.
 */
public class MessagesUtil {

	/** The bundle. */
	private static ResourceBundle bundle;
	
	/**
	 * Instantiates a new messages util.
	 */
	private MessagesUtil() {	}

	/**
	 * Gets the bundle.
	 *
	 * @return the bundle
	 */
	private static ResourceBundle getBundle() {

		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			if(context == null){
				bundle = ResourceBundle.getBundle("message_pt_BR");
			}else{
				bundle = context.getApplication().getResourceBundle(context, "msg");	
			}
			
		}
		return bundle;
	}

	/**
	 * Gets the value.
	 *
	 * @param key the key
	 * @return the value
	 */
	public static String getValue(String key) {

		String result = null;
		try {
			result = getBundle().getString(key);
		} catch (MissingResourceException e) {
			result = "???" + key + "??? not found";
		}
		return result;
	}
}