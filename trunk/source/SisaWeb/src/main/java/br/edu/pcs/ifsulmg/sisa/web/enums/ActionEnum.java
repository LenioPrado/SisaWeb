/*
* File:	     ActionEnum.java
* Creation date: 15/06/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.enums;

public enum ActionEnum {
	LIST(1,"Listar"),
	INSERT (2,"Incluir"),
	EDIT (3,"Editar"),
	DELETE (4,"Excluir");
	
	private String label;
    private int index;

    private ActionEnum(int index, String label) {
        this.label = label;
        this.index = index;        
    }
    
    public int getIndex() {
        return index;
    }

    public String getLabel() {
        return label;
    }	
}
