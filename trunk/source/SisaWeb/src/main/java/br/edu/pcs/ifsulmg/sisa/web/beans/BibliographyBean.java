/*
 * File:	     BibliographyBean.java
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

import br.edu.pcs.ifsulmg.sisa.exceptions.BibliographyException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.BibliographyDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class BibliographyBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1157781973341764834L;
	
	private Bibliography _bibliography;
	
	private List<Bibliography> _bibliographiesList;
	
	@Inject
	private BibliographyDelegate _bibliographyDelegate;
	
	private Boolean _editBibliography;
		
	@PostConstruct
	public void init() {
		_bibliography = new Bibliography();
		loadBibliographiesList();
	}
	
	public void save() {
		try {
			if (_editBibliography) {
				_bibliographyDelegate.update(_bibliography);
			}
			else {
				_bibliographyDelegate.insert(_bibliography);
			}
			loadBibliographiesList();
			RequestContext.getCurrentInstance().execute("PF('saveBibliographyDialog').hide()");
			RequestContext.getCurrentInstance().update("formListBibliographies");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (BibliographyException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadBibliography(Bibliography bibliography) {
		_bibliography = bibliography;
		_editBibliography = true;
	}

	public void loadBibliographiesList() {
		try {
			_bibliographiesList = _bibliographyDelegate.list();
		} catch (BibliographyException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_BIBLIOGRAPHIES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Bibliography bibliography) {
		try {
			_bibliographyDelegate.delete(bibliography);
			loadBibliographiesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (BibliographyException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}  catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_BIBLIOGRAPHY_DISCIPLINE;
			} else if(constraint.contains(MsgConstants.CONSTRAINT_BIBLIOGRAPHY_AUTHOR)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_BIBLIOGRAPHY_AUTHOR;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanBibliography() {
		_bibliography = new Bibliography();
		_editBibliography = false;
	}

	public Bibliography getBibliography() {
		return _bibliography;
	}

	public void setBibliography(Bibliography bibliography) {
		_bibliography = bibliography;
	}

	public List<Bibliography> getBibliographiesList() {
		return _bibliographiesList;
	}

	public void setBibliographysList(List<Bibliography> bibliographiesList) {
		_bibliographiesList = bibliographiesList;
	}

	public BibliographyDelegate getBibliographyDelegate() {
		return _bibliographyDelegate;
	}

	public void setBibliographyDelegate(BibliographyDelegate bibliographyDelegate) {
		_bibliographyDelegate = bibliographyDelegate;
	}

	public Boolean getEditBibliography() {
		return _editBibliography;
	}

	public void setEditBibliography(Boolean editBibliography) {
		_editBibliography = editBibliography;
	}
}