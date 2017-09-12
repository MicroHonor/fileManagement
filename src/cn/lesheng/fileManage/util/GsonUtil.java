package cn.lesheng.fileManage.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
	public static Map<String,Object> jsonStrToMap(String jsonStr){
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(jsonStr, new TypeToken<HashMap<String,Object>>() {}.getType());
	}
	
	public static String mapToJsonStr(Map<String,Object> map){
		Gson gson = new GsonBuilder().create();
		return gson.toJson(map);
	}
}
