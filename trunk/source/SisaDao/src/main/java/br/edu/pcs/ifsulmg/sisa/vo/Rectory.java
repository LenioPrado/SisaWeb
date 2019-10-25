/*
 * File:	     Rectory.java
 * Creation date: 11/06/2015
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;

/**
 * The Class Rectory.
 */
public class Rectory implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1484260630240651343L;
	
	/**
	 * Instantiates a new rectory.
	 */
	public Rectory(){		
		
	}
	
	/**
	 * Instantiates a new rectory.
	 *
	 * @param rectoryId the rectory id
	 * @param cnpj the cnpj
	 * @param name the name
	 */
	public Rectory(int rectoryId, String cnpj, String name){
		_rectoryId = rectoryId;
		_cnpj = cnpj;
		_name = name;
	}
	
	/** The _rectory id. */
	private int _rectoryId;
	
	/** The _cnpj. */
	private String _cnpj;
	
	/** The _name. */
	private String _name;	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _rectoryId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectory other = (Rectory) obj;
		if (_rectoryId != other._rectoryId)
			return false;
		return true;
	}
	
	/**
	 * Gets the rectory id.
	 *
	 * @return the rectory id
	 */
	public int getRectoryId() {
		return _rectoryId;
	}

	/**
	 * Sets the rectory id.
	 *
	 * @param rectoryId the new rectory id
	 */
	public void setRectoryId(int rectoryId) {
		_rectoryId = rectoryId;
	}
	
	/**
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {
		return _cnpj;
	}

	/**
	 * Sets the cnpj.
	 *
	 * @param cnpj the new cnpj
	 */
	public void setCnpj(String cnpj) {
		_cnpj = cnpj;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		_name = name;
	}
}