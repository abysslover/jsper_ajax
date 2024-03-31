package com.javawide.book.blackbook1.ch11.files;

import java.io.*;

public class HelloFileReader {
	public static void read(String filePath, Writer out) {
		if(!new File(filePath).isFile()) return;
		InputStreamReader in = null;
		char[] rBuf = new char[1024];
		int readed =0;
		try {
			in = new InputStreamReader(new FileInputStream(filePath), "UTF8");
			while((readed = in.read(rBuf)) != -1) {
				out.write(new String(rBuf, 0, readed).replaceAll("\n", "<br />"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
