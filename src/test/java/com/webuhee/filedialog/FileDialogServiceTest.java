package com.webuhee.filedialog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.webuhee.filedialog.FileDialogLocalFsDao;
import com.webuhee.filedialog.FileDialogServiceImpl;
import com.webuhee.filedialog.dto.FileList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FileDialogServiceTest {
	// TODO : NewsServiceTest
	@InjectMocks FileDialogServiceImpl svc = new FileDialogServiceImpl();
	@InjectMocks FileDialogLocalFsDao dao = new FileDialogLocalFsDao();
	
	@Mock FileList fileList;
	
	@Test
	public void shouldReturnFileListInDirectory() throws Exception {
		when(dao.getFileList("/")).thenReturn(fileList);
		assertThat(svc.getFileList("/"),  is(fileList));
		assertThat(svc.getFileList(""),  is(fileList));
		
		when(dao.getFileList("/docs/")).thenReturn(fileList);
		assertThat(svc.getFileList("/docs/"),  is(fileList));
		assertThat(svc.getFileList("/docs"),  is(fileList));
	}

	@Test(expected=Exception.class)
	public void shouldThrowExceptionWhileGetFileListInDirectory1() {
		svc.getFileList("/../../../../../../root");
	}
	@Test(expected=Exception.class)
	public void shouldThrowExceptionWhileGetFileListInDirectory2() {
		svc.getFileList("/../../../../../../root/");
	}
	@Test(expected=Exception.class)
	public void shouldThrowExceptionWhileGetFileListInDirectory3() {
		svc.getFileList("///root");
	}
	@Test(expected=Exception.class)
	public void shouldThrowExceptionWhileGetFileListInDirectory4() {
		svc.getFileList("///root/");
	}
	@Test(expected=Exception.class)
	public void shouldThrowExceptionWhileGetFileListInDirectory5() {
		svc.getFileList("//!@$%^&*()_+``[]{}:;\"'<,>.?/한글/root");
	}
	@Test(expected=Exception.class)
	public void shouldThrowExceptionWhileGetFileListInDirectory6() {
		svc.getFileList("//!@$%^&*()_+``[]{}:;\"'<,>.?/한글/root/");
	}
}
