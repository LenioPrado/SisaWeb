/*
* File:	     TimeFullConverter.java
* Creation date: 04/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.converter;

import java.sql.Timestamp;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.edu.pcs.ifsulmg.sisa.util.DateUtil;

/**
 * The Class TimeFullConverter.
 */
public class TimeFullConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String ret = "";
		if(arg2 != null){
			ret = DateUtil.createDateFormatFull((Timestamp)arg2);	
		}
		return ret;
	}
}