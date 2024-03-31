package com.javawide.book.blackbook1.ch14.chatting;

import java.nio.ByteBuffer;

public interface IMessageFilter {
	ByteBuffer onReceive(ByteBuffer message);
	ByteBuffer onSend(ByteBuffer message);
}
