/*
* File:	     TimestampConverter.java
* Creation date: 02/07/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.util.DateUtil;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

/**
 * The Class TimestampConverter.
 */
@FacesConverter("timestampConverter")
public class TimestampConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(arg2 != null){	    
	        return new Timestamp(convertToDate(arg2).getTime());
		}
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if(arg2 != null){
			try {
				Timestamp time = (Timestamp)arg2;
				return DateUtil.createDateFormatFull(time);
			} catch (Exception e) {
				LogSISA.log.error(e.getMessage());
				e.printStackTrace();
			}
		}		
		return arg2.toString();
	}
	
	private Date convertToDate(String dateValue){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = null;
        
        try {
        	date = sdf.parse(dateValue);
		} catch (ParseException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_DATE_CONVERSION_ERROR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
        
        return date;
	}
}