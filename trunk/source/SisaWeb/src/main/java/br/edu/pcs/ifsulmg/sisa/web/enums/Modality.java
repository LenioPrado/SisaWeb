/*
 * File:	     Modality.java
 * Creation date: 04/11/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.enums;

import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;

public enum Modality {
	
	HIGHER(1, MessagesUtil.getValue("lbl_modality_higher")),
	POST_GRADUATE(2, MessagesUtil.getValue("lbl_modality_post_graduate")),
	SUBSEQUENT(3, MessagesUtil.getValue("lbl_modality_subsequent")),
	INTEGRATED(4, MessagesUtil.getValue("lbl_modality_integrated")),
	CONCURRENT(5, MessagesUtil.getValue("lbl_modality_concurrent"));

    private String label;
    private int index;

    private Modality(int index, String label) {
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
		for (Modality vo : Modality.values()) {
			if (vo.getLabel().equals(label)) {
				return vo.getIndex();
			}
		}
		throw new IllegalArgumentException("Invalid modality enum index");
	}

	public static String getLabel(int index) {
		for (Modality vo : Modality.values()) {
			if (vo.getIndex() == index) {
				return vo.getLabel();
			}
		}
		throw new IllegalArgumentException("Invalid modality enum label");
	}
}