package com.javawide.book.blackbook1.ch11.uploader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class HelloUploader {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String path;
	private ProgressListener listener;

	public HelloUploader(HttpServletRequest request, HttpServletResponse response, String path) {
		this.request = request;
		this.response = response;
		setPath(path);
	}
	public void setPath(String path) {
		this.path = path;
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
	}
	public void setListener(ProgressListener listener) {
		this.listener = listener;
		upload();
	}
	private void upload() {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//upload.setHeaderEncoding("UTF-8");
		upload.setProgressListener(listener);
		FileItem fileItem = null;
		List<?> items;
		try {
			items = upload.parseRequest(request);
			for(Object aItem : items) {
				fileItem = (FileItem)aItem;
				if(!fileItem.isFormField()) {
					String file = fileItem.getName();
					file = file.substring(file.lastIndexOf(File.separator) + 1); 
					File f = new File(path + File.separator + file);
					if(f.isDirectory()) {
						report("Upload Fail");
						return;
					}
					fileItem.write(f);
				}					
			}
			report("Upload OK");
		} catch (FileUploadException e) {
			e.printStackTrace();
			report("Upload Fail");
		} catch (Exception e) {
			e.printStackTrace();
			report("Upload Fail");
		}
	}

	private void report(String message) {
		try {
			response.getWriter().print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
