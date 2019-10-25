/*
 * File:	     AuthorBean.java
 * Creation date: 08/07/2015
 * Author:        Lênio
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

import br.edu.pcs.ifsulmg.sisa.exceptions.AuthorException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Author;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.AuthorDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class AuthorBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192734798825886839L;
	
	private Author _author;
	
	private List<Author> _authorsList;
	
	@Inject
	private AuthorDelegate _authorDelegate;
	
	private Boolean _editAuthor;
		
	@PostConstruct
	public void init() {
		_author = new Author();
		loadAuthorsList();
	}
	
	public void save() {
		try {
			if (_editAuthor) {
				_authorDelegate.update(_author);
			}
			else {
				_authorDelegate.insert(_author);
			}
			loadAuthorsList();
			RequestContext.getCurrentInstance().execute("PF('saveAuthorDialog').hide()");
			RequestContext.getCurrentInstance().update("formListAuthors");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (AuthorException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadAuthor(Author author) {
		_author = author;
		_editAuthor = true;
	}

	public void loadAuthorsList() {
		try {
			_authorsList = _authorDelegate.list();
		} catch (AuthorException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_AUTHORS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Author author) {
		try {
			_authorDelegate.delete(author);
			loadAuthorsList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (AuthorException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_AUTHOR_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanAuthor() {
		_author = new Author();
		_editAuthor = false;
	}

	public Author getAuthor() {
		return _author;
	}

	public void setAuthor(Author author) {
		_author = author;
	}

	public List<Author> getAuthorsList() {
		return _authorsList;
	}

	public void setAuthorsList(List<Author> authorsList) {
		_authorsList = authorsList;
	}

	public AuthorDelegate getAuthorDelegate() {
		return _authorDelegate;
	}

	public void setAuthorDelegate(AuthorDelegate authorDelegate) {
		_authorDelegate = authorDelegate;
	}

	public Boolean getEditAuthor() {
		return _editAuthor;
	}

	public void setEditAuthor(Boolean editAuthor) {
		_editAuthor = editAuthor;
	}	
}