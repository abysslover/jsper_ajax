package com.javawide.book.blackbook1.ch14.jnlp;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.javawide.book.blackbook1.ch14.chatting.IMessageFilter;
import com.javawide.book.blackbook1.ch14.chatting.NIOClient;

public class ChatClientGUI extends Frame {
	private static final long serialVersionUID = -4248050684910462273L;
	private Panel top;
	private Panel bottom;
	private TextArea txtPrevious;
	private TextArea txtMessage;
	private int port;
	private String host;
	private NIOClient client;
	private String id;
	public ChatClientGUI() {
		buildControls();
		buildListeners();
		pack();
		setVisible(true);
	}
	private void buildControls() {
		this.buildPanels();
		this.buildLabels();
		this.buildTextAreas();
	}
	private void buildListeners() {
		buildCloseButtonListener();
		buildTextAreaListener();
	}
	private void buildCloseButtonListener() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(null != client) {
					client.send("removeUser " + id);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					client.close();
				}
				dispose();
			}
		});		
	}
	private void buildTextAreaListener() {
		txtMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_ENTER == e.getKeyCode()) {
					send();
					e.consume();
				}
			}
		});
	}
	private void send() {
		client.send(txtMessage.getText());
		txtMessage.setText("");
	}
	
	public void buildConnection() {
		try {
			client = new NIOClient(host, port);
			client.addFilter(new IMessageFilter() {
				public ByteBuffer onReceive(ByteBuffer message) {
					txtPrevious.append(Charset.defaultCharset().decode(message).toString() +
							System.getProperty("line.separator"));
					return message;
				}
				public ByteBuffer onSend(ByteBuffer message) {
					return ByteBuffer.wrap(new String(id + " : " +
							Charset.defaultCharset().decode(message)).getBytes()); 
				}
			});
			client.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void buildPanels() {
		this.top = new Panel(new BorderLayout());
		this.bottom = new Panel(new BorderLayout());
		add("North", top);
		add("South", bottom);
	}
	private void buildLabels() {
		this.top.add("North", new Label("지난 대화"));
		this.bottom.add("North", new Label("전달할 메시지"));
	}
	private void buildTextAreas() {
		this.txtPrevious = new TextArea("", 2, 25, TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtPrevious.setEditable(false);
		top.add("South", txtPrevious);
		this.txtMessage = new TextArea("", 2, 25, TextArea.SCROLLBARS_VERTICAL_ONLY);
		bottom.add("South", txtMessage);
	}	
	public void setPort(int port) {
		this.port = port;
	}
	public void setPort(String port) {
		if(null == port) port = "0";
		this.port = Integer.parseInt(port);
	}
	private void setHost(String host) {
		this.host = host;
	}
	private void setId(String id) {
		this.id = id;
		setTitle(id + " - Chatter");
		client.send("addUser " + id);
	}
	public static void main(String[] args) {
		ChatClientGUI clientGUI = new ChatClientGUI();
		clientGUI.setHost(args[0]);
		clientGUI.setPort(args[1]);
		clientGUI.buildConnection();
		clientGUI.setId(args[2]);
	}
}
