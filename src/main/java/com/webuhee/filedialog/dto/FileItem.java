package com.webuhee.filedialog.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FileItem {
	String name;
	int size;
	Date createdDate;
	Date modifiedDate;
	
	public String getName() {
		return name;
	}
	public int getSize() {
		return size;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
}
