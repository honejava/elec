package cn.haut.elec.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/***
 * 使用注解来实现用户权限的管理
 * @author Administrator
 *
 */
public @interface AnnotationLimit {

	String mid(); // 子模块模块名称

	String pid(); // 父模块操作名称
}
