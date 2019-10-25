/*
 * File:	     TraineeshipBean.java
 * Creation date: 02/07/2015
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

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.TraineeshipException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.TraineeshipDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class TraineeshipBean extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9022904004000931176L;
	
	private Traineeship _traineeship;
	
	private List<Traineeship> _traineeshipsList;
	
	@Inject
	private TraineeshipDelegate _traineeshipDelegate;
	
	private Boolean _editTraineeship;
	
	@PostConstruct
	public void init() {
		_traineeship = new Traineeship();
		loadTraineeshipsList();
	}
	
	public void save() {
		try {
			if (_editTraineeship) {
				_traineeshipDelegate.update(_traineeship);
			}
			else {
				_traineeshipDelegate.insert(_traineeship);
			}
			loadTraineeshipsList();
			RequestContext.getCurrentInstance().execute("PF('saveTraineeshipDialog').hide()");
			RequestContext.getCurrentInstance().update("formListTraineeships");	
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_TRAINEESHIP, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);

		} catch (TraineeshipException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_TRAINEESHIP, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadTraineeship(Traineeship traineeship) {
		_traineeship = traineeship;
		_editTraineeship = true;
	}

	public void loadTraineeshipsList() {
		try {
			_traineeshipsList = _traineeshipDelegate.list();
		} catch (TraineeshipException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_TRAINEESHIPS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}

	public void delete(Traineeship traineeship) {
		try {
			_traineeshipDelegate.delete(traineeship);
			loadTraineeshipsList();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_TRAINEESHIP, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (TraineeshipException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_TRAINEESHIP, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_TRAINEESHIP_PPC)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_TRAINEESHIP_PPC;
			}
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}


	public void cleanTraineeship() {
		_traineeship = new Traineeship();
		_editTraineeship = false;
	}

	public Traineeship getTraineeship() {
		return _traineeship;
	}

	public void setTraineeship(Traineeship traineeship) {
		_traineeship = traineeship;
	}

	public List<Traineeship> getTraineeshipsList() {
		return _traineeshipsList;
	}

	public void setTraineeshipsList(List<Traineeship> traineeshipsList) {
		_traineeshipsList = traineeshipsList;
	}

	public TraineeshipDelegate getTraineeshipDelegate() {
		return _traineeshipDelegate;
	}

	public void setTraineeshipDelegate(TraineeshipDelegate traineeshipDelegate) {
		_traineeshipDelegate = traineeshipDelegate;
	}

	public Boolean getEditTraineeship() {
		return _editTraineeship;
	}

	public void setEditTraineeship(Boolean editTraineeship) {
		_editTraineeship = editTraineeship;
	}
}