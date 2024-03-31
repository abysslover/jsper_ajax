package com.javawide.book.blackbook1.ch14.chatting;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

public class NIOClient extends AbNIOEchoService {
	private List<ByteBuffer> requests = Collections.synchronizedList(new ArrayList<ByteBuffer>());
	public NIOClient(String host, int port) throws IOException {
		super(host, port);
	}
	protected SelectableChannel getChannel() throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress(host, port));
		channel.register(selector, SelectionKey.OP_CONNECT);
		return channel;
	}

	public void send(String message) {
		requests.add(ByteBuffer.wrap(message.getBytes()));
		if(null == channel || null == selector) return;
		channel.keyFor(selector).interestOps(SelectionKey.OP_WRITE);
		selector.wakeup();
	}
	protected void write(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel)key.channel();
		while (!requests.isEmpty()) {
			ByteBuffer buf = requests.get(0);
			for(IMessageFilter filter : filters) {
				buf = filter.onSend(buf);
				buf.rewind();
			}
			socketChannel.write(buf);
			requests.remove(0);
		}
		key.interestOps(SelectionKey.OP_READ);
	}
	protected void read(SelectionKey key) throws IOException {
		ByteBuffer copyBuf = getCopiedBuffer(key);
		for(IMessageFilter filter : filters) {
			copyBuf = filter.onReceive(copyBuf);
			copyBuf.rewind();
		}
	}
	public static void main(String[] args) {
		NIOClient client;
		try {
			client = new NIOClient("javawide.com", 9090);
			client.addFilter(new IMessageFilter() {
				public ByteBuffer onReceive(ByteBuffer message) {
					System.out.println("Returned : " + Charset.defaultCharset().decode(message));
					return message;
				}
				public ByteBuffer onSend(ByteBuffer message) {
					String id = "Magneto : ";
					byte[] idMessage = new byte[id.length() + message.limit()];
					System.arraycopy(id.getBytes(), 0, idMessage, 0, id.length());
					System.arraycopy(message.array(), 0, idMessage, id.length(), message.limit());
					ByteBuffer buffer = ByteBuffer.wrap(idMessage);
					System.out.println("Send : " + Charset.defaultCharset().decode(buffer));
					return buffer;
				}				
			});
			client.start();
			client.send("Hello NIO");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
