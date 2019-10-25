/*
* File:	     TimestampRangeValidator.java
* Creation date: 30/08/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.sql.Timestamp;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("timestampRangeValidator")
public class TimestampRangeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
            return;
        }
         
        Object creationDateValue = component.getAttributes().get("creationDate");
        if (creationDateValue==null) {
            return;
        }
         
        Timestamp creationDate = (Timestamp)creationDateValue;
        Timestamp endDate = (Timestamp)value; 
        if (endDate.compareTo(creationDate) < 0) {
        	String message = MessagesUtil.getValue("lbl_creation_date_greater_than_evaluation_date");
        	throw new ValidatorException(BeanUtil.getMessage(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR));
        }
	}
}