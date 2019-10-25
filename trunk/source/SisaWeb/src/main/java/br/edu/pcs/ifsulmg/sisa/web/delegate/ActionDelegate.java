package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.ActionException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.vo.Action;

public class ActionDelegate extends CommonDelegate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975713497654229821L;

	public int insert(Action action) throws ActionException, EntityConstraintViolationException {
		int id = getActionDAO().insert(action);
		return id;
	}

	public void update(Action action) throws ActionException, EntityConstraintViolationException {
		getActionDAO().update(action);
	}

	public List<Action> list() throws ActionException {
		List<Action> list = getActionDAO().list();
		return list;
	}

	public void delete(Action action) throws ActionException, EntityConstraintViolationException {
		getActionDAO().delete(action.getActionId());
	}
}
