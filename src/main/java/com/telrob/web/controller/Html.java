package com.telrob.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telrob.core.message.Response;
import com.telrob.core.utils.StringUtils;
import com.telrob.web.entity.Christmas;
import com.telrob.web.entity.Student;
import com.telrob.web.entity.StudentExample;
import com.telrob.web.mapper.ChristmasMapper;
import com.telrob.web.mapper.StudentMapper;

@Controller
@RequestMapping
public class Html {
	@Autowired
	private ChristmasMapper christmasMapper;
	@RequestMapping("html/{index}")
	public String page(@PathVariable String index){
		return "/"+index;
	}
	@RequestMapping("/christmas")
	public String  cre(Integer id,String uid,ModelMap map) {
		if(id!=null&&!id.equals(0)&&uid!=null&&!uid.equals("")) {
			Christmas chris=christmasMapper.selectByPrimaryKey(id);
			if(chris!=null&&chris.getUid()!=null) {
				map.put("name", chris.getText());
			}else {
				map.put("name", "无法获取祝福信息");
			}
			
		}else {
			map.put("name", "非法连接");
		}
		
		return "/christmas";
	}
	@RequestMapping("/submit")
	@ResponseBody
	public Response<String> submit(String text){
		Response<String>response=new Response<String>();
		try {
			Christmas chris=new Christmas();
			chris.setCreateTime(new Date());
			chris.setText(text);
			String uid=StringUtils.getUUID32();
			chris.setUid(uid);
			christmasMapper.insertSelective(chris);
			Integer id=chris.getId();
			if(id==null) {
				response.setFlag(102);
				response.setMsg("无法返回id");
			}else {
				response.setFlag(0);
				response.setObj(id+"&uid="+uid);
			}
		}catch(Exception e) {
			response.setFlag(101);
			response.setMsg("插入数据失败");
		}
		
		return response;
	}
	
	@RequestMapping("/GGG")
	@ResponseBody
	public String GGG(String name,Integer id) {
		StudentExample example=new StudentExample();
		StudentExample.Criteria c=example.createCriteria();
		if(name!=null) {
			c.andNameEqualTo(name);
		}
		if(id!=null) {
			c.andIdEqualTo(id);
		}
		long start=System.currentTimeMillis();
		List<Student>list=studentMapper.selectByExample(example);
		long end=System.currentTimeMillis();
		return "time:"+(end-start);
	}
	
	@Autowired
	private StudentMapper studentMapper;
}
