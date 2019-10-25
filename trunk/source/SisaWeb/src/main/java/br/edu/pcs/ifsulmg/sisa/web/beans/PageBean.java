/*
 * File:	     PageBean.java
 * Creation date: 30/03/2016
 * Author:        Rarvs
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.PageException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Page;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PageDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class PageBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192797458825886839L;
	
	private Page _page;
	
	private List<Page> _pagesList;
	
	@Inject
	private PageDelegate _pageDelegate;
	
	private Boolean _editPage;
	
	@PostConstruct
	public void init() {
		_page = new Page();
		loadPagesList();
	}
	
	public void save() {
		try {
			if (_editPage) {
				_pageDelegate.update(_page);
			}
			else {
				_pageDelegate.insert(_page);
			}
			loadPagesList();
			RequestContext.getCurrentInstance().execute("PF('savePageDialog').hide()");
			RequestContext.getCurrentInstance().update("formListPages");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_PAGE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (PageException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_PAGE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadPage(Page page) {
		_page = page;
		_editPage = true;
	}

	public void loadPagesList() {
		try {
			_pagesList = _pageDelegate.list();
		} catch (PageException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PAGE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Page page) {
		try {
			_pageDelegate.delete(page);
			loadPagesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_PAGE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (PageException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_PAGE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_PAGE_MENU)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PAGE_MENU;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_PAGE_ROLE)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PAGE_ROLE;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanPage() {
		_page = new Page();
		_editPage = false;
	}

	public Page getPage() {
		return _page;
	}

	public void setPage(Page page) {
		_page = page;
	}

	public List<Page> getPagesList() {
		return _pagesList;
	}

	public void setPagesList(List<Page> pagesList) {
		_pagesList = pagesList;
	}

	public Boolean getEditPage() {
		return _editPage;
	}

	public void setEditPage(Boolean editPage) {
		_editPage = editPage;
	}	
}