/*
 * File:	     UserDelegate.java
 * Creation date: 04/06/2015
 * Author:        Lênio
 */
package br.edu.pcs.ifsulmg.sisa.web.delegate;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.mail.MessagingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

import br.edu.pcs.ifsulmg.sisa.exceptions.ConnectionFactoryException;
import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.UserException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.util.PasswordEncryptUtil;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.util.EmailUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.LdapLogin;
import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.PasswordUtil;

public class UserDelegate extends CommonDelegate implements Serializable {

	private static final long serialVersionUID = -5136675708671581860L;

	public User login(String email, String password) throws UserException {
		User user = getUserByEmail(email);
		
		if(LdapLogin.useLdapAuthentication()){
			
			if(LdapLogin.useLdapSSL()) {
				user = authenticateWithLdapUsingSSL(user, email, password);
			} else {
				user = authenticateWithLdap(user, email, password);
			}
			
		} else if (user != null){
			user = authenticateWithDatabase(user, password);
		}

		return user;
	}
	
	private User authenticateWithLdapUsingSSL(User user, String email, String password) {
		
		LdapLogin.trustSelfSignedSSL();
		return authenticateWithLdap(user, email, password);	
	}
	
	private User authenticateWithLdap(User user, String email, String password){
		DirContext context = LdapLogin.authenticateWithLdap(email, password);
		if(context == null){
			return null;
		} else if (user != null) {
			return user;
		} else {
			String userName = LdapLogin.extractUserNameFromEmail(email);					
			Attributes attr = LdapLogin.getUserAccountAttributes(email, context);
			
			if (attr != null) {
				email = attr.get("mail").toString();
				if (email.contains("mail:")) {
					email = email.replace("mail:", "").trim();
				}
				userName = attr.get("cn").toString();
				if (userName.contains(":")) {
					userName = userName.replace("cn:", "").trim();					
				}				
			}
			User newUser = new User(0, userName, "sisa", email);
			newUser.setViewPassword("sisa");
			try {
				insert(newUser);
				return newUser;
			} catch (Exception e) {
				LogSISA.log.error(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
	}
	
	private User authenticateWithDatabase(User user, String password){
		try{
			if(user == null || !PasswordEncryptUtil.passwordMatchTest(password, user.getPassword())){
				return null;			
			}
		} catch (NoSuchAlgorithmException e) {
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} 
		
		return user;
	}

	public void insert(User vo) throws UserException, EntityConstraintViolationException {
		try {
			vo.setPassword(PasswordEncryptUtil.encriptyPassword(vo.getViewPassword()));
		} catch (NoSuchAlgorithmException e) {
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
		getUserDAO().insert(vo);
	}

	public void update(User user) throws UserException, EntityConstraintViolationException {
		try {
			if (!user.getViewPassword().isEmpty() &&			
				PasswordEncryptUtil.passwordMatchTest(user.getCurrentPassword(), user.getPassword())){
				user.setPassword(PasswordEncryptUtil.encriptyPassword(user.getViewPassword()));
				user.setCurrentPassword("");
				user.setViewPassword("");
			} else if(user.getCurrentPassword().isEmpty() || 
					!PasswordEncryptUtil.passwordMatchTest(user.getCurrentPassword(), user.getPassword())) {
				throw new UserException(MsgConstants.MSG_CURRENT_PASSWORD_NOT_MATCH);
			}			
		} catch (NoSuchAlgorithmException e) {
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} 
		getUserDAO().update(user);
	}

	public void updatePassword(String email, String currentPassword,
			String newPassword) throws UserException {
		User user = getUserDAO().getByEmail(email);
		try {
			if (user != null
					&& PasswordEncryptUtil.passwordMatchTest(currentPassword, user.getPassword())) {
				user.setPassword(PasswordEncryptUtil.encriptyPassword(newPassword));
				getUserDAO().updateUserPassword(user);
			} else {
				throw new UserException(MessagesUtil.getValue("msg_update_password_fail"));
			}
		} catch (NoSuchAlgorithmException e) {
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public User getUserByEmail(String email) throws UserException {
		User user = null;
		try {
			user = getUserDAO().getByEmail(email);
		} catch (UserException e) {
			e.printStackTrace();
			LogSISA.log.error(e.getMessage());
		}
		return user;
	}

	public User getUserById(int id) throws UserException {
		User user = null;
		user = getUserDAO().getById(id);

		return user;
	}

	public List<User> list() throws UserException {
		List<User> list = getUserDAO().list();
		return list;
	}

	public void delete(User user) throws UserException, EntityConstraintViolationException {
		getUserDAO().delete(user.getUserId());
	}

	public void sendPassword(User user) throws UserException {

		if (user != null) {

			try {
				String newUserPassword = PasswordUtil.getRandomPassword();
				String encriptedPassword = PasswordUtil.encryptPassword(newUserPassword);

				user.setPassword(encriptedPassword);
				getUserDAO().updateUserPassword(user);

				String messageContent = String.format(MsgConstants.MSG_CONTENT, newUserPassword);
				EmailUtil.SendWithTLS(user.getEmail(), MsgConstants.MSG_SUBJECT, messageContent);

			} catch (NoSuchAlgorithmException | UnsupportedEncodingException
					| MessagingException | ConnectionFactoryException e) {
				LogSISA.log.error(e.getMessage(), e);
				e.printStackTrace();
				throw new UserException(MsgConstants.MSG_ERROR_SEND_PASSWORD);
			}
		} else {
			throw new UserException("Usuário não pode ser nulo.");
		}
	}
}