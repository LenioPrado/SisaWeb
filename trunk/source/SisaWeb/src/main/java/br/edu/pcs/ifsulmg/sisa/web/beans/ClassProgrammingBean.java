/*
 * File:	     ClassProgrammingBean.java
 * Creation date: 03/09/2015
 * Author:        Amanda
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

import br.edu.pcs.ifsulmg.sisa.exceptions.ClassProgrammingException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.ClassProgramming;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.ClassProgrammingDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class ClassProgrammingBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8808675061235232362L;
	
	protected ClassProgramming _classProgramming;
	
	protected List<ClassProgramming> _classProgrammingList;
	
	@Inject
	protected ClassProgrammingDelegate _classProgrammingDelegate;
	
	protected Boolean _editClassProgramming;
	
	protected boolean _teachingPlanIsVisible = true;
		
	@PostConstruct
	public void init() {
		_classProgramming = new ClassProgramming();
		loadClassProgrammingsList();
	}
	
	public void save() {
		try {
			if (_editClassProgramming) {
				_classProgrammingDelegate.update(_classProgramming);
			}
			else {
				_classProgrammingDelegate.insert(_classProgramming);
			}
			loadList();
			RequestContext.getCurrentInstance().execute("PF('saveClassProgrammingDialog').hide()");
			RequestContext.getCurrentInstance().update("formListClassProgrammings");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_CLASS_PROGRAMMING, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (ClassProgrammingException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_CLASS_PROGRAMMING, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	protected void loadList(){
		loadClassProgrammingsList();
	}

	public void loadClassProgramming(ClassProgramming classProgramming) {
		_classProgramming = classProgramming;
		_editClassProgramming = true;
	}

	public void loadClassProgrammingsList() {
		try {
			_classProgrammingList = _classProgrammingDelegate.list();
		} catch (ClassProgrammingException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_CLASS_PROGRAMMING, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(ClassProgramming classProgramming) {
		try {
			_classProgrammingDelegate.delete(classProgramming);
			loadClassProgrammingsList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_CLASS_PROGRAMMING, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (ClassProgrammingException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_CLASS_PROGRAMMING, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void cleanClassProgramming() {
		_classProgramming = new ClassProgramming();
		_editClassProgramming = false;
	}
	
	public ClassProgramming getClassProgramming() {
		return _classProgramming;
	}

	public void setClassProgramming(ClassProgramming classProgramming) {
		_classProgramming = classProgramming;
	}

	public List<ClassProgramming> getClassProgrammingsList() {
		return _classProgrammingList;
	}

	public void setClassProgrammingsList(List<ClassProgramming> classProgrammingsList) {
		_classProgrammingList = classProgrammingsList;
	}

	public ClassProgrammingDelegate getClassProgrammingDelegate() {
		return _classProgrammingDelegate;
	}

	public void setClassProgrammingDelegate(ClassProgrammingDelegate classProgrammingDelegate) {
		_classProgrammingDelegate = classProgrammingDelegate;
	}

	public Boolean getEditClassProgramming() {
		return _editClassProgramming;
	}

	public void setEditClassProgramming(Boolean editClassProgramming) {
		_editClassProgramming = editClassProgramming;
	}

	public boolean isTeachingPlanIsVisible() {
		return _teachingPlanIsVisible;
	}

	public void setTeachingPlanIsVisible(boolean teachingPlanIsVisible) {
		_teachingPlanIsVisible = teachingPlanIsVisible;
	}	
}