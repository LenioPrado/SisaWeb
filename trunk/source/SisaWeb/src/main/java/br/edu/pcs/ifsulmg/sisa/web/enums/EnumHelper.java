/*
* File:	     EnumHelper.java
* Creation date: 02/09/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.enums;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EnumHelper implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5638291067352857307L;

	public String getShiftLabel(int index) {
        return index == 0 ? "" : Shift.getLabel(index);
    }
	
	public Shift[] getShifts() {
        return Shift.values();
    }
	
	public String getModalityLabel(int index) {
        return index == 0 ? "" : Modality.getLabel(index);
    }
	
	public Modality[] getModalities() {
        return Modality.values();
    }
	
	public String getVerificationStatusLabel(int index) {
        return index == 0 ? "" : VerificationStatus.getLabel(index);
    }
	
	public VerificationStatus[] getVerificationStatus() {
        return VerificationStatus.values();
    }
	
	public String getBibliographyLabel(int index) {
        return index == 0 ? "" : BibliographyType.getLabel(index);
    }
	
	public BibliographyType[] getBibliographyTypes() {
        return BibliographyType.values();
    }
	
	public String getClassTypeLabel(int index) {
		return index == 0 ? "" : ClassType.getLabel(index);
    }
	
	public ClassType[] getClassTypes() {
        return ClassType.values();
    }
}
