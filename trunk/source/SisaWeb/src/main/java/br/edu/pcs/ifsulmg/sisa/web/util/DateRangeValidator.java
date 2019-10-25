/*
* File:	     DateRangeValidator.java
* Creation date: 04/05/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateRangeValidator")
public class DateRangeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
            return;
        }
         
        Object startDateValue = component.getAttributes().get("startDate");
        if (startDateValue==null) {
            return;
        }
         
        Date startDate = (Date)startDateValue;
        Date endDate = (Date)value; 
        if (endDate.compareTo(startDate) < 0) {
        	String message = MessagesUtil.getValue("lbl_start_date_greater_than_end_date");
        	throw new ValidatorException(BeanUtil.getMessage(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR));
        }
	}
}
