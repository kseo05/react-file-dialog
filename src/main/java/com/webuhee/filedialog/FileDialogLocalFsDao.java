package com.webuhee.filedialog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.webuhee.filedialog.dto.FileItem;
import com.webuhee.filedialog.dto.FileList;

@Repository
public class FileDialogLocalFsDao implements FileDialogDao {
	@Value("${filedialog.localfs.rootpath}")
	String localFileSystemRootPath;

	@Override
	public FileList getFileList(String path) {
		
		try {
			FileList result = new FileList();
			String fullPath = localFileSystemRootPath + path;
			File file = new File(fullPath);
			
			if (file.isDirectory()) {
				List<File> fileList = Arrays.asList(file.listFiles());
				
				Iterator<File> it = fileList.iterator();
				
				while (it.hasNext()) {
					File fileItem = it.next();
					StringBuilder itemPathBuilder = new StringBuilder(fullPath);
					itemPathBuilder.append("/");
					itemPathBuilder.append(fileItem.getName());
					
					Path filePath = Paths.get(itemPathBuilder.toString());
					BasicFileAttributes fileAttr= Files.getFileAttributeView(filePath, BasicFileAttributeView.class).readAttributes();
					
					String name = fileItem.getName();
					String type = fileAttr.isDirectory() ? "dir" : "file";
					long fileSize = fileAttr.size();
					long createdTime = fileAttr.creationTime().toMillis();
					long modifiedTime = fileAttr.lastModifiedTime().toMillis();
					
					result.add(new FileItem(name, type, fileSize, createdTime, modifiedTime));
				}
				
				return result;
				
			} else {
				throw new IOException("Expected Directory Path");
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
