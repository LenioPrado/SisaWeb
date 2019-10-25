/*
* File:	     MenuUtil.java
* Creation date: 27/04/2016
* Author:        Lênio
*/
package br.edu.pcs.ifsulmg.sisa.web.util;

import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.edu.pcs.ifsulmg.sisa.vo.Menu;

public class TreeUtil {

	private static TreeNode _tree;
	
	public static Menu getRootMenu(){
		String rootMenuDescription = MessagesUtil.getValue("lbl_root_menu");
		return new Menu(rootMenuDescription);
	}
	
	public static TreeNode createMenuTree(List<Menu> parentMenus){
		
		_tree = new DefaultTreeNode("Root", null);
		
		TreeNode main = new DefaultTreeNode("parent", getRootMenu(), _tree);
		main.setExpanded(true);
		
		for (Menu menu : parentMenus) {
			TreeNode node = new DefaultTreeNode("parent", menu, main);
			
			for (Menu childMenu : menu.getSubMenus()) {
				node.getChildren().add(new DefaultTreeNode("document", childMenu, node));
			}		
		}
		
		return _tree;
	}
}
