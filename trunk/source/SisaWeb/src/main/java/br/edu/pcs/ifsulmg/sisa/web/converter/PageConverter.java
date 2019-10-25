/*
 * File:	     PageConverter.java
 * Creation date: 04/05/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Page;

@FacesConverter(value = "pageConverter")
public class PageConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Page)) {
			return "";
		}
		
		return String.valueOf(((Page)value).getPageId());
    }
}