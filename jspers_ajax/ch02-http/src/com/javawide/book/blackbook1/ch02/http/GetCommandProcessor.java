package com.javawide.book.blackbook1.ch02.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GetCommandProcessor {
	private OutputStream out;
	private static final String PROTOCOL_NAME = "magneto://";
	private static final String CONTEXT_ROOT = new File("").getAbsolutePath();
	private static final String APPLICATION_ROOT = String.format("%s%s%s", CONTEXT_ROOT,
			System.getProperty("file.separator"), "WebContent"); 
	private File aFile;

	public void setOutput(OutputStream out) {
		this.out = out;		
	}
	public void process(String fromClient) throws IOException {
		if(!isValid(fromClient)) return;
		response();
	}
	private boolean isValid(String fromClient) {
		int commandOffset = fromClient.indexOf("GET ");
		int protocolOffset = fromClient.indexOf(PROTOCOL_NAME, commandOffset);
		int fileNameOffset = protocolOffset + PROTOCOL_NAME.length();
		this.aFile = new File(String.format("%s%s%s", APPLICATION_ROOT, System.getProperty("file.separator"),
				fromClient.substring(fileNameOffset)));
		return (-1 != commandOffset) && (-1 != protocolOffset) && aFile.exists();
	}
	private void response() throws IOException {
		byte[] buffer = new byte[1024];
		int readed = 0;
		FileInputStream fis = new FileInputStream(aFile);
		while(-1 != (readed = fis.read(buffer))) {
			out.write(buffer, 0, readed);
		}
		fis.close();
	}	
	public static void main(String[] args) {
		GetCommandProcessor processor = new GetCommandProcessor();
		processor.setOutput(System.out);
		try {
			processor.process("GET magneto://helloGetMethod.html");
			processor.process("GETT magneto://helloGetMethod1.html");
			processor.process("GE magneto://helloGetMethod1.html");
			processor.process("GET mangneto://helloGetMethod.html");
			processor.process("GET mangneto://helloGetMethod.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}