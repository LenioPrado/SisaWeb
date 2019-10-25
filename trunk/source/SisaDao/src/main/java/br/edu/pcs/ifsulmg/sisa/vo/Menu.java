/*
 * File:	     Menu.java
 * Creation date: 13/04/2016
 * Author:        Amanda
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Menu.
 */
public class Menu implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2484663648128954874L;

	/**
	 * Instantiates a new menu.
	 */
	public Menu() {

	}
	
	/**
	 * Instantiates a new menu.
	 *
	 * @param description the description
	 */
	public Menu(String description) {
		_description = description;
	}

	public Menu(int menuId, Menu parent, String description, int menuOrder, Page page) {
		_menuId = menuId;
		_parent = parent;
		_description = description;
		_menuOrder = menuOrder;
		_page = page;		
	}
	
	/** The _menu id. */
	private int _menuId;
	
	/** The _parent. */
	private Menu _parent;
	
	/** The _description. */
	private String _description;
	
	/** The _menu order. */
	private int _menuOrder;
	
	/** The _page. */
	private Page _page = new Page();
	
	/** The _sub menus. */
	private List<Menu> _subMenus = new ArrayList<Menu>();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _menuId;
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
		Menu other = (Menu) obj;
		if (_menuId != other._menuId)
			return false;
		return true;
	}
	
	public boolean hasParent(){
		return _parent != null && _parent.getMenuId() > 0;
	}
	
	public void increaseOrder(){
		_menuOrder++;
	}

	public int getMenuId() {
		return _menuId;
	}

	public void setMenuId(int menuId) {
		_menuId = menuId;
	}

	public Menu getParent() {
		return _parent;
	}

	public void setParent(Menu parent) {
		_parent = parent;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getMenuOrder() {
		return _menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		_menuOrder = menuOrder;
	}

	public Page getPage() {
		return _page;
	}

	public void setPage(Page page) {
		_page = page;
	}

	public List<Menu> getSubMenus() {
		return _subMenus;
	}
}