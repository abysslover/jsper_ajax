package com.javawide.book.blackbook1.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.SocketFactory;

public class SimpleClient extends AbClient {
	public SimpleClient(String host, int port){
		try {
			this.socket = SocketFactory.getDefault().createSocket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doEcho() throws IOException {
		BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
		String toServer;
		byte[] buffer = new byte[1024];
		int readed = 0;
		System.out.println("값을 입력해 주세요.");
		toServer = consoleInput.readLine();
		out.write(toServer.getBytes());
		readed = in.read(buffer);
		if(-1 == readed) return;
		System.out.println(new String(buffer, 0, readed));
	}
	public static void main(String[] args) {
		AbClient client = new SimpleClient("localhost", 10938);
		client.start();
	}
}