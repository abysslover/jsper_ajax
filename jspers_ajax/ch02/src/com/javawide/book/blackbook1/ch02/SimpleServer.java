package com.javawide.book.blackbook1.ch02;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

public class SimpleServer extends AbEchoService {
	private int port;
	protected ServerSocket socket;
	protected Vector<ServerWorker> clients = new Vector<ServerWorker>(100);
	public SimpleServer(int port) {
		this.port = port;
	}
	@Override
	public void doStart() throws IOException {
		this.socket = new ServerSocket(port);
	}
	@Override
	public void doEcho() throws IOException {
		while(true) {
			ServerWorker worker = new ServerWorker(socket.accept()); 
			this.clients.add(worker);
			worker.start();
		}
	}
	@Override
	public void close() {
		for(ServerWorker w : clients) {
			w.close();
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		SimpleServer server = new SimpleServer(10938);
		server.start();
	}
}
