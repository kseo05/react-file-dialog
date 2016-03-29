package com.webuhee.filedialog;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webuhee.filedialog.dto.FileList;

public class FileDialogLocalFsDao implements FileDialogDao {

	@Override
	public FileList getFileList(String path) {
		try {
			// 일단 이렇게.
			FileList stub = new ObjectMapper().readValue("["
					+ "{ 'name':'file1.txt', 'size':'1024', 'createDate':'?', 'modifiedDate':'?' }"
					+ "]", FileList.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
