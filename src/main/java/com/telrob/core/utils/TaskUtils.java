//package com.cjeg.Util;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.jsoup.Connection;
//import org.jsoup.Connection.Response;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import com.cjeg.web.admin.model.People;
//
///**
// * 
// * @author 张瑞志
// *
// * 创建时间:2017年8月23日 下午4:16:24
// *  专门用于跑任务
// */
//
//public class TaskUtils {
//	
//	//爬取页面的工具
//	public static List<People> spider(String urls) throws Exception{
//		List<People> list=new ArrayList<People>();
//		Connection connect=Jsoup.connect(urls).timeout(10000);
//		Response response=connect.execute();
//		int code=response.statusCode();
//		if(code==200){
//			//获取文档对象
//			Document document=Jsoup.connect(urls).get();
//			//获取要抓取的列集合
//			Elements elements=document.getElementsByTag("li");
//			for(Element ele:elements){
//				People people=new People();
//				//设置主键
//				people.setId(StringUtils.getUUID32());
//				//获取每个集合列表，在解析需要的字段
//				//根据class属性获取npn获取需要的列
//				Element ele1=ele.getElementsByClass("np1").get(0);
//				String indexNo=ele1.text();
//				people.setIndexNo(indexNo);
//				//获取绝对地址
//				String href=ele1.getElementsByTag("a").get(0).attr("abs:href");
//				people.setUrl(href);
//				Element ele2=ele.getElementsByClass("np2").get(0);
//				String title=ele2.text();
//				people.setTitle(title);
//				Element ele3=ele.getElementsByClass("np3").get(0);
//				String dateStr=ele3.text();
//				Date date=DateUtils.strTODate(dateStr, "yyyy.MM.dd");
//				people.setProduceData(date);
//				list.add(people);
//			}
//			
//		}else if(code==404){
//			//当状态码为404时说明没有网页了
//			return null;
//		}
//		return list;
//	}
//	
//	public static void main(String[] args) {
//		
//	}
//}
