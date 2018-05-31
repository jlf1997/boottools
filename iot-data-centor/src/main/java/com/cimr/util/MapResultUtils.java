package com.cimr.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapResultUtils {

	private static Map<String,Object> parseResultMapIncludeFields(Map<String,Object> map,String[] fields) {
		Map<String,Object> res = new HashMap<>();
		Iterator<String> iterator = map.keySet().iterator();
		String fieldi;
		while(iterator.hasNext()) {
			fieldi = iterator.next();
			if(fieldi==null) {
				break;
			}
			int index = 0;
			for(String field : fields) {
				if(fieldi.equals(field)) {
					res.put(fieldi, map.get(field));
					break;
				}
				index++;
			}
//			//字段数据库中不存在
//			if(index==fields.length) {
//				res.put(fieldi, null);
//			}
		}
		return res;
	}
	
	private static Map<String,Object> parseResultMapExcludeFields(Map<String,Object> map,String[] fields) {
		Map<String,Object> res = new HashMap<>();
		res.putAll(map);
		Iterator<String> iterator = map.keySet().iterator();
		String fieldi;
		while(iterator.hasNext()) {
			fieldi = iterator.next();
			if(fieldi==null) {
				break;
			}
			for(String field : fields) {
				if(fieldi.equals(field)) {
					res.remove(fieldi);
					break;
				}
				
			}
			
		}
		return res;
	}
	
	
	public static Map<String,Object> getList(Map<String,Object> map,String[] fields,int type){
		Map<String,Object> res = null ;
		if(fields!=null && fields.length>0) {
			//表示只查询给的字段
			if(type>0) {
				res = parseResultMapIncludeFields(map,fields);
			}
			//表示过滤给定的字段
			if(type<0) {
				res = parseResultMapExcludeFields(map,fields);
    		}
		}
		if(res==null) {
			return map;
		}
		return res;
	}
}
