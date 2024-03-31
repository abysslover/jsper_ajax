package com.javawide.book.blackbook1.ch14.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.*;

public class HelloMultipleUploader extends Applet {
	private static final long serialVersionUID = 5519898546114212820L;
	private LinkedHashMap headers = new LinkedHashMap();
	private static final String BOUNDARY = "-----------------javawide.com-LIMEUNCHEON";
	private static final String LINE_SEPARATOR = "\r\n";
	private BigDecimal LIMIT_FILE_SIZE;
	protected File[] files;
	private SocketChannel out;
	private String host;
	private int port = 8088;
	private String uploadURL;
	private Selector sel;
	private JProgressBar uploadProgressive;
	protected JPanel buttons;
	protected JPanel progressiveBars;
	protected BigDecimal totalFileLength;
	protected void getParameters() {
		host = getParameter("HOST");
		if(null == host) {
			host = getCodeBase().getHost();
			if(null == host) {
				host = "localhost";
			}
		}
		String port = getParameter("PORT");
		String uploadURL = getParameter("UPLOAD_URL");
		String limitFileSize = getParameter("LIMIT_FILE_SIZE");
		this.port = (null == port) ? 8088 : Integer.parseInt(port);
		this.uploadURL = (null == uploadURL) ? "/ch11/HelloUploader.jsp" : uploadURL;
		this.LIMIT_FILE_SIZE = (null == limitFileSize) ? new BigDecimal(104857600) : new BigDecimal(limitFileSize);
	}
	public void init() {
		System.out.println("Applet 초기화 ");
	}
	public void start() {
		setLayout(new BorderLayout());
		buildControls();
	}
	protected void buildControls() {
		buildButtons();
		buildProgressiveBar();
	}
	protected void buildProgressiveBar() {
		this.progressiveBars = new JPanel(new BorderLayout());
		add("North", progressiveBars);
		buildUploadProgressiveBar();
	}
	private void buildUploadProgressiveBar() {
		this.uploadProgressive = new JProgressBar();
		uploadProgressive.setStringPainted(true);
		progressiveBars.add("North", uploadProgressive);
	}
	protected void buildButtons() {
		this.buttons = new JPanel();
		add("South", buttons);
		buildFileOpenButton();
		buildUploadButton();
	}
	protected void buildChannel() throws IOException {
		//out = new FileOutputStream(new File("C:/HTTPPosted.txt")).getChannel();
		SocketAddress addr = new InetSocketAddress(InetAddress.getByName(host), port);
		out = SocketChannel.open(addr);
		out.configureBlocking(false);
		sel = Selector.open();
		out.register(sel, SelectionKey.OP_READ);
	}
	private void closeChannel() {
		try {
			if(null == out) return;
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void onUploadStart() {}
	private void buildUploadButton() {
		JButton upload = new JButton("업로드");
		this.buttons.add(upload);
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					SwingWorker worker = new SwingWorker() {
						protected Object doInBackground() throws Exception {
							try {
								getParameters();
								if(!hasSelectedFiles() || isOverflowLimitFileSize()) return null;
								onUploadStart();
								buildChannel();
								buildUploadCommand();
								buildHeaders();
								buildBody();
								buildLastBoundary();
								getResponseMessage();
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								closeChannel();
							}
							return null;
						}
					};
					public void run() {
						worker.execute();
					}
				});
			}
		});
	}
	private boolean hasSelectedFiles() {
		boolean hasFiles = (null != files);
		if(!hasFiles) {
			JOptionPane.showMessageDialog(this, "파일을 선택해 주세요");
		}
		return hasFiles;
	}
	
	private boolean isOverflowLimitFileSize() {
		this.totalFileLength = getTotalFileLength();
		boolean isOverflow = this.totalFileLength.compareTo(LIMIT_FILE_SIZE) > 0;
		if(isOverflow)
			JOptionPane.showMessageDialog(this,
					String.format("파일 용량이 제한 크기인 %s bytes를 넘겼습니다.", LIMIT_FILE_SIZE));
		return isOverflow;
	}
	private void buildFileOpenButton() {
		JButton fileOpen = new JButton("파일 열기");
		this.buttons.add(fileOpen);
		fileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						JFileChooser fileChooser = new JFileChooser(".");
						fileChooser.setMultiSelectionEnabled(true);
						fileChooser.showOpenDialog(null);
						files = fileChooser.getSelectedFiles();
						onFileSelected();
						for (int i = files.length - 1; i > -1; --i) {
							System.out.println(files[i].getAbsolutePath());
						}
					}
				});
			}
		});
	}
	protected void onFileSelected() {}
	private void buildUploadCommand() throws IOException {
		out.write(ByteBuffer.wrap(("POST " + uploadURL  + " HTTP/1.0" + LINE_SEPARATOR).getBytes()));
	}

	private void buildHeaders() throws IOException{
		headers.put("Accept", "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, */*");
		headers.put("Accept-Language", "ko");
		headers.put("Content-Type", "multipart/form-data; boundary="+BOUNDARY);
		headers.put("UA-CPU", "x86");
		headers.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 1.1.4322; .NET CLR 3.0.04506.30)");
		headers.put("Host", host);
		headers.put("Content-Length", getContentLength());
		headers.put("Connection", "Keep-Alive");
		headers.put("Pragma", "no-cache");
		writeHttpHeaders();
	}
	private void writeHttpHeaders() throws IOException {
		Iterator iter = headers.keySet().iterator();
		Object keyObj= null, valueObj = null;
		String aHeader = null;
		while(iter.hasNext()) {
			keyObj = iter.next();
			valueObj = headers.get(keyObj);
			aHeader = keyObj.toString() + ": " + valueObj.toString() + LINE_SEPARATOR;
			ByteBuffer buffer = ByteBuffer.wrap(aHeader.getBytes());
			out.write(buffer);
		}
		out.write(ByteBuffer.wrap(LINE_SEPARATOR.getBytes()));
	}
	private void buildBody() throws IOException {
		for (int i = files.length - 1; i > -1; --i) {
			out.write(ByteBuffer.wrap(getFormDataHeader(i)));
			writeReadedFile(files[i]);
		}
	}
	private void buildLastBoundary() throws IOException {
		out.write(ByteBuffer.wrap(String.format("%s--%s--%s",
				LINE_SEPARATOR, BOUNDARY, LINE_SEPARATOR).getBytes()));
	}
	private void getResponseMessage() throws IOException {
		System.out.println("-----------------------Response Message-----------------------");
		ByteBuffer buf = ByteBuffer.allocate(1024);
		sel.select();
		Iterator iter = sel.selectedKeys().iterator();
		while(iter.hasNext()) {
		    SelectionKey key = (SelectionKey)iter.next();
			SocketChannel sc = (SocketChannel)key.channel();
			sc.read(buf);
			buf.flip();
			System.out.println(Charset.forName("UTF-8").decode(buf).toString());
			buf.clear();
		}
		System.out.println("-----------------------Response Message-----------------------");
	}
	private void writeReadedFile(File file) {
		uploadProgressive.setMaximum(100);
		FileChannel in = null;
		long total = file.length();
		long written = 0;
		long currentWritten = 0;
		try {
			in = new FileInputStream(file).getChannel();
			while(written < total) {
				currentWritten = in.transferTo(written, 1024, out);
				written += currentWritten;
				onWritten(currentWritten);
				uploadProgressive.setValue((int)((double)written/(double)total * 100));
			}			
			in.close();
			in = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != in)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	protected void onWritten(long written) {}
	private byte[] getFormDataHeader(int index) {
		String contentType = URLConnection.guessContentTypeFromName(files[index].getName());
		contentType = (null == contentType) ? "application/octet-stream" : contentType;
		return String.format(
				"%s--%s%sContent-Disposition: form-data; name=\"fileToUpload%d\"; filename=\"%s\"%s" +
				"Content-Type: %s%s%s", LINE_SEPARATOR, BOUNDARY, LINE_SEPARATOR, index,
				files[index].getAbsolutePath(), LINE_SEPARATOR, contentType, LINE_SEPARATOR, LINE_SEPARATOR).getBytes();
	}
	private BigDecimal getContentLength() {
		return getTotalFileLength().add(getFormDataHeaderLength()).add(getLastBoundaryLength());
	}
	private BigDecimal getLastBoundaryLength() {
		String lastBoundary = LINE_SEPARATOR + "--" + BOUNDARY + "--" + LINE_SEPARATOR;
		return new BigDecimal(lastBoundary.length());
	}
	private BigDecimal getFormDataHeaderLength() {
		BigDecimal formDataLength = BigDecimal.ZERO;
		for (int i = files.length - 1; i > -1; --i) {
			formDataLength = formDataLength.add(new BigDecimal(getFormDataHeader(i).length));
		}
		return formDataLength;
	}
	protected BigDecimal getTotalFileLength() {
		BigDecimal length = BigDecimal.ZERO;
		for (int i = files.length - 1; i > -1; --i) {
			length = length.add(new BigDecimal(files[i].length()));
		}
		return length;
	}
}