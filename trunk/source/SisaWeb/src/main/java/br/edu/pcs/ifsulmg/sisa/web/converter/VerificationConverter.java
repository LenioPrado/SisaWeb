/*
 * File:	     VerificationConverter.java
 * Creation date: 28/08/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Verification;

@FacesConverter(value = "verificationConverter")
public class VerificationConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Verification)value).getVerificationId());
    }
}