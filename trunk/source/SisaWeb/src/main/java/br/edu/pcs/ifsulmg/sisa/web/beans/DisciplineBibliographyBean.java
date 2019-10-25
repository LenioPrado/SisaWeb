/*
 * File:	     DisciplineBibliographyBean.java
 * Creation date: 09/09/2015
 * Author:        Amanda
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
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.edu.pcs.ifsulmg.sisa.exceptions.BibliographyException;
import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineBibliographyException;
import br.edu.pcs.ifsulmg.sisa.exceptions.DisciplineException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Bibliography;
import br.edu.pcs.ifsulmg.sisa.vo.Discipline;
import br.edu.pcs.ifsulmg.sisa.vo.DisciplineBibliography;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.BibliographyDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.DisciplineBibliographyDelegate;
import br.edu.pcs.ifsulmg.sisa.web.delegate.DisciplineDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class DisciplineBibliographyBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8808675061235232362L;
	
	@Inject
	private DisciplineBibliographyDelegate _disciplineBibliographyDelegate;
	
	@Inject
	private DisciplineDelegate _disciplineDelegate;
	
	@Inject
	private BibliographyDelegate _bibliographyDelegate;
	
	private Discipline _discipline;
	
	private List<Discipline> _disciplinesList;
	
	private List<Bibliography> _bibliographiesList;
	
	private List<DisciplineBibliography> _disciplinesBibliographiesList;	

	private Boolean _editDisciplineBibliography;
	
	private List<Bibliography> _currentBibliographies;
	
	private List<Bibliography> _sourceBibliographies;
	
	private List<Bibliography> _targetBibliographies;
		
	private DualListModel<Bibliography> _dualListBibliographies;
	
	private boolean _isBasic = true;
	
	@PostConstruct
	public void init() {
		_discipline = new Discipline();
		_currentBibliographies = new ArrayList<Bibliography>();
		_sourceBibliographies = new ArrayList<Bibliography>();
		_targetBibliographies = new ArrayList<Bibliography>();
		
		_dualListBibliographies = new DualListModel<Bibliography>(_sourceBibliographies, _targetBibliographies);
		
		loadBibliographiesList();
		loadDisciplineBibliographiesList();
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
	
	public void loadDisciplineBibliographiesList() {
		try {
			_disciplinesBibliographiesList = _disciplineBibliographyDelegate.list();
			_disciplinesList = _disciplineDelegate.list();			
			
			for (DisciplineBibliography disciplineBibliography : _disciplinesBibliographiesList) {
				Discipline discipline = disciplineBibliography.getDiscipline();
				Bibliography bibliography = disciplineBibliography.getBibliography();
				bibliography.setBasicView(disciplineBibliography.isBasic());
				
				Discipline existentDiscipline = _disciplinesList.get(_disciplinesList.indexOf(discipline));
				if(!existentDiscipline.getAllBibliographies().contains(bibliography)){
					existentDiscipline.getAllBibliographies().add(bibliography);
				}
			}
			
		} catch (DisciplineBibliographyException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_DISCIPLINE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
		catch (DisciplineException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_DISCIPLINES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Discipline discipline, Bibliography bibliography) {
		try {
			_disciplineBibliographyDelegate.delete(discipline, bibliography);
			clearBibliographiesLists();
			loadDisciplineBibliographiesList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_DISCIPLINE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (DisciplineBibliographyException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_DISCIPLINE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace(); 
		}
	}
	
	public void save() {
		try {			
			if (_editDisciplineBibliography)
				_disciplineBibliographyDelegate.delete(_discipline, getBibliographiesToDelete());

			_disciplineBibliographyDelegate.insert(_discipline, getBibliographiesToSave());

			loadDisciplineBibliographiesList();
			RequestContext.getCurrentInstance().execute("PF('saveDisciplineBibliographyDialog').hide()");
			RequestContext.getCurrentInstance().update("formListDisciplineBibliographies");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_DISCIPLINE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (DisciplineBibliographyException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_DISCIPLINE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} 
		catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_CONSTRAINT_DISCIPLINE_BIBLIOGRAPHY, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private List<Bibliography> getBibliographiesToSave(){
		List<Bibliography> toSave = new ArrayList<Bibliography>();
		
		for (int i = 0; i < _dualListBibliographies.getTarget().size(); i++) {
			Bibliography selectedBibliography = _dualListBibliographies.getTarget().get(i);
			if (!_currentBibliographies.contains(selectedBibliography) && !toSave.contains(selectedBibliography)) {
				toSave.add(selectedBibliography);
			}			
		}
		return toSave;
	}
	
	private List<Bibliography> getBibliographiesToDelete(){
		List<Bibliography> toDelete = new ArrayList<Bibliography>();
		
		for (int i = 0; i < _currentBibliographies.size(); i++) {
			Bibliography currentBibliography = _currentBibliographies.get(i);
			if (!_dualListBibliographies.getTarget().contains(currentBibliography)&& !toDelete.contains(currentBibliography)) {
				toDelete.add(currentBibliography);
			}
		}
		return toDelete;
	}
	
	public void disciplineChange(){
		loadDisciplineBibliography(_discipline);
	}

	public void loadDisciplineBibliography(Discipline discipline) {
		_discipline = discipline;
		_editDisciplineBibliography = true;
		clearBibliographiesLists();
		
		for (Bibliography bibliography : _bibliographiesList) {
			bibliography.setShowTitleFormatted(false);
			_dualListBibliographies.getSource().add(bibliography);
		}
		
		for (int i = 0; i < _disciplinesBibliographiesList.size(); i++) {
			DisciplineBibliography current = _disciplinesBibliographiesList.get(i);
			if (current.getDiscipline().getDisciplineId() == _discipline.getDisciplineId())
			{
				current.getBibliography().setShowTitleFormatted(true);
				_currentBibliographies.add(current.getBibliography());				
				_dualListBibliographies.getTarget().add(current.getBibliography());
				_dualListBibliographies.getSource().remove(current.getBibliography());
			}
		}
		RequestContext.getCurrentInstance().update("bibliographyPickList");
	}	

	public void cleanDisciplineBibliography() {
		_discipline = new Discipline();
		_editDisciplineBibliography = false;
		clearBibliographiesLists();
	}

	private void clearBibliographiesLists() {
		_currentBibliographies.clear();
		_dualListBibliographies.getSource().clear();
		_dualListBibliographies.getTarget().clear();
	}
	
	public void onTransfer(TransferEvent event) {
        for(Object item : event.getItems()) {
        	Bibliography bibliography = (Bibliography)item;
        	bibliography.setShowTitleFormatted(event.isAdd());
        	bibliography.setBasicView(_isBasic);
//        	if (event.isAdd()) {
//        		_dualListBibliographies.getTarget().add(bibliography);	
//			} else {				
//				_dualListBibliographies.getTarget().remove(bibliography);
//			}        	
        }
    } 
	
	public void basicChanged(){
		System.out.println("Basic:" + _isBasic);
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

	public List<DisciplineBibliography> getDisciplinesBibliographiesList() {
		return _disciplinesBibliographiesList;
	}

	public void setDisciplinesBibliographiesList(
			List<DisciplineBibliography> disciplinesBibliographiesList) {
		_disciplinesBibliographiesList = disciplinesBibliographiesList;
	}

	public Boolean getEditDisciplineBibliography() {
		return _editDisciplineBibliography;
	}

	public void setEditDisciplineBibliography(Boolean editDisciplineBibliography) {
		_editDisciplineBibliography = editDisciplineBibliography;
	}

	public DualListModel<Bibliography> getDualListBibliographies() {
		return _dualListBibliographies;
	}

	public void setDualListBibliographies(DualListModel<Bibliography> dualListBibliographies) {
		_dualListBibliographies = dualListBibliographies;
	}

	public List<Bibliography> getCurrentBibliographies() {
		return _currentBibliographies;
	}

	public void setCurrentBibliographies(List<Bibliography> currentBibliographies) {
		_currentBibliographies = currentBibliographies;
	}

	public List<Bibliography> getBibliographiesList() {
		return _bibliographiesList;
	}

	public void setBibliographiesList(List<Bibliography> bibliographiesList) {
		_bibliographiesList = bibliographiesList;
	}

	public boolean isBasic() {
		return _isBasic;
	}

	public void setBasic(boolean isBasic) {
		_isBasic = isBasic;
	}
}