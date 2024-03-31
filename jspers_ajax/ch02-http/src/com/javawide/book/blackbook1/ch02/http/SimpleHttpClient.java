package com.javawide.book.blackbook1.ch02.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.javawide.book.blackbook1.ch02.SimpleClient;

public class SimpleHttpClient extends SimpleClient {

	public SimpleHttpClient(String host, int port) {
		super(host, port);
	}
	
	@Override
	public void doEcho() throws IOException {
		BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
		String toServer;
		System.out.println("값을 입력해 주세요.");
		toServer = consoleInput.readLine();
		out.write((toServer + "\r\n\r\n").getBytes());
		byte[] buffer = new byte[1024];
		int readed = 0;
		while(-1 != (readed = in.read(buffer))) {
			System.out.write(buffer, 0, readed);
		}
	}

	public static void main(String[] args) {
		SimpleHttpClient client = new SimpleHttpClient("localhost", 8088);
		client.start();
	}
}