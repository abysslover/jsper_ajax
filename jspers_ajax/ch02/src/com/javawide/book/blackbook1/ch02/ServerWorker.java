package com.javawide.book.blackbook1.ch02;

import java.io.IOException;
import java.net.Socket;

public class ServerWorker extends AbClient {
	public ServerWorker(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void doEcho() throws IOException {
		byte[] buffer = new byte[1024];
		int readed = 0;
		String fromClient;
		while(-1 != (readed = in.read(buffer))) {
			fromClient = new String(buffer, 0, readed);
			System.out.println(fromClient);
			out.write(buffer, 0, readed);
		}
	}
}
