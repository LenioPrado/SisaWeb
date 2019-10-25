/*
* File:	     FloatConverter.java
* Creation date: 08/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.converter;

import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * The Class FloatConverter.
 */
public class FloatConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String ret = "";
		if(arg2 != null){
			ret = NumberFormat.getNumberInstance(new Locale ("pt", "BR")).format((Float)arg2);
		}
		return ret;
	}
}
