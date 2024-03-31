package com.javawide.book.blackbook1.ch14.chatting;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class NIOServer extends AbNIOEchoService{
	public NIOServer(String host, int port) throws IOException {
		super(host, port);
	}

	protected void write(SelectionKey key) throws IOException {
		key.interestOps(SelectionKey.OP_READ);
	}

	protected void read(SelectionKey key) throws IOException {
		ByteBuffer wrapBuffer = null;
		try {
			wrapBuffer = getCopiedBuffer(key);
		for (SelectionKey aKey : selector.keys()) {
			if((aKey.channel() instanceof ServerSocketChannel)) continue;
			for(IMessageFilter filter : filters) {
				wrapBuffer = filter.onReceive(wrapBuffer);
				wrapBuffer.rewind();
			}
		}
		broadcast(wrapBuffer);
		if(key.isValid()) key.interestOps(SelectionKey.OP_WRITE);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void broadcast(String message) {
		try {
			broadcast(ByteBuffer.wrap(message.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void broadcast(ByteBuffer wrapBuffer) throws IOException {
		for (SelectionKey aKey : selector.keys()) {
			if((aKey.channel() instanceof ServerSocketChannel)) continue;
			for(IMessageFilter filter : filters) {
				wrapBuffer = filter.onSend(wrapBuffer);
				wrapBuffer.rewind();
			}
			((SocketChannel)aKey.channel()).write(wrapBuffer);
		}
	}

	protected SelectableChannel getChannel() throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		channel.socket().bind(new InetSocketAddress(host, port));
		channel.register(selector, SelectionKey.OP_ACCEPT);
		return channel;
	}

	public static void main(String[] args) {
		AbNIOEchoService server;
		try {
			server = new NIOServer("javawide.com", 9090);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
