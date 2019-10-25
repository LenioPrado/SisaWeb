/*
* File:	     MenuDAO.java
* Creation date: 13/04/2016
* Author:        Amanda
*/
package br.edu.pcs.ifsulmg.sisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.pcs.ifsulmg.sisa.exceptions.MenuException;
import br.edu.pcs.ifsulmg.sisa.util.LogSISA;
import br.edu.pcs.ifsulmg.sisa.vo.Menu;

/**
 * The Class MenuDAO.
 */
public class MenuDAO extends BaseDAOWithId<Menu, MenuException> {
	
	@Override
	protected String getInsertSql() {
		return "insert into menus values (null,?,?,?,?)";
	}

	@Override
	protected String getUpdateSql(Menu vo) {
		return "update menus set parent_id=?, description=?, menu_order=?, page_id=?" +
				" where menu_id=" + vo.getMenuId();
	}

	@Override
	protected String getDeleteSql(int id) {
		return "delete from menus where menu_id=" + id;
	}

	@Override
	protected String getWhereByIdSql(int id) {
		return " where m.menu_id=" + id;
	}

	@Override
	protected String getListSql() {
		return "select m.menu_id, m.description, m.parent_id, mm.description, m.menu_order, " +
				" m.page_id, p.name, p.url from menus m " +
				" left outer join menus mm on m.parent_id = mm.menu_id " +
				" left outer join pages p on m.page_id = p.page_id " + 
				" left outer join pages_roles pr on pr.page_id = p.page_id " +
				" left outer join actions a on pr.action_id = a.action_id ";
	}
	
	@Override
	protected String getOrderBySql(){
		return " order by m.parent_id, m.menu_order ";
	}
	
	@Override
	protected String getGroupBySql(){
		return " GROUP BY menu_id ";
	}

	@Override
	protected Object[] getData(Menu vo) {
		Integer parentId = vo.hasParent() ? vo.getParent().getMenuId() : null;
		Integer pageId = vo.getPage() != null ? vo.getPage().getPageId() : null; 
		Object[] objs = { parentId, vo.getDescription(), vo.getMenuOrder(), pageId };
		return objs;
	}

	@Override
	protected Menu fillEntityProperties(ResultSet rs) throws SQLException {
		Menu vo = new Menu();
		
		vo.setMenuId(rs.getInt("m.menu_id"));
		vo.setParent(new Menu());
		vo.getParent().setMenuId(rs.getInt("m.parent_id"));
		vo.setDescription(rs.getString("m.description"));
		vo.setMenuOrder(rs.getInt("m.menu_order"));
		vo.getPage().setPageId(rs.getInt("m.page_id"));
		vo.getPage().setName(rs.getString("p.name"));
		vo.getPage().setUrl(rs.getString("p.url"));
		
		return vo;
	}
	
	@Override
	protected void logSql(String sql) {
		LogSISA.log.info(sql);
	}
	
	public List<Menu> getParentMenusWithChildrenByRoleAndAction(int roleId, int actionId) throws MenuException {
		String sql = getListSql() + 
				" WHERE pr.role_id = " + roleId + " AND a.action_id = " + actionId +
				getGroupBySql() + getOrderBySql();
		return organizeMenus(listBySql(sql), false);		
	}

	public List<Menu> getParentMenusWithChildren() throws MenuException {

		List<Menu> menus = list();
		return organizeMenus(menus, true);		
	}
	
	private List<Menu> organizeMenus(List<Menu> menus, boolean keepParentWithNoChilds){
		List<Menu> parentMenus = new ArrayList<Menu>();
		List<Menu> parentMenusWithChildren = new ArrayList<Menu>();
		
		for (Menu menu : menus) {

			if (!menu.hasParent()) {
				parentMenus.add(menu);
			} else {
				int index = parentMenus.indexOf(menu.getParent());
				if (index >= 0) {
					Menu parent = parentMenus.get(index);
					parent.getSubMenus().add(menu);
					menu.setParent(parent);
				}
			} 				
		}
		if (keepParentWithNoChilds) {
			return parentMenus;
		} else {
			for (Menu menu : parentMenus) {
				if (menu.getSubMenus().size() > 0) {
					parentMenusWithChildren.add(menu);
				}
			}
			return parentMenusWithChildren;
		} 
	}
	
	@Override
	protected MenuException getEntityException(String message) {
		return new MenuException(message);
	}

	@Override
	protected MenuException getEntityException(Exception e) {
		return new MenuException(e);
	}
}