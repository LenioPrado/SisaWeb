/*
 * File:	     ResponsibleConverter.java
 * Creation date: 23/08/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Responsible;

@FacesConverter(value = "responsibleConverter")
public class ResponsibleConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Responsible)) {
			return "";
		}
		return String.valueOf(((Responsible)value).getResponsibleId());
    }
}
