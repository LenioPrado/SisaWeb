/*
 * File:	     VerificationStatus.java
 * Creation date: 30/06/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.enums;

import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;

public enum VerificationStatus {
	
	PENDING(1, MessagesUtil.getValue("lbl_status_pending")),
	APPROVED(2, MessagesUtil.getValue("lbl_status_approved")),
	DISAPPROVED(3, MessagesUtil.getValue("lbl_status_disapproved"));

    private String label;
    private int index;

    private VerificationStatus(int index, String label) {
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
		for (VerificationStatus vo : VerificationStatus.values()) {
			if (vo.getLabel().equals(label)) {
				return vo.getIndex();
			}
		}
		throw new IllegalArgumentException("Invalid verification status enum index");
	}

	public static String getLabel(int index) {
		for (VerificationStatus vo : VerificationStatus.values()) {
			if (vo.getIndex() == index) {
				return vo.getLabel();
			}
		}
		throw new IllegalArgumentException("Invalid verification status enum label");
	}
}
