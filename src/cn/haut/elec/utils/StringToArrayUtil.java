package cn.haut.elec.utils;

import org.junit.Test;

public class StringToArrayUtil {
	/**  
	* @Name: StringToArray
	* @Description: 截取字符串的方法
	* @Author: 甘亮（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-02-13（创建日期）
	* @Parameters: 无
	* @Return: String【】 字符串数组
	*/
	public static String[] StringToArray(String userID, String limit) {
		
		return userID.split(limit);
	}
	@Test
	public void test(){
		String[] obj=StringToArrayUtil.StringToArray("ddddddddddd, ffffffffffffff",", ");
		for (int i = 0; i < obj.length; i++) {
			System.out.println(obj[i]);
		}
	}

}
