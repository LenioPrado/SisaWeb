/*
 * File:	     ClassProgrammingTeachingPlanBean.java
 * Creation date: 18/10/2016
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.pcs.ifsulmg.sisa.exceptions.ClassProgrammingException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.ClassProgramming;
import br.edu.pcs.ifsulmg.sisa.vo.TeachingPlan;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.constants.WebConstants;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class ClassProgrammingTeachingPlanBean extends ClassProgrammingBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -925433977228414489L;
	
	TeachingPlan selectedTeachingPlan;
		
	@PostConstruct
	public void init() {
		defineTeachingPlanFromSession();
		if (selectedTeachingPlan != null) {
			listClassProgrammingsByTeachingPlan();
			_classProgramming = new ClassProgramming();
		}		
	}
	
	private void defineTeachingPlanFromSession(){
		selectedTeachingPlan = (TeachingPlan)BeanUtil.getParamInSession("teachingPlan");				
		_teachingPlanIsVisible = false;
	}
	
	private void listClassProgrammingsByTeachingPlan() {
		try {
			int teachingPlanId = selectedTeachingPlan.getTeachingPlanId();
			_classProgrammingList = _classProgrammingDelegate.listByTeachingPlan(teachingPlanId);
		} catch (ClassProgrammingException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_CLASS_PROGRAMMING, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	public void save(){
		_classProgramming.setTeachingPlan(selectedTeachingPlan);
		super.save();
	}
	
	@Override
	protected void loadList(){
		listClassProgrammingsByTeachingPlan();
	}
	
	public String backToTeachingPlanFill(){
		return WebConstants.PAGE_FILL_TEACHING_PLAN;
	}
}