package com.webuhee.filedialog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FileItem {
	String name;
	String type;
	long fileSize;
	long createdTime;
	long modifiedTime;
	
	public FileItem(String name, String type, long fileSize, long createdTime, long modifiedTime) {
		super();
		this.name = name;
		this.type = type;
		this.fileSize = fileSize;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public long getFileSize() {
		return fileSize;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public long getModifiedTime() {
		return modifiedTime;
	}
}
