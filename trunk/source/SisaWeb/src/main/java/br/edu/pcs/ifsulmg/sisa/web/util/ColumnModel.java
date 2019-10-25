/*
* File:	     ColumnModel.java
* Creation date: 06/05/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.io.Serializable;

public class ColumnModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -947261950375619503L;

	public ColumnModel(String header, String propertyName){
		_header = header;
		_propertyName = propertyName;
	}
	
	private String _header;
	
	private String _propertyName;

	public String getHeader() {
		return _header;
	}

	public void setHeader(String header) {
		_header = header;
	}

	public String getPropertyName() {
		return _propertyName;
	}

	public void setPropertyName(String propertyName) {
		_propertyName = propertyName;
	}
}
