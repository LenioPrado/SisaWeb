/*
 * File:	     SelectItemsBaseConverter.java
 * Creation date: 09/07/2015
 * Author:        L�nio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public abstract class SelectItemsBaseConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {        
        return SelectItemsUtils.findValueByStringConversion(context, component, value, this);    
    }    
}
