/*
 * File:	     BibliographyAuthorBean.java
 * Creation date: 12/08/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.AuthorException;
import br.edu.pcs.ifsulmg.sisa.exceptions.BibliographyAuthorException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Author;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.vo.BibliographyAuthor;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.AuthorDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.BibliographyAuthorDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class BibliographyAuthorBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7582715734091040654L;
	
	@Inject
	private AuthorDelegate _authorDelegate;
	
	@Inject
	private BibliographyAuthorDelegate _bibliographyAuthorDelegate;
	
	private Author _author;
	
	private List<Author> _authorsList;
	
	private List<BibliographyAuthor> _bibliographiesAuthorsList;
	
	private Boolean _editBibliographyAuthor;
	
	private List<Bibliography> _selectedBibliographies;
	
	private List<Bibliography> _currentBibliographies;
		
	@PostConstruct
	public void init() {
		_author = new Author();		
		_selectedBibliographies = new ArrayList<Bibliography>();
		_currentBibliographies = new ArrayList<Bibliography>();
		loadAuthorBibliographiesList();
	}
	
	public void save() {
		try {			
			if (_editBibliographyAuthor)
				_bibliographyAuthorDelegate.delete(_author, getBibliographiesToDelete());

			_bibliographyAuthorDelegate.insert(_author, getBibliographiesToSave());

			loadAuthorBibliographiesList();
			RequestContext.getCurrentInstance().execute("PF('saveBibliographyAuthorDialog').hide()");
			RequestContext.getCurrentInstance().update("formListBibliographyAuthor");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_BIBLIOGRAPHY_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (BibliographyAuthorException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_BIBLIOGRAPHY_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_CONSTRAINT_BIBLIOGRAPHY_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private List<Bibliography> getBibliographiesToSave(){
		List<Bibliography> toSave = new ArrayList<Bibliography>();
		
		for (int i = 0; i < _selectedBibliographies.size(); i++) {
			Bibliography selectedBibliography = _selectedBibliographies.get(i);
			if (!_currentBibliographies.contains(selectedBibliography) && !toSave.contains(selectedBibliography)) {
				toSave.add(selectedBibliography);
			}
		}
		return toSave;
	}
	
	private List<Bibliography> getBibliographiesToDelete(){
		List<Bibliography> toDelete = new ArrayList<Bibliography>();
		
		for (int i = 0; i < _currentBibliographies.size(); i++) {
			Bibliography currentDiscipline = _currentBibliographies.get(i);
			if (!_selectedBibliographies.contains(currentDiscipline)&& !toDelete.contains(currentDiscipline)) {
				toDelete.add(currentDiscipline);
			}
		}
		return toDelete;
	}
	
	public void authorChange(){
		loadBibliographyAuthor(_author);
	}

	public void loadBibliographyAuthor(Author author) {
		_author = author;
		_editBibliographyAuthor = true;
		clearBibliographiesLists();
		
		for (int i = 0; i < _bibliographiesAuthorsList.size(); i++) {
			BibliographyAuthor current = _bibliographiesAuthorsList.get(i);
			if (current.getAuthor().getAuthorId() == _author.getAuthorId())
			{
				_currentBibliographies.add(current.getBibliography());
				_selectedBibliographies.add(current.getBibliography());
			}
		}		
	}

	public void loadAuthorBibliographiesList() {
		try {
			_bibliographiesAuthorsList = _bibliographyAuthorDelegate.list();
			_authorsList = _authorDelegate.list();			
			
			for (BibliographyAuthor bibliographyAuthor : _bibliographiesAuthorsList) {
				Author author = bibliographyAuthor.getAuthor();
				Bibliography bibliography = bibliographyAuthor.getBibliography();

				Author existentAuthor = _authorsList.get(_authorsList.indexOf(author));
				if(!existentAuthor.getBibliographies().contains(bibliography)){
					existentAuthor.getBibliographies().add(bibliography);
				}
			}
			
		} catch (BibliographyAuthorException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_BIBLIOGRAPHIES_AUTHORS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
		 catch (AuthorException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_AUTHORS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Author author, Bibliography bibliography) {
		try {
			_bibliographyAuthorDelegate.delete(author, bibliography);
			clearBibliographiesLists();
			loadAuthorBibliographiesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_BIBLIOGRAPHY_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (BibliographyAuthorException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_BIBLIOGRAPHY_AUTHOR, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace(); 
		}
	}

	public void cleanBibliographyAuthor() {
		_author = new Author();
		_editBibliographyAuthor = false;
		clearBibliographiesLists();
	}

	private void clearBibliographiesLists() {
		_currentBibliographies.clear();
		_selectedBibliographies.clear();
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

	public List<BibliographyAuthor> getBibliographiesAuthorsList() {
		return _bibliographiesAuthorsList;
	}

	public void setBibliographiesAuthorsList(
			List<BibliographyAuthor> bibliographiesAuthorsList) {
		_bibliographiesAuthorsList = bibliographiesAuthorsList;
	}

	public Boolean getEditBibliographyAuthor() {
		return _editBibliographyAuthor;
	}

	public void setEditBibliographyAuthor(Boolean editBibliographyAuthor) {
		_editBibliographyAuthor = editBibliographyAuthor;
	}

	public List<Bibliography> getSelectedBibliographies() {
		return _selectedBibliographies;
	}

	public void setSelectedBibliographies(List<Bibliography> selectedBibliographies) {
		_selectedBibliographies = selectedBibliographies;
	}

	public List<Bibliography> getCurrentBibliographies() {
		return _currentBibliographies;
	}

	public void setCurrentBibliographies(List<Bibliography> currentBibliographies) {
		_currentBibliographies = currentBibliographies;
	}
}