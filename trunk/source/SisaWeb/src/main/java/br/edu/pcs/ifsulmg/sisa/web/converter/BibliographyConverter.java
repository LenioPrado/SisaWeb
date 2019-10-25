/*
 * File:	     BibliographyConverter.java
 * Creation date: 13/08/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;

@FacesConverter(value = "bibliographyConverter")
public class BibliographyConverter extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Bibliography)value).getBibliographyId());
    }
}