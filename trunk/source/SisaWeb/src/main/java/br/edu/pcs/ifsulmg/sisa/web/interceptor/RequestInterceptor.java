/*
* File:	     RequestInterceptor.java
* Creation date: 04/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.interceptor;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.web.constants.WebConstants;
import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.UserAccessUtils;

/**
 * The Class RequestInterceptor.
 */
public class RequestInterceptor implements PhaseListener  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8126958826581147161L;
	
	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent phase) {

	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	@SuppressWarnings("static-access")
	@Override
	public void beforePhase(PhaseEvent phase) {		
		UIViewRoot viewRoot = phase.getFacesContext().getCurrentInstance().getViewRoot();
		
		if (viewRoot != null) {
			String viewRootId = viewRoot.getViewId();
			if (!viewRootId.equals(WebConstants.PAGE_RECOVERY_PASSWORD) &&				
				!viewRootId.equals(WebConstants.PAGE_LOGIN)&&				
				!viewRootId.equals(WebConstants.PAGE_SELECT_ROLE)&&				
				!viewRootId.equals(WebConstants.PAGE_404)&&				
				!viewRootId.equals(WebConstants.PAGE_500)) {
				User user = getUserParam(phase);
				if (user == null) {
					getCurrentInstance(phase).getApplication().getNavigationHandler()
						.handleNavigation(FacesContext.getCurrentInstance(), "", WebConstants.PAGE_500);
					System.out.println(MessagesUtil.getValue("lbl_user_not_connected"));
					return;
				} else if(!userHasAccess(viewRootId, user, getRoleParam(phase))){					
					System.out.println(MessagesUtil.getValue("lbl_user_has_not_access"));
					redirect(phase, WebConstants.PAGE_404);
				} else {				
					UserAccessUtils.getInstance().setCurrentPageInSession(viewRootId);	
				}				
			}
		}
	}
	
	private boolean userHasAccess(String viewRootId, User user, Role role){		
		return UserAccessUtils.getInstance().hasPageListAccess(viewRootId);
	}
	
	private void redirect(PhaseEvent phase, String page){
		phase.getFacesContext().getApplication().getNavigationHandler()
			.handleNavigation(phase.getFacesContext(), null, page);
	}
		
	private User getUserParam(PhaseEvent phase){
		return (User) getCurrentInstance(phase).getExternalContext().getSessionMap().
				get(WebConstants.PARAM_USER);
	}
	
	private Role getRoleParam(PhaseEvent phase){
		return (Role) getCurrentInstance(phase).getExternalContext().getSessionMap().
				get(WebConstants.PARAM_ROLE);
	}
	
	@SuppressWarnings("static-access")
	private FacesContext getCurrentInstance(PhaseEvent phase){
		return phase.getFacesContext().getCurrentInstance();
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
