/*
* File:	     BeanUtil.java
* Creation date: 04/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.util.PropsUtil;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.constants.WebConstants;

/**
 * The Class BeanUtil.
 */
public class BeanUtil {

	/** The Constant PANEL. */
	public static final String ID_MSG_USER = "msgUser";
	
	public static final String ID_MSG = "msgGeral";
	
	public static final String ID_MSG_PASSWORD = "msgPwd";
	
	/** The Constant DAYS_TO_SUBTRACT_FROM_TODAY. */
	private static final int DAYS_TO_SUBTRACT_FROM_TODAY = 2;
	
	/** The Constant DAY_LAST_HOUR. */
	private static final int DAY_LAST_HOUR = 23;
	
	/** The Constant DAY_LAST_MINUTE. */
	private static final int DAY_LAST_MINUTE = 59;
	
	/** The Constant DAY_LAST_SECOND. */
	private static final int DAY_LAST_SECOND = 59;
	
	/** The Constant DAY_LAST_MILLISECOND. */
	private static final int DAY_LAST_MILLISECOND = 999;

	/**
	 * Instantiates a new bean util.
	 */
	private BeanUtil() {	}
	
	/**
	 * Sets the message.
	 *
	 * @param e the e
	 * @param msg the msg
	 * @param id the id
	 * @param severity the severity
	 */
	public static void setMessage(ActionEvent e, String msg, String id, Severity severity) {
		String clientId = e.getComponent().getClientId(FacesContext.getCurrentInstance());
		clientId = clientId.substring(0, clientId.lastIndexOf(":") + 1);
		clientId += id;
		FacesMessage message = new FacesMessage();
		message.setDetail(msg);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(clientId, message);
	}
	
	/**
	 * Sets the message general.
	 *
	 * @param msg the msg
	 * @param id the id
	 * @param severity the severity
	 */
	public static void setMessageGeneral(String msg, String id, Severity severity) {
		FacesMessage message = getMessage(msg, id, severity);
		FacesContext.getCurrentInstance().addMessage(id, message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	/**
	 * Gets the message.
	 *
	 * @param msg the msg
	 * @param id the id
	 * @param severity the severity
	 * @return the message
	 */
	public static FacesMessage getMessage(String msg, String id, Severity severity){
		FacesMessage message = new FacesMessage();
		message.setDetail(msg);
		message.setSeverity(severity);
		return message;
	}

	/**
	 * Invalidate session.
	 */
	public static void invalidateSession() {
		HttpSession sessao = getSessionInFacesCtxt();
		sessao.invalidate();
	}

	/**
	 * Gets the session in faces ctxt.
	 *
	 * @return the session in faces ctxt
	 */
	private static HttpSession getSessionInFacesCtxt() {
		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	/**
	@SuppressWarnings({"unchecked", "el-syntax"})
	public static <T> T findBean(String beanName) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	}
	*/
	
	public static Object getManagedBean(String bean) {
		  
		  FacesContext facesContext = FacesContext.getCurrentInstance();
		  ELContext elContext = facesContext.getELContext();
		  ExpressionFactory factory = facesContext.getApplication().getExpressionFactory();
		  
		  return factory.createValueExpression(elContext, bean, Object.class).getValue(elContext);
	}
	
	public static String getImageUrl(String image) {
		String host = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
        int port = FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();
        String projectName = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath(); 
        String imagePath = "http://" + host + ":" + port + projectName + "/images/" + image;
        imagePath = "http://localhost:8080" + projectName + "/images/" + image;
        
        //TODO: Alterar o caminho para recuperar dinamicamente.
        LogSISA.log.info(imagePath); 
        
        return imagePath;
	}
	
	/**
	 * Removes the from session.
	 *
	 * @param key the key
	 */
	public static void removeFromSession(String key) {
		HttpSession sessao = getSessionInFacesCtxt();
		sessao.removeAttribute(key);
	}
	
	
	/**
	 * Put session.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public static void putSession(String key, Object value) {
		HttpSession sessao = getSessionInFacesCtxt();
		sessao.setAttribute(key, value);
	}
	

	/**
	 * Gets the param in session.
	 *
	 * @param key the key
	 * @return the param in session
	 */
	public static Object getParamInSession(String key) {
		HttpSession sessao = getSessionInFacesCtxt();
		return sessao.getAttribute(key);
	}
	
	/**
	 * Forward success.
	 *
	 * @param msg the msg
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void forwardSuccess(String msg) throws IOException {
		setMessageGeneral(msg, null, FacesMessage.SEVERITY_INFO);
		redirectTo(WebConstants.PAGE_SUCCESS);
	}
	
	/**
	 * Redirect to.
	 *
	 * @param page the page
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void redirectTo(String page) throws IOException{
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), "", page);
	}

	/**
	 * Gets the full url.
	 *
	 * @return the full url
	 */
	public static String getFullUrl() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		String url = request.getRequestURL().toString();
		return url;
	}


	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public static String getVersion() {
		Properties p = PropsUtil.getInstance();
		String version = p.getProperty(MsgConstants.KEY_VERSION);
		return version;
	}
	
	/**
	 * Gets the start filter date.
	 *
	 * @return the start filter date
	 */
	public static Date getStartFilterDate() {
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DAY_OF_MONTH, -DAYS_TO_SUBTRACT_FROM_TODAY);
		
		return calendar.getTime();
	}	

	/**
	 * Gets the end filter date.
	 *
	 * @return the end filter date
	 */
	public static Date getEndFilterDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * Format end date.
	 *
	 * @param endDateTime the end date time
	 * @return the date
	 */
	public static Date formatEndDate(Date endDateTime){
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(endDateTime);
		calendar.set(Calendar.HOUR_OF_DAY, DAY_LAST_HOUR);
		calendar.set(Calendar.MINUTE, DAY_LAST_MINUTE);
		calendar.set(Calendar.SECOND, DAY_LAST_SECOND);
		calendar.set(Calendar.MILLISECOND, DAY_LAST_MILLISECOND);
		
		return calendar.getTime();	
	}
}