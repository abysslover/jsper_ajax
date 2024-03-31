package com.javawide.book.blackbook1.ch14.jnlp;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.javawide.book.blackbook1.ch14.chatting.IMessageFilter;

public class UserFilter implements IMessageFilter {
	private final String ADD_USER = "addUser";
	private final String REMOVE_USER = "removeUser";
	private ChatServerGUI server;
	public UserFilter(ChatServerGUI server) {
		this.server = server;
	}
	public ByteBuffer onReceive(ByteBuffer message) {
		String command = Charset.defaultCharset().decode(message).toString();
		String type = getCommandType(command);
		if(null == type) return message;
		int start = command.indexOf(type);
		String userId = command.substring(start + type.length() + 1);
		if(REMOVE_USER.equals(type)) {
			server.removeUser(userId);
		} else if(ADD_USER.equals(type)) {
			server.addUser(userId);
		}
		return message;
	}
	private String getCommandType(String command) {
		int start = command.indexOf(ADD_USER);
		if(-1 != start) return ADD_USER;
		start = command.indexOf(REMOVE_USER);
		if(-1 != start) return REMOVE_USER;
		return null;
	}
	public ByteBuffer onSend(ByteBuffer message) {
		String command = Charset.defaultCharset().decode(message).toString();
		String type = getCommandType(command);
		if(null == type) return message;
		int start = command.indexOf(type);
		String userId = command.substring(start + type.length() + 1);
		if(REMOVE_USER.equals(type)) {
			return ByteBuffer.wrap(
					String.format("%s 님께서 대화방에서 나가셨습니다.", userId).getBytes());
		} else if(ADD_USER.equals(type)) {
			return ByteBuffer.wrap(
					String.format("%s 님께서 대화방에 참여 하셨습니다.", userId).getBytes());
		}
		return message;
	}

}
