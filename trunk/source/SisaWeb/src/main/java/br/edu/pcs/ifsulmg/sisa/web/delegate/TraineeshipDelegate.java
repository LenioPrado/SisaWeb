/*
 * File:	     TraineeshipDelegate.java
 * Creation date: 02/07/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.TraineeshipException;
import br.edu.pcs.ifsulmg.sisa.vo.Traineeship;

public class TraineeshipDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6426420529250914528L;

	public int insert(Traineeship traineeship) throws TraineeshipException, EntityConstraintViolationException {
		int id = getTraineeshipDAO().insert(traineeship);
		return id;
	}

	public void update(Traineeship traineeship) throws TraineeshipException, EntityConstraintViolationException {
		getTraineeshipDAO().update(traineeship);
	}

	public List<Traineeship> list() throws TraineeshipException {
		List<Traineeship> list = getTraineeshipDAO().list();
		return list;
	}

	public void delete(Traineeship traineeship) throws TraineeshipException, EntityConstraintViolationException {
		getTraineeshipDAO().delete(traineeship.getTraineeshipId());
	}
}