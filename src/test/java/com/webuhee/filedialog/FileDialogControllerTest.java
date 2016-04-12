package com.webuhee.filedialog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webuhee.filedialog.FileDialogController;
import com.webuhee.filedialog.FileDialogService;
import com.webuhee.filedialog.dto.FileList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class FileDialogControllerTest {
	private MockMvc mockMvc;
	
	@InjectMocks FileDialogController con = new FileDialogController();
	@Mock FileDialogService svc;
	@Mock FileList fileList;
	@Mock ObjectMapper objMapper;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(con).build();
	}
	
	@Test
	public void shouldGetFileListInDirectory() throws Exception {
		when(svc.getFileList("")).thenReturn(fileList);
		when(svc.getFileList("/")).thenReturn(fileList);
		when(svc.getFileList("/docs")).thenReturn(fileList);
		when(svc.getFileList("/docs/")).thenReturn(fileList);
		this.mockMvc.perform(get("/file-dialog/storage")).andExpect(status().isOk()).andExpect(content().string(""));
		this.mockMvc.perform(get("/file-dialog/storage/")).andExpect(status().isOk()).andExpect(content().string(""));
		this.mockMvc.perform(get("/file-dialog/storage/docs")).andExpect(status().isOk()).andExpect(content().string(""));
		this.mockMvc.perform(get("/file-dialog/storage/docs/")).andExpect(status().isOk()).andExpect(content().string(""));
		
		this.mockMvc.perform(get("")).andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/")).andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/file-dialog")).andExpect(status().is4xxClientError());
		this.mockMvc.perform(get("/file-dialog/")).andExpect(status().is4xxClientError());
	}
}
