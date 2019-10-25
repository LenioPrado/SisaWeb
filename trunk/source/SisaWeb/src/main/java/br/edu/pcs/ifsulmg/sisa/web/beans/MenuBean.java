/*
 * File:	     MenuBean.java
 * Creation date: 13/04/2016
 * Author:        Lênio
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
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.MenuModel;

import br.edu.pcs.ifsulmg.sisa.exceptions.EntityConstraintViolationException;
import br.edu.pcs.ifsulmg.sisa.exceptions.MenuException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Menu;
import br.edu.pcs.ifsulmg.sisa.vo.Role;
import br.edu.pcs.ifsulmg.sisa.web.constants.MsgConstants;
import br.edu.pcs.ifsulmg.sisa.web.delegate.MenuDelegate;
import br.edu.pcs.ifsulmg.sisa.web.enums.ActionEnum;
import br.edu.pcs.ifsulmg.sisa.web.util.BeanUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.MenuUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.MessagesUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.TreeUtil;
import br.edu.pcs.ifsulmg.sisa.web.util.UserAccessUtils;

@Named
@ViewScoped
public class MenuBean extends CommonBean implements Serializable {

	private static final long serialVersionUID = -9170371936820166497L;
	
	@Inject
	private MenuDelegate _menuDelegate;

	private MenuModel _menuModel;
	
	private TreeNode _tree;
	
	private Menu _menu;
	
	private List<Menu> _menusList;
	
	private TreeNode _selectedNode;
	
	@PostConstruct
	public void init() {
		loadParentMenusList();
		createMenusList();		
	}
	
	private void loadParentMenusList(){
		try {
			Role role = UserAccessUtils.getInstance().getRoleInSession();
			_menusList = _menuDelegate.getParentMenusWithChildrenByRoleAndAction(role.getRoleId(), ActionEnum.LIST.getIndex());
		} catch (MenuException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_MENUS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}
	}	
	
	private void createMenusList(){
		_menuModel = MenuUtil.getMenuModel(_menusList);			
	}
	
	public void createTreeList(){
		try {
			_menusList = _menuDelegate.getParentMenusWithChildren();				
		} catch (MenuException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_LOADING_LIST_MENUS, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();			
		}		
		
		if (_menusList.size() > 0 && _menusList.get(0).getMenuId() == 0) {
			_menusList.remove(0);
		}
		_tree = TreeUtil.createMenuTree(_menusList);
		if (_menusList.size() > 0 && _menusList.get(0).getMenuId() > 0) {
			_menusList.add(0, TreeUtil.getRootMenu());
		}
	}
	
	public void save() {
		try {
			_menu.setMenuOrder(getMenuOrder());
			_menuDelegate.insert(_menu);
			reloadMenuAndTree();
			RequestContext.getCurrentInstance().execute("PF('saveMenuDialog').hide()");
			RequestContext.getCurrentInstance().update("formListMenus");
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_MENU, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (MenuException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_MENU, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			BeanUtil.setMessageGeneral(e.getMessage(), BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private int getMenuOrder(){
		int order = -1;		
		Menu toInsertParent = _menu.getParent();
		List<Menu> toFind = null;
		
		if (toInsertParent == null || toInsertParent.getMenuId() == 0) {
			toFind = _menusList;
		} else {
			toFind = toInsertParent.getSubMenus();
		}
		
		for (Menu menu : toFind) {
			order = menu.getMenuOrder() > order ? menu.getMenuOrder() : order;
		}
		
		return order + 1;
	}
	
	public void delete() {
		try {
			Menu menuToDelete = (Menu)_selectedNode.getData();
			_menuDelegate.delete(menuToDelete);
			reloadMenuAndTree();
			BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_DELETE_MENU, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
		} catch (MenuException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_DELETE_MENU, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_MENU_PARENT_MENU)) {
				message = MsgConstants.MSG_ERROR_DELETE_CONSTRAINT_MENU_PARENT_MENU;
			} 
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void reloadMenuAndTree(){
		_selectedNode = null;
		loadParentMenusList();
		createMenusList();	
	}
	
	public void cleanMenu() {
		_menu = new Menu();
		setParentMenu();
	}
	
	private void setParentMenu(){
		if (_selectedNode != null) {			
			if (_selectedNode.getType().equals(MessagesUtil.getValue("menu_type_document"))){
				_selectedNode = _selectedNode.getParent();
			}
			_menu.setParent((Menu)_selectedNode.getData());
		}		
	}
	
    public void onDragDrop(TreeDragDropEvent event) {
    	Menu source = (Menu)event.getDragNode().getData();
        Menu destiny = (Menu)event.getDropNode().getData();        
      
        String sourceParentDesc = source.hasParent() ? source.getParent().getDescription() : "//";
        String destinationParentDesc = destiny.hasParent() ? destiny.getParent().getDescription() : "//";
        
    	String message = String.format("Source: %s, Destination: %s -- Source Parent: %s, Destination Parent: %s", 
				source.getDescription(), destiny.getDescription(), sourceParentDesc, destinationParentDesc);
    	System.out.println(message);
        
        if (!source.hasParent() && !destiny.hasParent()) {
        	System.out.println("Drag Parent --> Drop Parent");
        	reorganizeOrder(_menusList, destiny.getMenuOrder(), source, destiny.getParent());
		} 
        else if(!source.hasParent() && destiny.hasParent()) {
        	System.out.println("Drag Parent --> Drop Child");
        	BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_MENU_CHANGES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_WARN);
		} 
        else if(source.hasParent() && !destiny.hasParent()) {
        	System.out.println("Drag Child --> Drop Parent");
        	reorganizeOrder(destiny.getSubMenus(), 0, source, destiny);
		} 
        else if(source.hasParent() && destiny.hasParent()) {
        	System.out.println("Drag Child --> Drop Child");
        	reorganizeOrder(destiny.getParent().getSubMenus(), destiny.getMenuOrder(), source, destiny.getParent());
		}        
    }
    
    private void reorganizeOrder(List<Menu> toProcess, int position, Menu dragMenu, Menu dropParent){
    	for (Menu menu : toProcess) {
			if (menu.getMenuOrder() > position && menu != dragMenu) {
				menu.increaseOrder();
				update(menu);
			}
		}
    	
    	dragMenu.setMenuOrder(position+1);
    	dragMenu.setParent(dropParent);
    	update(dragMenu);
    	reloadMenuAndTree();

    	BeanUtil.setMessageGeneral(MsgConstants.MSG_SUCCESS_SAVE_MENU_CHANGES, BeanUtil.ID_MSG, FacesMessage.SEVERITY_INFO);
    }
    
    private void update(Menu menu){
		try {
			_menuDelegate.update(menu);			
		} catch (MenuException e) {
			BeanUtil.setMessageGeneral(MsgConstants.MSG_ERROR_SAVE_MENU, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		} catch (EntityConstraintViolationException e) {
			String message = e.getMessage();
			String constraint = e.getCause().getMessage().toLowerCase();
			if (constraint.contains(MsgConstants.CONSTRAINT_MENU_PARENT_MENU)) {
				message = MsgConstants.MSG_ERROR_SAVE_MENU;
			} 
			BeanUtil.setMessageGeneral(message, BeanUtil.ID_MSG, FacesMessage.SEVERITY_ERROR);
			LogSISA.log.error(e.getMessage());
			e.printStackTrace();
		}
    }
	
	public Menu getMenu() {
        return _menu;
    }
 
    public MenuModel getMenuModel() {
        return _menuModel;
    }

	public TreeNode getTree() {
		return _tree;
	}

	public TreeNode getSelectedNode() {
		return _selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		_selectedNode = selectedNode;
	}

	public List<Menu> getMenusList() {
		return _menusList;
	}

	public void setMenusList(List<Menu> menusList) {
		_menusList = menusList;
	}
}
