package com.javawide.book.blackbook1.ch02.http;

import java.io.IOException;

import com.javawide.book.blackbook1.ch02.ServerWorker;
import com.javawide.book.blackbook1.ch02.SimpleServer;

public class MagnetoServer extends SimpleServer {
	public MagnetoServer(int port) {
		super(port);
	}
	@Override
	public void doEcho() throws IOException {
		while(true) {
			ServerWorker worker = new MagnetoServerWorker(socket.accept()); 
			this.clients.add(worker);
			worker.start();
		}
	}
	public static void main(String[] args) {
		MagnetoServer server = new MagnetoServer(10938);
		server.start();
	}
}
