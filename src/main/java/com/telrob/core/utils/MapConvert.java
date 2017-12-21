package com.telrob.core.utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 将map转换成一个对象
 * @author admin
 *
 */
public class MapConvert{
	public static <T>T convert(Map<String,String> map,Class<T> t) throws Exception{
		T obj=t.newInstance();
		Field[] fds=t.getDeclaredFields();
		for(Field fd:fds){
			String name=fd.getName();
			String val=map.get(name);
			if(val!=null&&!val.equals("")){
				Object value=str2Obj(fd,val);
				if(value!=null){
					fd.setAccessible(true);
					fd.set(obj, value);
				}
			}
		}
		return obj;
	}
	
	private static Object str2Obj(Field fd,String value) throws Exception{
		if(fd.getType().getName().equals(String.class.getName())){
			return value;
		}else if(fd.getType().getName().equals("int")||fd.getType().getName().equals(Integer.class.getName())){
			return Integer.parseInt(value);
		}else if(fd.getType().getName().equals("long")||fd.getType().getName().equals(Long.class.getName())){
			return Long.parseLong(value);
		}else if(fd.getType().getName().equals("double")||fd.getType().getName().equals(Double.class.getName())){
			return Double.parseDouble(value);
		}else if(fd.getType().getName().equals("float")||fd.getType().getName().equals(Float.class.getName())){
			return Float.parseFloat(value);
		}else if(fd.getType().getName().equals("short")||fd.getType().getName().equals(Short.class.getName())){
			return Short.parseShort(value);
		}else if(fd.getType().getName().equals("byte")||fd.getType().getName().equals(Byte.class.getName())){
			return Byte.parseByte(value);
		}else if(fd.getType().getName().equals("boolean")||fd.getType().getName().equals(Boolean.class.getName())){
			return Boolean.parseBoolean(value);
		}else if(fd.getType().getName().equals(Date.class.getName())){
			value=value.replaceAll("/", "-");
			//默认日期格式转换
			if(value.contains(":")){
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return fmt.parse(value);
			}else{
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				return fmt.parse(value);
			}
		}
		return null;
	}
}
