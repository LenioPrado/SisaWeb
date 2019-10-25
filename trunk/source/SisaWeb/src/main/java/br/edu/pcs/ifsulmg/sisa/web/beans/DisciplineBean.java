/*
 * File:	     DisciplineBean.java
 * Creation date: 06/07/2015
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

import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.DisciplineDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class DisciplineBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8808675061235232362L;
	
	private Discipline _discipline;
	
	private List<Discipline> _disciplinesList;
	
	@Inject
	private DisciplineDelegate _disciplineDelegate;
	
	private Boolean _editDiscipline;
	
	@PostConstruct
	public void init() {
		_discipline = new Discipline();
		loadDisciplinesList();
	}
	
	public void save() {
		try {
			if (_editDiscipline) {
				_disciplineDelegate.update(_discipline);
			}
			else {
				_disciplineDelegate.insert(_discipline);
			}
			loadDisciplinesList();
			RequestContext.getCurrentInstance().execute("PF('saveDisciplineDialog').hide()");
			RequestContext.getCurrentInstance().update("formListDisciplines");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (DisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadDiscipline(Discipline discipline) {
		_discipline = discipline;
		_editDiscipline = true;
	}

	public void loadDisciplinesList() {
		try {
			_disciplinesList = _disciplineDelegate.list();
		} catch (DisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_DISCIPLINES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Discipline discipline) {
		try {
			_disciplineDelegate.delete(discipline);
			loadDisciplinesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (DisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY;
			} 
			else if(constraint.contains(MsgConstants.CONSTRAINT_DISCIPLINE_PPC)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_PPC;
			}
			else if(constraint.contains(MsgConstants.CONSTRAINT_DISCIPLINE_TEACHING_PLAN)){
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_DISCIPLINE_TEACHING_PLAN;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanDiscipline() {
		_discipline = new Discipline();
		_editDiscipline = false;
	}

	public Discipline getDiscipline() {
		return _discipline;
	}

	public void setDiscipline(Discipline discipline) {
		_discipline = discipline;
	}

	public List<Discipline> getDisciplinesList() {
		return _disciplinesList;
	}

	public void setDisciplinesList(List<Discipline> disciplinesList) {
		_disciplinesList = disciplinesList;
	}

	public DisciplineDelegate getDisciplineDelegate() {
		return _disciplineDelegate;
	}

	public void setDisciplineDelegate(DisciplineDelegate disciplineDelegate) {
		_disciplineDelegate = disciplineDelegate;
	}

	public Boolean getEditDiscipline() {
		return _editDiscipline;
	}

	public void setEditDiscipline(Boolean editDiscipline) {
		_editDiscipline = editDiscipline;
	}	
}