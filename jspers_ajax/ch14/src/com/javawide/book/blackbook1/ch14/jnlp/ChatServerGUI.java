package com.javawide.book.blackbook1.ch14.jnlp;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import com.javawide.book.blackbook1.ch14.chatting.NIOServer;

public class ChatServerGUI extends Frame{
	private static final long serialVersionUID = -6342419303756789065L;
	private Panel top;
	private Panel bottom;
	private List userList;
	private TextArea txtNotice;
	private int port;
	private String host;
	private NIOServer server;
	private ArrayList<String> users = new ArrayList<String>();
	
	public ChatServerGUI() {
		buildControls();
		buildListeners();
		pack();
		setVisible(true);
	}
	private void buildControls() {
		this.buildPanels();
		this.buildLabels();
		this.buildLists();
		this.buildTextAreas();
	}
	private void buildPanels() {
		this.top = new Panel(new BorderLayout());
		this.bottom = new Panel(new BorderLayout());
		add("North", top);
		add("South", bottom);
	}
	private void buildLabels() {
		this.top.add("West", new Label("접속자 목록"));
		this.bottom.add("West", new Label("공지 사항 전달"));
	}
	private void buildLists() {
		this.userList = new List();
		top.add("East", userList);
	}
	private void buildTextAreas() {
		this.txtNotice = new TextArea("", 2, 25, TextArea.SCROLLBARS_VERTICAL_ONLY);
		bottom.add("South", txtNotice);
	}
	private void buildListeners() {
		buildCloseButtonListener();
		buildTextAreaListener();
	}
	private void buildCloseButtonListener() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				server.close();
				dispose();
			}
		});		
	}
	private void buildTextAreaListener() {
		txtNotice.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_ENTER == e.getKeyCode()) {
					send();
					e.consume();
				}
			}
		});
	}	
	private void send() {
		server.broadcast(txtNotice.getText());
		txtNotice.setText("");		
	}
	public void buildConnection() {
		try {
			server = new NIOServer(host, port);
			server.addFilter(new UserFilter(this));
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setPort(String port) {
		if(null == port) port = "0";
		this.port = Integer.parseInt(port);
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void addUser(String userId) {
		if(!users.contains(userId)) {
			users.add(userId);
			userList.add(userId);
		}
	}
	public void removeUser(String userId) {
		if(users.contains(userId)) {
			users.remove(userId);
			userList.remove(userId);
		}
	}
	public static void main(String[] args) {
		ChatServerGUI serverGUI = new ChatServerGUI();
		serverGUI.setTitle("Server GUI - ADMIN");
		serverGUI.setHost("javawide.com");
		serverGUI.setPort("9090");
		/*serverGUI.setHost(args[0]);
		serverGUI.setPort(args[1]);
		*/
		serverGUI.buildConnection();
	}
}
