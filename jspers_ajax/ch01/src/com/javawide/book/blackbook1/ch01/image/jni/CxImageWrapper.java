package com.javawide.book.blackbook1.ch01.image.jni;

public class CxImageWrapper {
	static {
		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("comjavawide_CxImageWrapper");
	}
	private native void doCrop(String srcFileName, String dstFileName, int startX,
			int startY, int width, int height);
	public void crop(String srcFileName, String dstFileName, int left,
			int top, int right, int bottom) {
		doCrop(srcFileName, dstFileName, left, top, right, bottom);
	}
	
	private native void doResample(String srcFileName, String dstFileName, int width, int height);
	public void resample(String srcFileName, String dstFileName, int width, int height) {
		doResample(srcFileName, dstFileName, width, height);
	}
	public static void main(String[] args) {
		CxImageWrapper cxImage = new CxImageWrapper();
		cxImage.crop("C:/cropped/sunset.bmp", "C:/cropped/sunset_cropped.jpg", 200, 0, 600, 500);
		cxImage.resample("C:/cropped/sunset.bmp", "C:/cropped/sunset_resampled.jpg", 128, 128);
	}
}