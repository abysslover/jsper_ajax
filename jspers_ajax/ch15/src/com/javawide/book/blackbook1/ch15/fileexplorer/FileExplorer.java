package com.javawide.book.blackbook1.ch15.fileexplorer;

import java.io.File;

public class FileExplorer {
	public static File[] getFiles(String path) {
		if(null == path) path = ".";
		path = path.replaceAll("\\.\\.", "");
		File f = new File(path);
		if(f.exists())
			return new File(path).listFiles();
		else return new File[]{new File(".")};
	}
	public static void main(String[] args) {
		for(File f : FileExplorer.getFiles(".")) {
			if(f.isDirectory())
				System.out.print("Directory : ");
			else if(f.isFile())
				System.out.print("File : ");
				System.out.println(f.getName());
		}
	}
}