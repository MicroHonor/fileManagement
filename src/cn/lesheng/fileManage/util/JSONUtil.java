package cn.lesheng.fileManage.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.helpers.Util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class JSONUtil {
//	static {  
//        //注册器  
//        MorpherRegistry mr = JSONUtils.getMorpherRegistry();  
//  
//        //可转换的日期格式，即Json串中可以出现以下格式的日期与时间  
//        DateMorpher dm = new DateMorpher(new String[] { "yyyy-MM-dd'T'HH:mm:ss" });  
//        mr.registerMorpher(dm);  
//    }  
	
//	public static List<Object> jsonArrayStrToBean(String jsonArrayStr,Class<?> cls){
////		JSONArray array = JSONArray.fromObject(jsonArrayStr);
////		List<Object> list = new ArrayList<Object>();
////		Gson gson = new Gson();
////		Type listType = new TypeToken<List<T>>() {
////        }.getType();
////		for(int i=0;i<array.size();i++){
////			JSONObject jObj = (JSONObject) array.get(i);
//////			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd"}));
////			String[] dateFormats = new String[] {"yyyy/MM/dd","yyyy-MM-dd'T'HH:mm:ssZ"};  
////			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats)); 
////			Object obj = gson.fromJson(jObj.toString(), cls);
////			
//////			list.add(JSONObject.toBean(jObj,cls));
////		}
////		return list;
//	}
	
}
