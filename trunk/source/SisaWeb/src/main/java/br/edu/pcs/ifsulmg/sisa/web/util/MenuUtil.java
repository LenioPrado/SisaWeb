/*
* File:	     MenuUtil.java
* Creation date: 27/04/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.util.List;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.edu.pcs.ifsulmg.sisa.vo.Menu;
import br.edu.pcs.ifsulmg.sisa.vo.User;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;

public class MenuUtil {

	private static MenuModel _menu;

	public static MenuModel getMenuModel(List<Menu> parentMenus){
		
		_menu = new DefaultMenuModel(); 
		
		for (Menu menu : parentMenus) {
			DefaultSubMenu submenu = new DefaultSubMenu(menu.getDescription());
			submenu.setIcon("ui-icon-document");
			
			for (Menu childMenu : menu.getSubMenus()) {
				DefaultMenuItem menuItem = new DefaultMenuItem(childMenu.getDescription());				
				menuItem.setUrl(childMenu.getPage().getUrl());
				submenu.addElement(menuItem);
			}			
			_menu.addElement(submenu);
		}
		
		//createCurrentUserMenuItem();
		
		return _menu;
	}
	
	public static void createCurrentUserMenuItem(){
		String firstName = getUserFirstName();
		DefaultSubMenu submenu = new DefaultSubMenu(firstName);
		submenu.setIcon("ui-icon-document");
		
		DefaultMenuItem menuEditUser = new DefaultMenuItem(MessagesUtil.getValue(MsgConstants.MSG_EDIT_USER_KEY));				
		menuEditUser.setAjax(true);
		menuEditUser.setOncomplete("PF('saveUserDialog').show()");
		menuEditUser.setIcon("ui-icon-pencil");
		menuEditUser.setCommand("#{userBean.loadUser}");
		menuEditUser.setUpdate(":formIncludeUser");
		submenu.addElement(menuEditUser);
		
		_menu.addElement(submenu);
		
		DefaultMenuItem menuLogout = new DefaultMenuItem(MessagesUtil.getValue(MsgConstants.MSG_LOGOUT_KEY));				
		menuLogout.setIcon("ui-icon-extlink");
		menuLogout.setCommand("#{loginBean.logout}");
		submenu.addElement(menuLogout);
	}
	
	private static String getUserFirstName(){
		User currentUser = UserAccessUtils.getInstance().getUserInSession();
		
		if (currentUser != null && !currentUser.getName().isEmpty()) {
			String arr[] = currentUser.getName().split(" ", 2);
			return arr[0];
		} else {
			return MsgConstants.UNKHNOWN_USER;
		}		
	}
}
