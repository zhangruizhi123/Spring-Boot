package com.telrob.core.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;

import sun.font.FontDesignMetrics;

import com.telrob.core.consts.Consts;

/**
 * 用于生成图像验证码的工具类
 * @author admin
 *
 */
public class ImageUtils {
	public static String writeImage(OutputStream output) throws IOException{
		Font font=new Font("",Font.BOLD, 60);
		FontMetrics fm=FontDesignMetrics.getMetrics(font);
		BufferedImage bufferedImage=new BufferedImage(200, 100, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2=(Graphics2D)bufferedImage.getGraphics();
		g2.setFont(font);
		String str=RandomStringUtils.random(4, Consts.RANDOM_CODE);
		g2.setColor(Color.BLACK);
		int widths[]=fm.getWidths();
		//g2.rotate(30*Math.PI/180);
		g2.drawString(str, 20,fm.getHeight()-10);
//		g2.drawString(str, 20,fm.getHeight()-10);
		ImageIO.write(bufferedImage, "png", output);
		return str;
	}
	
	
}
