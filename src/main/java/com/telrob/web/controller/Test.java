package com.telrob.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Test")
public class Test {

	@RequestMapping("/index")
	public String gg(Map<String,Object> map){
		return "/index";
	}
	
	@RequestMapping("/table")
	@ResponseBody
	public Map<String,Object>dd(){
		Map<String,Object> map=new HashMap<String,Object>();
		List<P>ls=new ArrayList<P>();
		for(int i=0;i<100;i++) {
			P p=new P();
			p.setId(i);
			p.setName("张三"+i);
			p.setDesc("hhhh"+i);
			p.setLevel("level"+i);
			p.setParentName("parentName"+i);
			ls.add(p);
		}
		map.put("total",100);
		map.put("rows", ls);
		System.out.println("-======++++加载表格");
		return map;
	}
	public class P{
		String name;
		String parentName;
		String level;
		String desc;
		int id;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getParentName() {
			return parentName;
		}
		public void setParentName(String parentName) {
			this.parentName = parentName;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
	}
}
