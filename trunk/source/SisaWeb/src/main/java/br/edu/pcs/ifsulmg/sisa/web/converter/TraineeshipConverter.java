/*
 * File:	     TraineeshipConverter.java
 * Creation date: 02/07/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;

@FacesConverter(value = "traineeshipConverter")
public class TraineeshipConverter  extends SelectItemsBaseConverter {
    
	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Traineeship)value).getTraineeshipId());
    }
}