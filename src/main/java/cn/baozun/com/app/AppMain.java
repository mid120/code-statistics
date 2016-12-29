package cn.baozun.com.app;

import java.io.File;

import cn.baozun.com.filter.MyFileFilter;
import cn.baozun.com.util.ReadLineUtil;


/**
 * 程序的入口
 * @author Administrator
 * @date 2016年12月29日13:26:33
 *
 */
public class AppMain {
	static Long sum=0L;
	static MyFileFilter filter;
	public static void main(String[] args) {
//		args=new String[]{"E:\\freeTime\\myHomeWork\\itcast-quartz",".xml"};
		String path =args[0];
		filter=new MyFileFilter(args[1]);
		File f = new  File(path);
		method(f);
		System.out.println(path+" :"+sum);
	}
	
	/**
	 * 递归遍历出文件下的所有java文件。
	 * @param f
	 */
	public static void method(File f){
		File[] list = f.listFiles(filter);
		if(list!=null){
			for (int i = 0; i < list.length; i++) {
				if(list[i].isDirectory()){
				   method(list[i]);
				}else{
					sum+=ReadLineUtil.statisticsCodeLine(list[i]);
				}
			}
		}
	}
}
