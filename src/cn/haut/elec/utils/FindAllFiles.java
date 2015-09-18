package cn.haut.elec.utils;

import java.io.File;
public class FindAllFiles {

	public static void findAllFiles(File file, String finder) throws Exception {
		// 判断输入的用户是否是有小的路径
		if (!file.isAbsolute()) {
			System.out.println("你输入的不是有效的系统路径");
			return;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {// 如果一个文件夹 包含一个空的文件夹目录
				for (File f : files) {
					findAllFiles(f, finder);
				}
			}
		} else {
			// 文件目录包含 avi的打印出路径
			if (file.getName().contains(finder)) {
				System.out.println(file.getPath());
			}
			// 文件内容包含 avi的打印出路径
			/*
			 * BufferedReader bufferedReader = new BufferedReader(new
			 * FileReader( file)); String readline = ""; while ((readline =
			 * bufferedReader.readLine()) != null) { if
			 * (readline.contains(finder)) { System.out.println(file.getPath());
			 * } } bufferedReader.close();
			 */
		}
	}
	static long i=0;
	// 找到以....为后缀的文件路径
	@SuppressWarnings("unused")
	public static void findAllFiles2(File file, String finder) throws Exception {
		// 定义一个变量 记录查询到 结果的 数量
		long count = 0;
		
		// 判断输入的用户是否是有小的路径
		if (!file.isAbsolute()) {
			System.out.println("你输入的不是有效的系统路径");
			return;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {// 如果一个文件夹 包含一个空的文件夹目录
				for (File f : files) {
					if (f.isDirectory()) {
						findAllFiles2(f, finder);
						continue;
					}
					// 如果是文件的话 那么判断文件的后缀
					String fileName = f.getName();
					if(fileName.length()<=4){
						//如果文件目录的长度小于4那么说明文件的类型不是以.rmvb结束的
						continue;
					}
					String endwith = fileName.substring(fileName.length() - 4,
							fileName.length());
					System.out.println(++i);
					if (endwith.equals(finder)) {
						System.out.println(f.getPath());
						count++;
					}
				}
				/*if (count == 0) {
					System.out.println("目录" + file + "\n没有以" + finder + "结尾的文件");
				}*/
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// findAllFiles(new File("D:\\"),"avi");
		// 遍历电脑硬盘上的所有的文件
		/*String disk = "CDEFGHIJ";
		char[] disks = disk.toCharArray();
		for (int i = 0; i < disks.length; i++) {
			File file = new File(disks[i] + "://");
			if (file.isAbsolute()) {
				findAllFiles2(file, ".rmvb");
			}
		}*/
		  findAllFiles(new File("G:/"),".rmvb");//搜索出所有以。avi的文件
		/*
		 * String fileName="sjsjsjsjsjsjsjsj.avi"; String
		 * sss=fileName.substring(fileName.length()-4, fileName.length());
		 * System.out.println(sss);
		 */
	}

}
