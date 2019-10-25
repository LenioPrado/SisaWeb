/*
 * File:	     CommonBean.java
 * Creation date: 20/06/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.util.List;

import br.edu.pcs.ifsulmg.sisa.vo.Action;
import br.edu.pcs.ifsulmg.sisa.vo.Page;
import br.edu.pcs.ifsulmg.sisa.web.enums.ActionEnum;
import br.edu.pcs.ifsulmg.sisa.web.util.UserAccessUtils;

/**
 * The Class CommonBean.
 */
public abstract class CommonBean {
	
	public CommonBean() {
		loadPageAccess();
	}
	
	private void loadPageAccess(){
		_currentPage = UserAccessUtils.getInstance().getCurrentPageFromSession();
		
		if (_currentPage != null) {
			for (Action action : _currentPage.getActions()) {
				if (action.getActionId() == ActionEnum.INSERT.getIndex()) {
					_canInsert = true;
					continue;
				}
				if (action.getActionId() == ActionEnum.EDIT.getIndex()) {
					_canEdit = true;
					continue;
				}
				if (action.getActionId() == ActionEnum.DELETE.getIndex()) {
					_canDelete = true;
					continue;
				}
			}
		}
	}

	/** The _current page. */
	private Page _currentPage;
	
	/** The _can edit. */
	private Boolean _canEdit = false;
	
	/** The _can insert. */
	private Boolean _canInsert = false;
	
	/** The _can delete. */
	private Boolean _canDelete = false;
	
	/** The _filtered. */
	private List<Object> _filtered;
	
	/** The _sub filtered. */
	private List<Object> _subFiltered;

	/**
	 * Gets the can edit.
	 *
	 * @return the can edit
	 */
	public Boolean getCanEdit() {
		return _canEdit;
	}

	/**
	 * Sets the can edit.
	 *
	 * @param canEdit the new can edit
	 */
	public void setCanEdit(Boolean canEdit) {
		_canEdit = canEdit;
	}

	/**
	 * Gets the can insert.
	 *
	 * @return the can insert
	 */
	public Boolean getCanInsert() {
		return _canInsert;
	}

	/**
	 * Sets the can insert.
	 *
	 * @param canInsert the new can insert
	 */
	public void setCanInsert(Boolean canInsert) {
		_canInsert = canInsert;
	}

	/**
	 * Gets the can delete.
	 *
	 * @return the can delete
	 */
	public Boolean getCanDelete() {
		return _canDelete;
	}

	/**
	 * Sets the can delete.
	 *
	 * @param canDelete the new can delete
	 */
	public void setCanDelete(Boolean canDelete) {
		_canDelete = canDelete;
	}

	/**
	 * Gets the filtered.
	 *
	 * @return the filtered
	 */
	public List<Object> getFiltered() {
		return _filtered;
	}

	/**
	 * Sets the filtered.
	 *
	 * @param filtered the new filtered
	 */
	public void setFiltered(List<Object> filtered) {
		_filtered = filtered;
	}

	/**
	 * Gets the sub filtered.
	 *
	 * @return the sub filtered
	 */
	public List<Object> getSubFiltered() {
		return _subFiltered;
	}

	/**
	 * Sets the sub filtered.
	 *
	 * @param subFiltered the new sub filtered
	 */
	public void setSubFiltered(List<Object> subFiltered) {
		_subFiltered = subFiltered;
	}	
}