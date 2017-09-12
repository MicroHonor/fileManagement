package cn.lesheng.fileManage.util;

import java.util.Iterator;
import java.util.Map;

public class CompareUtils {
	public static String compareMap(Map<String,Object> f,Map<String,Object> s){
		StringBuffer result = new StringBuffer("|");
		Iterator<String> iter = f.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			if(!f.get(key).equals(s.get(key))){
				result.append(key).append("|");
			}
		}
		return result.toString();
	}
}
