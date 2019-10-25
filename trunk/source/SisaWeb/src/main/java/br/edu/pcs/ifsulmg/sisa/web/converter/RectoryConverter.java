/*
 * File:	     RectoryConverter.java
 * Creation date: 10/09/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Rectory;

@FacesConverter(value = "rectoryConverter")
public class RectoryConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Rectory)value).getRectoryId());
    }
}