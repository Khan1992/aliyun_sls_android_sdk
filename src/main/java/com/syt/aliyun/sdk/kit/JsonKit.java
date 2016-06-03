package com.syt.aliyun.sdk.kit;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 只针对LogItem进行生成JSON字符串， 不依赖于第三包JAR
 * @author laotang
 *
 */
public class JsonKit {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String toJson(Object valueObj) {
		if(ToolsKit.isEmpty(valueObj))  throw new NullPointerException("obj is null");
		
		if(valueObj instanceof String) {
			return "\"" + valueObj+ "\"";
		}
		
		if(valueObj instanceof Integer) {
			return valueObj.toString();
		}
		
		if(valueObj instanceof Map){
			return map2Json((Map)valueObj);
		}
		
		if(valueObj instanceof Collection){
			return map2Collection((Collection)valueObj);
		}
		
		return null;
		
	}
	
	
	private static String map2Json(Map<String, Object> map) {
		if(ToolsKit.isEmpty(map)) return "null";
		StringBuilder sb = new StringBuilder("{");
		boolean first = true;
		for(Iterator<Entry<String, Object>> iter = map.entrySet().iterator(); iter.hasNext();) {
			if(first) first = false; else sb.append(",");
			Entry<String,Object> entry = iter.next();
			sb.append("\"")
				.append(entry.getKey())
				.append("\":")
				.append(toJson(entry.getValue()));
		}
		sb.append("}");
		return sb.toString();
	}
	
	private static String map2Collection(Collection<Object> collection) {
		if(ToolsKit.isEmpty(collection)) return "null";
		StringBuilder sb = new StringBuilder("[");
		boolean first = true;
		for(Iterator<Object> iter = collection.iterator(); iter.hasNext();) {
			if(first) first = false; else sb.append(",");
			Object obj = iter.next();
			if(ToolsKit.isEmpty(obj)){
				sb.append("null");
			} else {
				sb.append(toJson(obj));
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
}