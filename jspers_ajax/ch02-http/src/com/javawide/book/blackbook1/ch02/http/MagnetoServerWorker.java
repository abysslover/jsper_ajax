package com.javawide.book.blackbook1.ch02.http;

import java.io.IOException;
import java.net.Socket;

import com.javawide.book.blackbook1.ch02.ServerWorker;

public class MagnetoServerWorker extends ServerWorker {
	public MagnetoServerWorker(Socket socket) {
		super(socket);
	}
	
	@Override
	public void doEcho() throws IOException {
		byte[] buffer = new byte[1024];
		int readed = 0;
		String fromClient;
		if(-1 != (readed = in.read(buffer))) {
			fromClient = new String(buffer, 0, readed);
			GetCommandProcessor processor = new GetCommandProcessor();
			System.out.println(fromClient);
			processor.setOutput(out);
			processor.process(fromClient);
		}
	}
}
