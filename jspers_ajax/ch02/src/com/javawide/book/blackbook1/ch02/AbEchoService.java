package com.javawide.book.blackbook1.ch02;

import java.io.IOException;

public abstract class AbEchoService extends Thread implements IEchoService {
	@Override
	public final void run() {
		try {
			doStart();
			System.out.println(this.getName() + "이(가) 시작");
			doEcho();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			close();
			System.out.println(this.getName() + "이(가) 종료");
		}
	}
}