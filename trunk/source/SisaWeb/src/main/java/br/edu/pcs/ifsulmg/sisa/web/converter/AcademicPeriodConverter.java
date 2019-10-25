/*
 * File:	     AcademicPeriodConverter.java
 * Creation date: 27/08/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.AcademicPeriod;

@FacesConverter(value = "academicPeriodConverter")
public class AcademicPeriodConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof AcademicPeriod)) {
			return "";
		}
		return String.valueOf(((AcademicPeriod)value).getAcademicPeriodId());
    }
}