package cn.haut.elec.utils;

import org.apache.struts2.ServletActionContext;


public class ValueStackUtils {

	/**将传递的对象压入栈顶*/
	public static void setValueStatck(Object object) {
		ServletActionContext.getContext().getValueStack().pop();//删除栈顶之前对象
		ServletActionContext.getContext().getValueStack().push(object);//将新的对象压入栈顶
	}

}
