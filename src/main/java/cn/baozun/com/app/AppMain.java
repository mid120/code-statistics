package cn.baozun.com.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

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
	public static void main(String[] args) throws FileNotFoundException {
//		args=new String[]{"D:\\workspace_20160912\\crm\\trunk",".java"};
		String path =args[0];
		filter=new MyFileFilter(args[1]);
		File f = new  File(path);
		method1(f);
		System.out.println(path+" :"+sum);
	}
	
	/**
	 * 递归遍历出文件下的所有java 或者其他类型的文件。
	 * @param f
	 */
	public static void method1(File f){
		File[] list = f.listFiles(filter);
		if(list!=null){
			for (int i = 0; i < list.length; i++) {
				if(list[i].isDirectory()){
				   method1(list[i]);
				}else{
					sum+=ReadLineUtil.statisticsCodeLine(list[i]);
				}
			}
		}
	}
	
	/**
	 * 下面是采用文件队列形式,遍历所有java 或者其他类型的文件
	 */
	//获取指定目录下的文件和文件夹对象
	public static void method2(File dir) throws FileNotFoundException {
		
		//定义一个容器保存所有的文件夹
		LinkedList<File> list = new LinkedList<File>();
		
		//先判断dir是否为null
		if( dir == null ){
			throw new NullPointerException("请正确传递一个目录，不能传递null");
		}
		//判断当前dir是否存在
		if( !dir.exists() ){
			throw new FileNotFoundException("传递的文件对象在硬盘上不存在");
		}
		//判断dir是否是文件夹
		if( !dir.isDirectory() ){
			throw new RuntimeException("传递的文件对象不是目录");
		}
		//获取指定的目录下的文件和文件夹对象
		File[] files = dir.listFiles(filter);
		if( files !=null ){
			//遍历
			for( File file : files ){
				//判断取出的file对象是否是文件夹
				if( file.isDirectory() ){
					//判断成立说明当前拿到的一定是文件夹，添加到容器中
					list.addLast(file);
				}else{
					sum+=ReadLineUtil.statisticsCodeLine(file);
				}
			}
		}
		//调用专门负责遍历集合的方法
		method_2(list);
	}
	
	public static void method_2(LinkedList<File> list) {
		//现在需要遍历集合，从集合中取出存放的文件夹对象，然后取出每个文件夹，继续列举
		while( list.size() > 0 ){
			//取出集合中的第一个文件夹对象，并把其删除
			File file = list.removeFirst();
			//获取这个文件夹下的文件和文件夹
			File[] files = file.listFiles(filter);
			
			//在使用列举的方法的时候一定要判断当前这个目录Java能不能访问，如果可以访问，就可以获取到这个目录下的文件和文件夹对象，如果不能访问
			//那么列举方法就会返回一个null
			if( files !=null ){
				for( File f : files ){  
					if( f.isDirectory() ) {
						list.addLast(f);
					}else{
						sum+=ReadLineUtil.statisticsCodeLine(f);
					}
				}
			}
		}
	}


	
}
