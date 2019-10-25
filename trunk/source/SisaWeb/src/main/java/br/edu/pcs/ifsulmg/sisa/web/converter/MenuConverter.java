/*
 * File:	     MenuConverter.java
 * Creation date: 04/05/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Menu;

@FacesConverter(value = "menuConverter")
public class MenuConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Menu)value).getMenuId());
    }
}