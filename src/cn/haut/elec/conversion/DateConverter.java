package cn.haut.elec.conversion;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.haut.elec.utils.StringToArrayUtil;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class DateConverter extends DefaultTypeConverter {
	@SuppressWarnings({ "rawtypes", "deprecation" })
	@Override
	public Object convertValue(Object value, Class toType) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// 将字符串转化为 日期
			if (java.util.Date.class.equals(toType)) {
				String[] strArr = (String[]) value;
				String date = strArr[0];
				String[] array = StringToArrayUtil.StringToArray(date, "/");
				String dates = array[2] + "/" + array[0] + "/" + array[1];
				return new Date(dates);
			}
			if (java.lang.String.class.equals(toType)) {
				Date date = (Date) value;
				return dateFormat.format(date);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return null;
	}
}
