package com.webuhee.filedialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webuhee.filedialog.dto.FileList;

@Service
public class FileDialogServiceImpl implements FileDialogService {
	@Autowired
	FileDialogDao dao;

	@Override
	public FileList getFileList(String path) {
		String refinedPath = path.replace("\\", "/");
		
		// "../" 와 "//" 등은 허용하지 않는다.
		if (refinedPath.indexOf("../") > -1 || refinedPath.indexOf("//") > -1) {
			return null;
		}
		
		if (! refinedPath.endsWith("/")) {
			refinedPath += "/";
		}
		
		return dao.getFileList(refinedPath);
	}

}
