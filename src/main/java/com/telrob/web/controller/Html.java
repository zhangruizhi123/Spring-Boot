package com.telrob.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class Html {
	@RequestMapping("html/{index}")
	public String page(@PathVariable String index){
		return "/"+index;
	}
}
