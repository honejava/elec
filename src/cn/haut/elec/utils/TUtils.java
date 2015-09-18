package cn.haut.elec.utils;

import java.lang.reflect.ParameterizedType;


public class TUtils {

	/**
	 * 泛型转换
	 */
	@SuppressWarnings("rawtypes")
	public static Class getTGenericSuperClass(Class entity) {
		
		ParameterizedType parameterizedType = (ParameterizedType)entity.getGenericSuperclass();
		
		Class entityClass = (Class) parameterizedType.getActualTypeArguments()[0];
		
		return entityClass;
	}

}
