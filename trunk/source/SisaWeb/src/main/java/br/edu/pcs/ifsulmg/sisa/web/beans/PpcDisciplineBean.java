/*
 * File:	     PpcDisciplineBean.java
 * Creation date: 20/08/2015
 * PpcDiscipline:       PagodeiroDoMal
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

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PpcDisciplineException;
import br.edu.pcs.ifsulmg.sisa.exceptions.PpcException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.Ppc;
import br.edu.pcs.ifsulmg.sisa.vo.PpcDiscipline;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PpcDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.PpcDisciplineDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class PpcDisciplineBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9192734798825886839L;
	
	@Inject
	private PpcDisciplineDelegate _ppcDisciplineDelegate;
	
	@Inject
	private PpcDelegate _ppcDelegate;
	
	private Ppc _ppc;
	
	private List<Ppc> _ppcs;
	
	private List<PpcDiscipline> _ppcDisciplinesList;	

	private Boolean _editPpcDiscipline;
	
	private List<Discipline> _selectedDisciplines;
	
	private List<Discipline> _currentDisciplines;
	
	@PostConstruct
	public void init() {
		_ppc = new Ppc();		
		_selectedDisciplines = new ArrayList<Discipline>();
		_currentDisciplines = new ArrayList<Discipline>();
		loadPpcDisciplinesList();
	}
	
	public void save() {
		try {			
			if (_editPpcDiscipline)
				_ppcDisciplineDelegate.delete(getPpcsDisciplinesToDelete());

			_ppcDisciplineDelegate.insert(getPpcsDisciplinesToSave());

			loadPpcDisciplinesList();
			RequestContext.getCurrentInstance().execute("PF('savePpcDisciplineDialog').hide()");
			RequestContext.getCurrentInstance().update("formListPpcDisciplines");	
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_PPC_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (PpcDisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_PPC_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_PPC_DISCIPLINE_TEACHING_PLAN)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PPC_DISCIPLINE_TEACHING_PLAN;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private List<PpcDiscipline> getPpcsDisciplinesToSave(){
		List<PpcDiscipline> toSave = new ArrayList<PpcDiscipline>();
		
		for (int i = 0; i < _selectedDisciplines.size(); i++) {
			Discipline selectedDiscipline = _selectedDisciplines.get(i);
			PpcDiscipline currentPpcDiscipline = getPpcDiscipline(selectedDiscipline, _ppc);
			if (!_currentDisciplines.contains(selectedDiscipline) && !toSave.contains(currentPpcDiscipline)) {
				toSave.add(currentPpcDiscipline);
			}
		}
		return toSave;
	}
	
	private List<PpcDiscipline> getPpcsDisciplinesToDelete(){
		List<PpcDiscipline> toDelete = new ArrayList<PpcDiscipline>();
		
		for (int i = 0; i < _currentDisciplines.size(); i++) {
			Discipline currentDiscipline = _currentDisciplines.get(i);
			PpcDiscipline currentPpcDiscipline = getPpcDiscipline(currentDiscipline, _ppc);
			if (!_selectedDisciplines.contains(currentDiscipline)&& !toDelete.contains(currentPpcDiscipline)) {
				toDelete.add(currentPpcDiscipline);
			}
		}
		return toDelete;
	}
	
	private PpcDiscipline getPpcDiscipline(Discipline discipline, Ppc ppc){
		PpcDiscipline ppcDiscipline = new PpcDiscipline(discipline, ppc);
		int index = _ppcDisciplinesList.indexOf(ppcDiscipline);		
		return index >= 0 ? _ppcDisciplinesList.get(index) : ppcDiscipline;
	}
	
	public void ppcChange(){
		loadPpcDiscipline(_ppc);
	}

	public void loadPpcDiscipline(Ppc ppc) {
		_ppc = ppc;
		_editPpcDiscipline = true;
		clearDisciplinesLists();
		
		for (int i = 0; i < _ppcDisciplinesList.size(); i++) {
			PpcDiscipline current = _ppcDisciplinesList.get(i);
			if (current.getPpc().getPpcId() == _ppc.getPpcId())
			{
				_currentDisciplines.add(current.getDiscipline());
				_selectedDisciplines.add(current.getDiscipline());
			}
		}		
	}

	public void loadPpcDisciplinesList() {
		try {
			_ppcDisciplinesList = _ppcDisciplineDelegate.list();
			_ppcs = _ppcDelegate.list();			
			
			for (PpcDiscipline ppcDiscipline : _ppcDisciplinesList) {
				Ppc ppc = ppcDiscipline.getPpc();
				Discipline discipline = ppcDiscipline.getDiscipline();

				Ppc existentPpc = _ppcs.get(_ppcs.indexOf(ppc));
				if(!existentPpc.getDisciplines().contains(discipline)){
					existentPpc.getDisciplines().add(discipline);
				}
			}
			
		} catch (PpcDisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PPC_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
		catch (PpcException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_PPCS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Ppc ppc, Discipline discipline) {
		try {
			_ppcDisciplineDelegate.delete(getPpcDiscipline(discipline, ppc));
			clearDisciplinesLists();
			loadPpcDisciplinesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_PPC_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (PpcDisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_PPC_DISCIPLINE, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace(); 
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_PPC_DISCIPLINE_TEACHING_PLAN)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_PPC_DISCIPLINE_TEACHING_PLAN;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private void clearDisciplinesLists() {
		_currentDisciplines.clear();
		_selectedDisciplines.clear();
	}

	public Ppc getPpc() {
		return _ppc;
	}

	public void setPpc(Ppc ppc) {
		_ppc = ppc;
	}

	public List<PpcDiscipline> getPpcDisciplinesList() {
		return _ppcDisciplinesList;
	}

	public void setPpcDisciplinesList(List<PpcDiscipline> ppcDisciplineList) {
		_ppcDisciplinesList = ppcDisciplineList;
	}

	public Boolean getEditPpcDiscipline() {
		return _editPpcDiscipline;
	}

	public void setEditPpcDiscipline(Boolean editPpcDiscipline) {
		_editPpcDiscipline = editPpcDiscipline;
	}

	public List<Ppc> getPpcs() {
		return _ppcs;
	}

	public void setPpcs(List<Ppc> ppcs) {
		_ppcs = ppcs;
	}

	public List<Discipline> getSelectedDisciplines() {
		return _selectedDisciplines;
	}

	public void setSelectedDisciplines(List<Discipline> selectedDisciplines) {
		_selectedDisciplines = selectedDisciplines;
	}

	public List<Discipline> getCurrentDisciplines() {
		return _currentDisciplines;
	}

	public void setCurrentDisciplines(List<Discipline> currentDisciplines) {
		_currentDisciplines = currentDisciplines;
	}	
}