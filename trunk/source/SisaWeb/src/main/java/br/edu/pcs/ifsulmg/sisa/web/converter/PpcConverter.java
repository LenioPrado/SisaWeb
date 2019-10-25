/*
 * File:	     PpcConverter.java
 * Creation date: 10/09/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Ppc;

@FacesConverter(value = "ppcConverter")
public class PpcConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Ppc)) {
			return "";
		}
		return String.valueOf(((Ppc)value).getPpcId());
    }
}
