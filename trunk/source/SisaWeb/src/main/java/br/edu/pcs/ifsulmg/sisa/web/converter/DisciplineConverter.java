/*
 * File:	     DisciplineConverter.java
 * Creation date: 09/07/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Discipline;

@FacesConverter(value = "disciplineConverter")
public class DisciplineConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Discipline)) {
			return "";
		}
		return String.valueOf(((Discipline)value).getDisciplineId());
    }
}