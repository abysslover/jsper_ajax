package com.javawide.book.blackbook1.ch02;

import java.io.IOException;

public interface IEchoService {
	void doStart() throws IOException;
	void doEcho() throws IOException;
	void close();
}
