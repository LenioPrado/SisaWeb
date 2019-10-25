/*
* File:	     EmailUtil.java
* Creation date: 04/06/2015
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.edu.pcs.ifsulmg.sisa.exceptions.ConnectionFactoryException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.util.PropsUtil;

/**
 * The Class EmailUtil.
 */
public final class EmailUtil {

	/** The _smtp host. */
	private static String _smtpHost = "mail.smtp.host";
	
	/** The _smtp port. */
	private static String _smtpPort = "mail.smtp.port";
	
	/** The _smtp ssl port. */
	private static String _smtpSslPort = "mail.smtp.sslport";
	
	/** The _smtp auth. */
	private static String _smtpAuth = "mail.smtp.auth";
	
	/** The _start tls enable. */
	private static String _startTlsEnable = "mail.smtp.starttls.enable";
	
	/** The _timeout. */
	private static String _timeout = "mail.smtp.timeout";
	
	/** The _email. */
	private static String _email = "mail.smtp.email";
	
	/** The _password. */
	private static String _password = "mail.smtp.password";	
	
	/** The _friendly email name. */
	private static String _friendlyEmailName = "friendlyEmailName";
    
    /**
     * Send with ssl.
     *
     * @param recipientEmail the recipient email
     * @param subject the subject
     * @param messageContent the message content
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws MessagingException the messaging exception
     * @throws ConnectionFactoryException the connection factory exception
     */
    public static void SendWithSSL(String recipientEmail, String subject, String messageContent) throws UnsupportedEncodingException, MessagingException, ConnectionFactoryException {
    	Send(recipientEmail, subject, messageContent, true);
    }
    
    /**
     * Send with tls.
     *
     * @param recipientEmail the recipient email
     * @param subject the subject
     * @param messageContent the message content
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws MessagingException the messaging exception
     * @throws ConnectionFactoryException the connection factory exception
     */
    public static void SendWithTLS(String recipientEmail, String subject, String messageContent) throws UnsupportedEncodingException, MessagingException, ConnectionFactoryException {
    	Send(recipientEmail, subject, messageContent, false);
    }
    
    /**
     * Send.
     *
     * @param recipientEmail the recipient email
     * @param subject the subject
     * @param messageContent the message content
     * @param useSSL the use ssl
     * @throws MessagingException the messaging exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws ConnectionFactoryException the connection factory exception
     */
    private static void Send(String recipientEmail, String subject, String messageContent, boolean useSSL) throws MessagingException, UnsupportedEncodingException {
        
    	if (recipientEmail.length() == 0) {
    		throw new IllegalArgumentException("O email do destinatário não foi fornecido.");
		}
    	
    	Properties properties = PropsUtil.getInstance();
    	
    	final String username = properties.getProperty(_email);
		final String password = properties.getProperty(_password);
		String friendlyEmailName = properties.getProperty(_friendlyEmailName);
		String smtpAuth = properties.getProperty(_smtpAuth); 
		String smtpStartTlsEnable = properties.getProperty(_startTlsEnable);
		String smtpHost = properties.getProperty(_smtpHost);
		String smtpPort = properties.getProperty(_smtpPort);
		String smtpSslPort = properties.getProperty(_smtpSslPort);
		String timeOut = properties.getProperty(_timeout);
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.starttls.enable", smtpStartTlsEnable);
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.timeout", timeOut);
		
		if (useSSL) {
			props.put("mail.smtp.socketFactory.port", smtpSslPort);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		Session session = Session.getInstance(props, authenticator);
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username, friendlyEmailName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject(subject);
			message.setText(messageContent);
 
			Transport.send(message);

		} catch (MessagingException | UnsupportedEncodingException e) {
			LogSISA.log.error( e.getMessage(), e);
			e.printStackTrace();
			throw e;
		}
    }
}