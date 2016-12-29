package cn.baozun.com.filter;

import java.io.File;
import java.io.FileFilter;
/**
 * 自定义文件过滤器
 * @author Administrator
 *
 */
public class MyFileFilter implements FileFilter {
	
	private String sufix;
	

	public MyFileFilter(String sufix) {
		super();
		this.sufix = sufix;
	}



	public boolean accept(File pathname) {
		
		return pathname.isDirectory()||(pathname.isFile()&&pathname.getName().endsWith(sufix));
	}

}
