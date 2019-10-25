/*
 * File:	     AuthorConverter.java
 * Creation date: 13/08/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Author;

@FacesConverter(value = "authorConverter")
public class AuthorConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Author)value).getAuthorId());
    }
}