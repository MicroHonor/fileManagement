package cn.lesheng.fileManage.util;

import java.lang.reflect.ParameterizedType;
/**
 * 泛型的操作类
 * @author Administrator
 *
 */
public class GenericType {
	/**
	 * 得到当前第一个泛型代表类的class
	 * @param cls 当前类
	 * @return 泛型的class
	 */
	public static Class<?> getEntityClass(Class<?> cls) {
		ParameterizedType pt = (ParameterizedType) cls.getGenericSuperclass();
		return (Class<?>)pt.getActualTypeArguments()[0];
	}

}
