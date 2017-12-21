package com.telrob.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telrob.core.annotation.Authority;
import com.telrob.core.message.Response;
import com.telrob.web.entity.SysMenu;
import com.telrob.web.service.SysMenuService;

/**
 * 菜单管理
 * @author admin
 *
 */
@Controller
@RequestMapping("/sysmenu")
public class SyMenuController {
	
	@Autowired
	private SysMenuService SysMenuService;
	
	@Authority(value=Authority.ADMIN)
	@RequestMapping("/list")
	@ResponseBody
	public Response<List<SysMenu>> listMenus(HttpServletRequest request){
		Response<List<SysMenu>> result=new Response<List<SysMenu>>();
		try{
			result.setObj(SysMenuService.listAll(request));
		}catch(Exception e){
			result.setFlag(1);
			result.setMsg(e.getMessage());
		}
		return result;
	}
}
