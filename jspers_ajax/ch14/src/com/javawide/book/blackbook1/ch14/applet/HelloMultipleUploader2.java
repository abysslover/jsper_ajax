package com.javawide.book.blackbook1.ch14.applet;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.*;

public class HelloMultipleUploader2 extends HelloMultipleUploader {
	private static final long serialVersionUID = 8723534641727990181L;
	private Vector fileList;
	private JTable uploadFileTable;
	private JProgressBar totalUploadProgressive;
	private long prosessedLength;
	private long prevCurrentTimeMillis;
	private long prevProsessedLength;
	private String bps;
	protected void buildControls() {
		super.buildControls();
		buildTable();
	}
	protected void buildTable() {
		buildUploadFileTable();
	}
	private void buildUploadFileTable() {
		Vector cols = new Vector(5);
		cols.add("이름(Name)");
		cols.add("크기(Size)");
		fileList = new Vector();
		uploadFileTable = new JTable(fileList, cols);
		uploadFileTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		JScrollPane scrollPane = new JScrollPane(uploadFileTable);
		uploadFileTable.setPreferredScrollableViewportSize(new Dimension(300, 70));
		uploadFileTable.setFillsViewportHeight(true);
		add("Center", scrollPane);
	}
	protected void onFileSelected() {
		fileList.clear();
		for(int i=files.length-1;i>-1; --i) {
			Vector v = new Vector();
			v.add(files[i].getName());
			v.add(Long.valueOf(files[i].length()));
			fileList.add(v);
		}
		uploadFileTable.paintImmediately(uploadFileTable.getBounds());
		uploadFileTable.addNotify();
	}
	protected void buildProgressiveBar() {
		super.buildProgressiveBar();
		buildTotalUploadProgressiveBar();
	}
	private void buildTotalUploadProgressiveBar() {
		this.totalUploadProgressive = new JProgressBar(); 
		progressiveBars.add("South", totalUploadProgressive);
		totalUploadProgressive.setStringPainted(true);
		totalUploadProgressive.setDoubleBuffered(true);
		totalUploadProgressive.setString("0Bps");
	}
	protected final void onWritten(long written) {
		prosessedLength += written;
		totalUploadProgressive.setValue((int)((double)prosessedLength /(double)totalFileLength.longValue() * 100));
		totalUploadProgressive.setString(getBPS());		
	}
	private String getBPS() {
		long currentTimeMillis = System.currentTimeMillis();
		long timeDiff =  currentTimeMillis - this.prevCurrentTimeMillis;
		if(timeDiff < 500) return this.bps;
		double bits = (this.prosessedLength - this.prevProsessedLength) * 8;
		bits = bits * 1000 / timeDiff;
		String bps = "";
		if(bits > 1073741824) {
			bps = String.format("%10.2f", bits / 1073741824) + "GBps";
		} else if(bits > 1048576) {
			bps = String.format("%5.2f", bits / 1048576) + "MBps";
		} else if(bits > 1024) {
			bps = String.format("%5.2f", bits / 1024) + "KBps";
		} else if(bits <= 1024) {
			bps = bits + "Bps";
		}
		this.prevProsessedLength = this.prosessedLength;
		this.prevCurrentTimeMillis = currentTimeMillis;
		this.bps = bps;
		return bps;
	}
	protected void onUploadStart() {
		this.prosessedLength = 0;
	}
}
