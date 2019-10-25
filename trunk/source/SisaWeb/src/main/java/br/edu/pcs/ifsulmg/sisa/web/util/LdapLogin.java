/*
* File:	     LdapLogin.java
* Creation date: 15/06/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import br.edu.pcs.ifsulmg.sisa.util.PropsUtil;

public class LdapLogin {
        
	/** The ldap ad server. */
	private static String _ldapAdServer = "ldap.ad.server";
	
	/** The ldap domain name. */
	private static String _ldapDomainName = "ldap.domain.name";	
	
	/** The _ldap search base. */
	private static String _ldapSearchBase = "ldap.search.base";
	
	/** The Constant _ldapUseAuthentication. */
	private static final String _ldapUseAuthentication = "ldap.use.authentication";
	
	/** The Constant _ldapUseSSL. */
	private static final String _ldapUseSSL = "ldap.use.ssl";
	
	public static void trustSelfSignedSSL() {
		try {
		    SSLContext ctx = SSLContext.getInstance("TLS");
		    X509TrustManager tm = new X509TrustManager() {

		        public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
		        }

		        public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
		        }

		        public X509Certificate[] getAcceptedIssuers() {
		            return null;
		        }
		    };
		    ctx.init(null, new TrustManager[]{tm}, null);
		    SSLContext.setDefault(ctx);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}

	public static DirContext authenticateWithLdap(String email, String password){
		
		String userName = extractUserNameFromEmail(email);
		
		final String ldapAdServer = PropsUtil.getInstance().getProperty(_ldapAdServer);
        final String ldapDomainName = PropsUtil.getInstance().getProperty(_ldapDomainName);     
        
        final String ldapDomainUsername = ldapDomainName + "\\" + userName;
        
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, ldapDomainUsername);
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapAdServer);
        env.put("java.naming.ldap.attributes.binary", "objectSID");
        
        try {
        	DirContext dap = new InitialDirContext(env);        	
			return dap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Attributes getUserAccountAttributes(String email, DirContext context){
		final String ldapSearchBase = PropsUtil.getInstance().getProperty(_ldapSearchBase);
 		
		if (context != null) {
			String userName = extractUserNameFromEmail(email);			
			String searchFilter = "(&(objectClass=user)(sAMAccountName=" + userName + "))";

	        SearchControls searchControls = new SearchControls();
	        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

	        NamingEnumeration<SearchResult> results = null;
	        		
	        try {
				results = context.search(ldapSearchBase, searchFilter, searchControls);
				if(results.hasMoreElements()) {
					Attributes attrs = ((SearchResult) results.next()).getAttributes();
					if (attrs != null) {
						System.out.println(attrs.get("distinguishedName"));
						System.out.println(attrs.get("givenname"));
						System.out.println(attrs.get("sn"));
						System.out.println(attrs.get("mail"));
						return attrs;
					}
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		return null;
	}
	
	public static String extractUserNameFromEmail(String email){
		if (email.contains("@")) {
			return email.substring(0, email.indexOf("@"));
		}		
		return email;
	}
	
	public static boolean useLdapAuthentication(){
		return Boolean.parseBoolean(PropsUtil.getInstance().getProperty(_ldapUseAuthentication));
	}
	
	public static boolean useLdapSSL() {
		return Boolean.parseBoolean(PropsUtil.getInstance().getProperty(_ldapUseSSL));
	}
    
    //Arquivado apenas para consultas
	public void showAllAttributes(SearchResult sr) throws NamingException{
		Attributes attrs = sr.getAttributes();
	    for (NamingEnumeration<?> ne = attrs.getAll(); ne.hasMore();) {
	      Attribute attr = (Attribute) ne.next();	      
	      System.out.println("Attribute ID: " + attr.getID() + " -- Value: " + attr.get());
	    }
	}
}