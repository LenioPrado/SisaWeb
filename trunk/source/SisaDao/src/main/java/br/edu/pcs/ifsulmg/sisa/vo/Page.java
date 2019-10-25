/*
 * File:	     Author.java
 * Creation date: 30/03/2016
 * Author:        PagodeiroDoMal
 */
package br.edu.pcs.ifsulmg.sisa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Page.
 */
public class Page implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4164481328701788463L;
	
	
	/**
	 * Instantiates a new page.
	 */
	public Page() {

	}

	/**
	 * Instantiates a new page.
	 *
	 * @param pageId the page id
	 * @param name the name
	 */
	public Page(int pageId, String name, String url) {
		_name = name;
		_pageId = pageId;
		_url = url;
	}
	
	/** The _page id. */
	private int _pageId;
	
	/** The _name. */
	private String _name;
	
	/** The _url. */
	private String _url;
	
	/** The _actions. */
	private List<Action> _actions = new ArrayList<Action>();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _pageId;
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
		Page other = (Page) obj;
		if (_pageId != other._pageId)
			return false;
		return true;
	}
	
	public int getPageId() {
		return _pageId;
	}
	
	public void setPageId(int pageId) {
		_pageId = pageId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public List<Action> getActions() {
		return _actions;
	}

	public void setActions(List<Action> actions) {
		_actions = actions;
	}
}