/*
 * File:	     BibliographyType.java
 * Creation date: 02/08/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.enums;

import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;

public enum BibliographyType {
	
	BOOK(1, MessagesUtil.getValue("lbl_bibliography_type_book")),
	LINK(2, MessagesUtil.getValue("lbl_bibliography_type_link")),
	DISSERTATION(3, MessagesUtil.getValue("lbl_bibliography_type_dissertation")),
	THESIS(4, MessagesUtil.getValue("lbl_bibliography_type_thesis"));

    private String label;
    private int index;

    private BibliographyType(int index, String label) {
        this.label = label;
        this.index = index;        
    }
    
    public int getIndex() {
        return index;
    }

    public String getLabel() {
        return label;
    }
    
    public static int getKey(String label) {
		for (BibliographyType vo : BibliographyType.values()) {
			if (vo.getLabel().equals(label)) {
				return vo.getIndex();
			}
		}
		throw new IllegalArgumentException("Invalid bibliography enum index");
	}

	public static String getLabel(int index) {
		for (BibliographyType vo : BibliographyType.values()) {
			if (vo.getIndex() == index) {
				return vo.getLabel();
			}
		}
		throw new IllegalArgumentException("Invalid bibliography enum label");
	}
}