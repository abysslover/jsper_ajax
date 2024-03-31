package com.javawide.book.blackbook1.ch02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class AbClient extends AbEchoService {
	protected Socket socket;
	protected InputStream in;
	protected OutputStream out;

	@Override
	public void doStart() throws IOException {
		buildStream();
	}

	private void buildStream() throws IOException {
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();		
	}

	@Override
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
