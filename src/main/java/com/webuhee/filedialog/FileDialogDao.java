package com.webuhee.filedialog;

import com.webuhee.filedialog.dto.FileList;

public interface FileDialogDao {
	public FileList getFileList(String path);
}
