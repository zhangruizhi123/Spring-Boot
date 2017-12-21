package com.telrob.core.message;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telrob.core.consts.Consts;
import com.telrob.core.utils.ImageUtils;


/**
 * 
 * @author 张瑞志
 *
 * 创建时间:2017年3月11日 上午10:25:19
 *
 */
@Controller
@RequestMapping("/user/image")
public class ImageController {
	/**
	 * 图片验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/code")
	public void getImage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		OutputStream output=response.getOutputStream();
		String code=ImageUtils.writeImage(output);
		//图片验证码
		session.setAttribute(Consts.IMAGE_SESSION, code.toLowerCase());
		output.close();
	}
	
}
