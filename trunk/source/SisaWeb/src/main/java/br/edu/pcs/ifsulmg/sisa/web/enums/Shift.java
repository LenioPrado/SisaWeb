/*
 * File:	     Shift.java
 * Creation date: 29/10/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.enums;

import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;

public enum Shift {
	
	DAYTIME(1, MessagesUtil.getValue("lbl_shift_daytime")),
	NIGHT(2, MessagesUtil.getValue("lbl_shift_night")),
	EVENING(3, MessagesUtil.getValue("lbl_shift_evening")),
	MORNING(4, MessagesUtil.getValue("lbl_shift_morning"));

    private String label;
    private int index;

    private Shift(int index, String label) {
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
		for (Shift vo : Shift.values()) {
			if (vo.getLabel().equals(label)) {
				return vo.getIndex();
			}
		}
		throw new IllegalArgumentException("Invalid shift enum index");
	}

	public static String getLabel(int index) {
		for (Shift vo : Shift.values()) {
			if (vo.getIndex() == index) {
				return vo.getLabel();
			}
		}
		throw new IllegalArgumentException("Invalid shift enum label");
	}
}
