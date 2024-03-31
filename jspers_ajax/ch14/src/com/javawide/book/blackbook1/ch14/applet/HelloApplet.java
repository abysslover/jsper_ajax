package com.javawide.book.blackbook1.ch14.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.*;

public class HelloApplet extends Applet {
	private static final long serialVersionUID = -8194995793037719676L;
	private String name;
	public void start() {
		String userName = getParameter("name");
		this.name = (null == userName || "null".equals(userName)) ? "GUEST" : userName;
		createHelloAppletFile();
	}
	private void createHelloAppletFile() {
		FileOutputStream fos = null;
		try {
			String fileName = new File("").getAbsolutePath();
			fileName += "/HelloSignedApplet.txt";
			fos = new FileOutputStream(fileName);
			fos.write("Hello Signed Applet!".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fos)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		g.drawString("Hello " + this.name, 10, 10);
		g.drawString(new File("").getAbsolutePath(), 10, 20);
	}
}