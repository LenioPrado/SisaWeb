package br.edu.pcs.ifsulmg.sisa.test.bean;

import javax.inject.Inject;
import javax.naming.directory.DirContext;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.pcs.ifsulmg.sisa.test.configuration.WeldJUnit4Runner;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.web.delegate.UserDelegate;
import br.edu.pcs.ifsulmg.sisa.web.util.LdapLogin;

@RunWith(WeldJUnit4Runner.class)
public class LdapLoginTest {

	@Inject
	UserDelegate userDelegate;
	
	@Before
	public void setUp() throws Exception {
		//
	}
	
	@After
	public void tearDown() throws Exception {
		//
	}

	@Test
	public void testLoginUsingLdap() {
		System.out.println("Test Login Using Ldap...");
				
		try{			
			DirContext cxt = LdapLogin.authenticateWithLdap("lenio.prado", "PASSWORD");
			
			Assert.assertNotNull(cxt);
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		System.out.println("Test Login Using Ldap finished...");		
	}
	
	@Test
	public void testLoginUsingLdapAndSSL() {
		System.out.println("Test Login Using Ldap and SSL...");
				
		try{			
			LdapLogin.trustSelfSignedSSL();
			DirContext cxt = LdapLogin.authenticateWithLdap("lenio.prado", "PASSWORD");
			
			Assert.assertNotNull(cxt);
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		System.out.println("Test Login Using Ldap and SSL finished...");		
	}
	
	@Test
	public void testUserDelegate() {
		System.out.println("Test User Delegate...");
				
		try{
			User user = userDelegate.login("lenio.prado@ifsuldeminas.edu.br", "PASSWORD");
			
			Assert.assertNotNull(user);
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		System.out.println("Test User Delegate Finished...");		
	}
}
