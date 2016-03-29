package com.webuhee.filedialog;

import org.springframework.beans.factory.annotation.Autowired;

import com.webuhee.filedialog.dto.FileList;

public class FileDialogServiceImpl implements FileDialogService {
	@Autowired
	FileDialogDao dao;

	@Override
	public FileList getFileList(String path) {
		// TODO Auto-generated method stub
		return dao.getFileList(path);
	}

}
