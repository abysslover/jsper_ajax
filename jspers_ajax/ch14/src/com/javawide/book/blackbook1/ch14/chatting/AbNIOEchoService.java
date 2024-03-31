package com.javawide.book.blackbook1.ch14.chatting;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;

import com.javawide.book.blackbook1.ch02.AbEchoService;

public abstract class AbNIOEchoService extends AbEchoService {
	protected InetAddress host;
	protected int port;
	protected Selector selector;
	protected ByteBuffer buffer = ByteBuffer.allocate(8192);
	protected ArrayList<IMessageFilter> filters = new ArrayList<IMessageFilter>();
	protected SelectableChannel channel;

	protected abstract void write(SelectionKey key) throws IOException;
	protected abstract void read(SelectionKey key) throws IOException;
	protected abstract SelectableChannel getChannel() throws IOException;
	
	public AbNIOEchoService(String host, int port) throws UnknownHostException {
		this.host = InetAddress.getByName(host);
		this.port = port;
	}
	protected void accept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(this.selector, SelectionKey.OP_READ);
		socketChannel.keyFor(selector).attach(new ArrayList<ByteBuffer>());
	}
	protected void connect(SelectionKey key) throws IOException {
		((SocketChannel)key.channel()).finishConnect();
		key.interestOps(SelectionKey.OP_WRITE);
	}	
	public void doStart() throws IOException {
		buildSelector();
	}
	public void close() {
		try {
			closeSelector();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doEcho() throws IOException {
		while (true) {
			selector.select();
			Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
			while (selectedKeys.hasNext()) {
				SelectionKey key = selectedKeys.next();
				selectedKeys.remove();
				if (!key.isValid()) continue;
				if (key.isAcceptable()) {
					accept(key);
				} else if (key.isConnectable()) {
					connect(key);
				} else if (key.isReadable()) {
					read(key);
				} else if (key.isWritable()) {
					write(key);
				}
			}
		}
	}
	protected void buildSelector() throws IOException {
		this.selector = SelectorProvider.provider().openSelector();
		this.channel = getChannel();
	}
	protected void closeSelector() throws IOException {
		if(null != channel)
			channel.close();
		if(null != selector)
			selector.close();		
	}
	protected ByteBuffer getCopiedBuffer(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel)key.channel();
		buffer.clear();
		int numRead = 0;
		IOException exception = new IOException("Connection Closed.");
		try {
			numRead = channel.read(buffer);
			if(0 > numRead) throw exception;
		} catch (IOException e) {
			key.cancel();
			channel.close();
			throw exception;
		}
		byte[] copyBuf = new byte[numRead];
		System.arraycopy(buffer.array(), 0, copyBuf, 0, numRead);
		return ByteBuffer.wrap(copyBuf);
	}
	public void addFilter(IMessageFilter filter) {
		filters.add(filter);
	}
	public void removeFilter(IMessageFilter filter) {
		filters.remove(filter);
	}
}
