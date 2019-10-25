/*
 * File:	     EvaluationBean.java
 * Creation date: 09/07/2015
 * Evaluation:        Amanda
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
import br.edu.pcs.ifsulmg.sisa.exceptions.EvaluationException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Evaluation;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.EvaluationDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;

@Named
@ViewScoped
public class EvaluationBean extends CommonBean implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -5451465246214814191L;
		
		private Evaluation _evaluation;
		
		private List<Evaluation> _evaluationsList;
		
		@Inject
		private EvaluationDelegate _evaluationDelegate;
		
		private Boolean _editEvaluation;
		
		@PostConstruct
		public void init() {
			_evaluation = new Evaluation();
			loadEvaluationsList();
		}
		
		public void save() {
			try {
				if (_editEvaluation) {
					_evaluationDelegate.update(_evaluation);
				}
				else {
					_evaluationDelegate.insert(_evaluation);
				}
				loadEvaluationsList();
				RequestContext.getCurrentInstance().execute("PF('saveEvaluationDialog').hide()");
				RequestContext.getCurrentInstance().update("formListEvaluations");	
				BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
			} catch (EvaluationException e) {
				BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
				LogSISA.log.error(e.getMessage());
				e.printStackTrace();
			} catch (EntityConstraintViolationException e) {
				BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
				LogSISA.log.error(e.getMessage());
				e.printStackTrace();
			}
		}

		public void loadEvaluation(Evaluation evaluation) {
			_evaluation = evaluation;
			_editEvaluation = true;
		}

		public void loadEvaluationsList() {
			try {
				_evaluationsList = _evaluationDelegate.list();
			} catch (EvaluationException e) {
				BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_EVALUATIONS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
				LogSISA.log.error(e.getMessage());
				e.printStackTrace();			
			}
		}

		public void delete(Evaluation evaluation) {
			try {
				_evaluationDelegate.delete(evaluation);
				loadEvaluationsList();
				BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
			} catch (EvaluationException e) {
				BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_EVALUATION, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
				LogSISA.log.error(e.getMessage());
				e.printStackTrace();
			} catch (EntityConstraintViolationException e) {
				String message = e.getMessage();
				String constraint = e.getCause().getMessage().toLowerCase();
				if (constraint.contains(MsgConstants.CONSTRAINT_EVALUATION_PROCEDURE_EVALUATION)) {
					message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_EVALUATION_PROCEDURE_EVALUATION;
				} 
				BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
				LogSISA.log.error(e.getMessage());
				e.printStackTrace();
			}
		}

		public void cleanEvaluation() {
			_evaluation = new Evaluation();
			_editEvaluation = false;
		}

		public Evaluation getEvaluation() {
			return _evaluation;
		}

		public void setEvaluation(Evaluation evaluation) {
			_evaluation = evaluation;
		}

		public List<Evaluation> getEvaluationsList() {
			return _evaluationsList;
		}

		public void setEvaluationsList(List<Evaluation> evaluationsList) {
			_evaluationsList = evaluationsList;
		}

		public EvaluationDelegate getEvaluationDelegate() {
			return _evaluationDelegate;
		}

		public void setEvaluationDelegate(EvaluationDelegate evaluationDelegate) {
			_evaluationDelegate = evaluationDelegate;
		}

		public Boolean getEditEvaluation() {
			return _editEvaluation;
		}

		public void setEditEvaluation(Boolean editEvaluation) {
			_editEvaluation = editEvaluation;
		}	
	}