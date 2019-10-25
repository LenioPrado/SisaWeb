/*
 * File:	     CourseConverter.java
 * Creation date: 24/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Course;

@FacesConverter(value = "courseConverter")
public class CourseConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Course)) {
			return "";
		}
		return String.valueOf(((Course)value).getCourseId());
    }
}