/*
 * File:	     CampusConverter.java
 * Creation date: 10/09/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Campus;

@FacesConverter(value = "campusConverter")
public class CampusConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Campus)value).getCampusId());
    }
}