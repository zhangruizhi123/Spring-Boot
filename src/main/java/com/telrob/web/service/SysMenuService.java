package com.telrob.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telrob.core.consts.Consts;
import com.telrob.web.entity.SysMenu;
import com.telrob.web.entity.SysMenuExample;
import com.telrob.web.entity.SysUser;
import com.telrob.web.entity.UserMenu;
import com.telrob.web.entity.UserMenuExample;
import com.telrob.web.mapper.UserMenuMapper;


@Service
public class SysMenuService {
	
	@Autowired
	private UserMenuMapper userMenuMapper;
	
	public List<SysMenu> listAll(HttpServletRequest request){
		SysUser users = (SysUser) request.getSession().getAttribute(Consts.ADMIN_SESSION);
		//菜单只查询两级
		List<SysMenu> menuList=new ArrayList<SysMenu>();
		SysMenuExample sysmenuExample=new SysMenuExample();
		sysmenuExample.setOrderByClause("orders");
		List<SysMenu> list=new ArrayList<SysMenu>();
		if(users==null){
			//Integer id=users.getId();
			UserMenuExample userMenuExample =new UserMenuExample();
			//去除重复数据
			userMenuExample.setDistinct(true);
			userMenuExample.createCriteria();//.andUserIdEqualTo(id);
			List<UserMenu> listMenu=userMenuMapper.selectByExample(userMenuExample);
			if(listMenu!=null&&listMenu.size()>0){
				for(UserMenu um:listMenu){
					SysMenu menu=new SysMenu(um);
					list.add(menu);
				}
			}
		}
		//List<Sysmenu> list=sysmenuMapper.selectByExample(sysmenuExample);
		Map<String,SysMenu> pmenu=new HashMap<String,SysMenu>();
		for(SysMenu menu:list){
			//说明是一级菜单
			if(menu.getPid()==0){
				menuList.add(menu);
				pmenu.put(""+menu.getId(), menu);
			}
		}
		//遍历下级菜单
		for(SysMenu menu:list){
			if(menu.getPid()!=0){
				SysMenu m=pmenu.get(""+menu.getPid());
				if(m!=null){
					m.addChild(menu);
				}
			}
		}
		return menuList;
	}
}
