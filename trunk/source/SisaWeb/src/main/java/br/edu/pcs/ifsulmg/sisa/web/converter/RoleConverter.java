/*
 * File:	     RoleConverter.java
 * Creation date: 28/08/2015
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Role;

@FacesConverter(value = "roleConverter")
public class RoleConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Role) {
        	return String.valueOf(((Role)value).getRoleId());	
		}
        return "";		
    }
}