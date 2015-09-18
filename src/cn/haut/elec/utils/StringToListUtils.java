package cn.haut.elec.utils;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import edu.emory.mathcs.backport.java.util.Arrays;

public class StringToListUtils {

	/**
	 * 指定分割的字符，分割字符串，返回List<String>
	 * @param String：表示分割的字符串
	 * @param string：表示分割的字符
	 * @return，分割后的结果集
	 */
	@SuppressWarnings("unchecked")
	public static List<String> stringToList(String name, String flag) {
		List<String> list = null;
		if(StringUtils.isNotBlank(name)){
			String [] arrays = name.split(flag);
			if(arrays!=null && arrays.length>0){
				//将数组转换成集合
				list = Arrays.asList(arrays);
			}
		}
		return list;
	}

}
