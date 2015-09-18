package cn.haut.elec.lucene.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

public class DataChartUtils {

	/** 使用传递的参数文件，实现文件上传的功能(以文件名的后缀作为后缀名)，并同时返回路径path */
	public static String fileReturnPath(File upload, String fileName) {
		// 获取上传的文件路径
		String bathPath = ServletActionContext.getServletContext().getRealPath(
				"/dataChart");
		// 使用当前时间在upload的文件夹创建日期的文件夹
		String datePath = new SimpleDateFormat("/yyyy/MM/dd/")
				.format(new Date());
		// 使用UUID的方式，作为上传文件的文件名
		String perfix = "";
		if (StringUtils.isNotBlank(fileName)) {
			int pos = fileName.lastIndexOf(".");
			perfix = fileName.substring(pos, fileName.length());
		}
		String filename = UUID.randomUUID().toString() + "" + perfix;

		// 如果没有日期文件夹，完成对日期文件夹的创建
		File dateFile = new File(bathPath + datePath);
		if (!dateFile.exists()) {
			// 创建文件夹
			dateFile.mkdirs();
		}

		// 最终的路径path
		String path = bathPath + datePath + filename;
		File destFile = new File(path);
		// 文件上传,不能删除临时文件
		// try {
		// FileUtils.copyFile(upload, destFile);
		// } catch (IOException e) {
		// throw new RuntimeException();
		// }
		upload.renameTo(destFile);

		return path;
	}

	public static void main(String[] args) {
		File srcFile = new File("f:/dir/a.txt");
		File destFile = new File("f:/dir/dir2xxxxxxx/a.txt");
		boolean flag = srcFile.renameTo(destFile);
		System.out.println("falg:" + flag);
	}
}
