package com.javawide.book.blackbook1.ch11.files;

import java.io.*;

public class HelloFileReader {
	public static String read(String filePath) {
		if(!new File(filePath).exists()) return "없음";
		InputStreamReader in = null;
		StringBuffer buffer = new StringBuffer(100);
		char[] rBuf = new char[1024];
		int readed =0;
		try {
			in = new InputStreamReader(new FileInputStream(filePath), "UTF8");
			while((readed = in.read(rBuf)) != -1) {
				buffer.append(new String(rBuf, 0, readed));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(HelloFileReader.read("WebContent/HelloCategory.xml"));
	}
}
