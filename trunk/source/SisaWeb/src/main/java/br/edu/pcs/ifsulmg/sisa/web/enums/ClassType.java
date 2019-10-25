/*
 * File:	     ClassType.java
 * Creation date: 30/05/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.enums;

import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;

public enum ClassType {
	
	PRACTICE(1, MessagesUtil.getValue("lbl_class_type_practice")),
	THEORETICAL(2, MessagesUtil.getValue("lbl_class_type_theoretical"));

    private String label;
    private int index;

    private ClassType(int index, String label) {
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
		for (ClassType vo : ClassType.values()) {
			if (vo.getLabel().equals(label)) {
				return vo.getIndex();
			}
		}
		throw new IllegalArgumentException("Invalid class type enum index");
	}

	public static String getLabel(int index) {
		for (ClassType vo : ClassType.values()) {
			if (vo.getIndex() == index) {
				return vo.getLabel();
			}
		}
		throw new IllegalArgumentException("Invalid class type enum label");
	}
}